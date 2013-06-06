package gui.tp3;

import com.xuggle.xuggler.*;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import gui.MessageFrame;
import gui.Panel;
import gui.Window;
import model.ColorImage;
import model.Image;
import model.mask.Mask;
import model.mask.MaskFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Tp3 extends JMenu {

    private static final long serialVersionUID = 1L;

    public Tp3() {
        super("TP 3");
        this.setEnabled(true);

        JMenuItem supressNoMaxs = new JMenuItem("Supresión de No Máximos");
        supressNoMaxs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    panel.getWorkingImage().suppressNoMaxs();
                    panel.repaint();
                }
            }
        });

        JMenuItem thresholdWithHysteresis = new JMenuItem(
                "Umbral con histéresis");
        thresholdWithHysteresis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    JDialog thresholdWithHysteresisDialog = new ThresholdWithHysteresisDialog(
                            panel);
                    thresholdWithHysteresisDialog.setVisible(true);
                }
            }
        });

        JMenuItem canny = new JMenuItem("Canny");
        canny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    panel.getWorkingImage().applyCannyBorderDetection();
                    panel.repaint();
                }
            }
        });

        JMenuItem susan = new JMenuItem("Susan");
        susan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    JDialog susanBorderDetectorDialog = new SusanBorderDetectorDialog(
                            panel);
                    susanBorderDetectorDialog.setVisible(true);
                }
            }
        });

        JMenuItem houghForLines = new JMenuItem("Hough para Líneas");
        houghForLines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    panel.getWorkingImage().houghTransformForLines();
                    panel.repaint();
                }

            }
        });

        JMenuItem houghForCircles = new JMenuItem("Hough para círulos");
        houghForCircles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    panel.getWorkingImage().houghTransformForCircles();
                    panel.repaint();
                }
            }
        });

        JMenuItem tracking = new JMenuItem("Tracking de Imágenes");
        tracking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                JDialog trackingDialog = new TrackingDialog(panel);
                trackingDialog.setVisible(true);
            }
        });

        JMenuItem video = new JMenuItem("Tracking de Video");
        video.addActionListener(new ActionListener() {
            public Panel panel;
            boolean firstCall = true;
            BufferedImage currentImage;
            private Object lock = "";
            private BlockingQueue<BufferedImage> frames = new LinkedBlockingQueue<BufferedImage>(10);

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(Tp3.this);
                panel = (((Window) getTopLevelAncestor()).getPanel());
                final IContainer container = IContainer.make();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File f = chooser.getSelectedFile();
                        try {
                            String filename = f.getAbsoluteFile().getAbsolutePath();
                            if (container.open(filename, IContainer.Type.READ, null) < 0) {
                                throw new IllegalArgumentException("could not open file: " + filename);
                            }

                            ;

                            int numStreams = container.getNumStreams();

                            // and iterate through the streams to find the first video stream
                            int videoStreamId = -1;
                            IStreamCoder videoCoder = null;
                            for (int i = 0; i < numStreams; i++) {
                                // Find the stream object
                                IStream stream = container.getStream(i);
                                // Get the pre-configured decoder that can decode this stream;
                                IStreamCoder coder = stream.getStreamCoder();

                                if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                                    videoStreamId = i;
                                    videoCoder = coder;
                                    break;
                                }
                            }
                            if (videoStreamId == -1)
                                throw new RuntimeException("could not find video stream in container: "
                                        + filename);

                            IConverter myConverter =
                                    ConverterFactory.createConverter(ConverterFactory.XUGGLER_BGR_24,
                                            IPixelFormat.Type.BGR24, videoCoder.getWidth(), videoCoder.getHeight());


                     /*
                     * Now we have found the video stream in this file.  Let's open up our decoder so it can
                     * do work.
                     */
                            if (videoCoder.open() < 0)
                                throw new RuntimeException("could not open video decoder for container: "
                                        + filename);

                            IVideoResampler resampler = null;
                            if (videoCoder.getPixelType() != IPixelFormat.Type.BGR24) {
                                // if this stream is not in BGR24, we're going to need to
                                // convert it.  The VideoResampler does that for us.
                                resampler = IVideoResampler.make(videoCoder.getWidth(),
                                        videoCoder.getHeight(), IPixelFormat.Type.BGR24,
                                        videoCoder.getWidth(), videoCoder.getHeight(), videoCoder.getPixelType());
                                if (resampler == null)
                                    throw new RuntimeException("could not create color space " +
                                            "resampler for: " + filename);
                            }

                    /*
                     * Now, we start walking through the container looking at each packet.
                     */
                            IPacket packet = IPacket.make();
                            long firstTimestampInStream = Global.NO_PTS;
                            long systemClockStartTime = 0;
                            while (container.readNextPacket(packet) >= 0) {
                          /*
                           * Now we have a packet, let's see if it belongs to our video stream
                           */
                                if (packet.getStreamIndex() == videoStreamId) {
                            /*
                             * We allocate a new picture to get the data out of Xuggler
                             */
                                    IVideoPicture picture = IVideoPicture.make(videoCoder.getPixelType(),
                                            videoCoder.getWidth(), videoCoder.getHeight());

                                    int offset = 0;
                                    while (offset < packet.getSize()) {
                              /*
                               * Now, we decode the video, checking for any errors.
                               *
                               */
                                        int bytesDecoded = videoCoder.decodeVideo(picture, packet, offset);
                                        if (bytesDecoded < 0)
                                            throw new RuntimeException("got error decoding video in: "
                                                    + filename);
                                        offset += bytesDecoded;

                                /*
                                 * Some decoders will consume data in a packet, but will not be able to construct
                                 * a full video picture yet.  Therefore you should always check if you
                                 * got a complete picture from the decoder
                                 */
                                        if (picture.isComplete()) {
                                            IVideoPicture newPic = picture;
                                    /*
                                     * If the resampler is not null, that means we didn't get the
                                     * video in BGR24 format and
                                     * need to convert it into BGR24 format.
                                     */
                                            if (resampler != null) {
                                                // we must resample
                                                newPic = IVideoPicture.make(resampler.getOutputPixelFormat(),
                                                        picture.getWidth(), picture.getHeight());
                                                if (resampler.resample(newPic, picture) < 0)
                                                    throw new RuntimeException("could not resample video from: "
                                                            + filename);
                                            }
                                            if (newPic.getPixelType() != IPixelFormat.Type.BGR24)
                                                throw new RuntimeException("could not decode video" +
                                                        " as BGR 24 bit data in: " + filename);

                                            /**
                                             * We could just display the images as quickly as we decode them,
                                             * but it turns out we can decode a lot faster than you think.
                                             *
                                             * So instead, the following code does a poor-man's version of
                                             * trying to match up the frame-rate requested for each
                                             * IVideoPicture with the system clock time on your computer.
                                             *
                                             * Remember that all Xuggler IAudioSamples and IVideoPicture objects
                                             * always give timestamps in Microseconds, relative to the first
                                             * decoded item. If instead you used the packet timestamps, they can
                                             * be in different units depending on your IContainer, and IStream
                                             * and things can get hairy quickly.
                                             */
                                            if (firstTimestampInStream == Global.NO_PTS) {
                                                // This is our first time through
                                                firstTimestampInStream = picture.getTimeStamp();
                                                // get the starting clock time so we can hold up frames
                                                // until the right time.
                                                systemClockStartTime = System.currentTimeMillis();
                                            } else {
                                                long systemClockCurrentTime = System.currentTimeMillis();
                                                long millisecondsClockTimeSinceStartofVideo =
                                                        systemClockCurrentTime - systemClockStartTime;
                                                // compute how long for this frame since the first frame in the
                                                // stream.
                                                // remember that IVideoPicture and IAudioSamples timestamps are
                                                // always in MICROSECONDS,
                                                // so we divide by 1000 to get milliseconds.
                                                long millisecondsStreamTimeSinceStartOfVideo =
                                                        (picture.getTimeStamp() - firstTimestampInStream) / 1000;
                                                final long millisecondsTolerance = 50; // and we give ourselfs 50 ms of tolerance
                                                final long millisecondsToSleep =
                                                        (millisecondsStreamTimeSinceStartOfVideo -
                                                                (millisecondsClockTimeSinceStartofVideo +
                                                                        millisecondsTolerance));
                                                if (millisecondsToSleep > 0) {
                                                    try {
                                                        Thread.sleep(millisecondsToSleep);
                                                    } catch (InterruptedException e2) {
                                                        // we might get this when the user closes the dialog box, so
                                                        // just return from the method.
                                                        return;
                                                    }
                                                }
                                            }

                                            // And finally, convert the BGR24 to an Java buffered image
                                            BufferedImage javaImage = myConverter.toImage(newPic);

                                            frames.put(javaImage);
                                        }
                                    }
                                } else {
                                    /*
                                     * This packet isn't part of our video stream, so we just
                                     * silently drop it.
                                     */
                                    do {
                                    } while (false);
                                }

                            }


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                t.start();


                final Mask mask = MaskFactory.buildGaussianMask(5, 5);

                final CountDownLatch latch = new CountDownLatch(1);

                new Thread(new Runnable() {
                    double[] avgIn = null;
                    ColorImage img = null;

                    @Override
                    public void run() {
                        try {
                            do {


                                BufferedImage javaImage = frames.poll(1, TimeUnit.MINUTES);
//                                frames.clear();

                                if (img == null) {
                                    img = new ColorImage(javaImage, null, Image.ImageType.RGB, true);
                                } else {
                                    img = ColorImage.reuse(img, javaImage);
                                }


                                if (firstCall) {
                                    panel.setImage(img);
                                    firstCall = false;
                                    panel.setWorkingImage(img);
                                    panel.repaint();
                                    TrackingDialog trackingDialog = new TrackingDialog(panel);
                                    trackingDialog.setVisible(true);
                                    trackingDialog.setOnClick(new Runnable() {
                                        @Override
                                        public void run() {
                                            firstCall = false;
                                            latch.countDown();
                                        }
                                    });
                                    latch.await();
                                } else {
                                    panel.setWorkingImage(img.clone());
                                    img.applyMask(mask);
                                    img.tracking(panel.getDrawingContainer(), panel, false);
                                }


                            } while (true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


        this.add(supressNoMaxs);
        this.add(new JSeparator());
        this.add(thresholdWithHysteresis);
        this.add(new JSeparator());
        this.add(canny);
        this.add(new JSeparator());
        this.add(susan);
        this.add(new JSeparator());
        this.add(houghForLines);
        this.add(houghForCircles);
        this.add(new JSeparator());
        this.add(tracking);
        this.add(video);

    }

    private boolean imageLoaded(Panel panel) {
        Image panelImage = panel.getWorkingImage();
        if (panelImage == null) {
            new MessageFrame("Debe cargarse una imagen antes");
            return false;
        }
        return true;
    }
}
