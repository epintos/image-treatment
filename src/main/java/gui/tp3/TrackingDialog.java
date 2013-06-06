package gui.tp3;

import gui.Panel;
import model.ColorImage;
import model.DrawingContainer;
import model.mask.MaskFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by cristian@redmintlabs.com on 16/05/13 at 12:05
 */
public class TrackingDialog extends JDialog {
    private Runnable onClick = null;
    private DrawingContainer drawingContainer = new DrawingContainer();

    public TrackingDialog(final Panel panel) {
        setTitle("Tracking");
        setBounds(1, 1, 250, 130);
        Dimension size = getToolkit().getScreenSize();
        setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
        this.setResizable(false);
        setLayout(null);

        JPanel pan1 = new JPanel();
        pan1.setBounds(0, 0, 250, 40);


        JLabel msg = new JLabel("Clickea área a trakear...");
        msg.setSize(250, 40);

        final JButton okButton = new JButton("OK");
        okButton.setEnabled(false);
        okButton.setSize(250, 40);
        okButton.setBounds(0, 50, 250, 50);

        drawingContainer.inner = new ArrayList<Point>();
        drawingContainer.innerBorder = new ArrayList<Point>();
        drawingContainer.in = new ArrayList<Point>();

        final MouseListener listener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();
                model.Image img = panel.getImage();
                if(x >= 0 && y >= 0 && x < img.getWidth() && y < img.getHeight()){
                    drawingContainer.inner.add(new Point(x, y));
                    panel.loadTracker(drawingContainer);
                    panel.repaint();
                    if(drawingContainer.inner.size() == 2){
                        okButton.setEnabled(true);
                        panel.removeMouseListener(this);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        };


        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.Image aux = ((ColorImage)panel.getImage()).clone();
                aux.applyMask(MaskFactory.buildGaussianMask(5, 5));
                aux.tracking(panel.getDrawingContainer(), panel, true);
                panel.repaint();
                if (onClick != null) {
                    onClick.run();
                }
                panel.removeMouseListener(listener);
                dispose();
            }
        });

        panel.addMouseListener(listener);

        pan1.add(msg);
        this.add(pan1);
        this.add(okButton);
    }

    public Runnable getOnClick() {
        return onClick;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }
}
