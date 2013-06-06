package app;

import model.Image;
import org.apache.sanselan.ImageFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ColorUtilities {

	public static int getRedFromRGB(int rgb) {
		return new Color(rgb).getRed();
	}

	public static int getGreenFromRGB(int rgb) {
		return new Color(rgb).getGreen();
	}

	public static int getBlueFromRGB(int rgb) {
		return new Color(rgb).getBlue();
	}

	public static BufferedImage populateEmptyBufferedImage(
			Image image) {
//		int height = image.getHeight();
//		int width = image.getWidth();
//
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				int rgbPixel = image.getRGBPixel(x, y);
//				emptyImage.setRGB(x, y, rgbPixel);
//			}
//		}
//        Graphics g = emptyImage.getGraphics();
//
//        g.drawImage(image.getBufferedImage(), image.getWidth(), image.getHeight(), null);
//
//        g.dispose();

        ColorModel cm = image.getBufferedImage().getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.getBufferedImage().copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public static int toBufferedImageType(Image.ImageType type) {
		if (type == Image.ImageType.GRAYSCALE) {
			return BufferedImage.TYPE_BYTE_GRAY;
		}
		if (type == Image.ImageType.RGB) {
			return BufferedImage.TYPE_INT_RGB;
		}
		throw new IllegalArgumentException();
	}

	public static ImageFormat toSanselanImageFormat(Image.ImageFormat fmt) {
		if (fmt == Image.ImageFormat.BMP) {
			return ImageFormat.IMAGE_FORMAT_BMP;
		}
		if (fmt == Image.ImageFormat.PGM) {
			return ImageFormat.IMAGE_FORMAT_PGM;
		}
		if (fmt == Image.ImageFormat.PPM) {
			return ImageFormat.IMAGE_FORMAT_PPM;
		}
		if (fmt == Image.ImageFormat.RAW) {
			return ImageFormat.IMAGE_FORMAT_UNKNOWN;
		}
		throw new IllegalArgumentException();
	}

	public static BufferedImage generateHistogram(Image image) {

		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.FREQUENCY);

		double[] histData = image.getHistogramPixels();

		dataset.addSeries("Histogram", histData, histData.length);

		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean show = false;
		boolean tooltips = false;
		boolean urls = false;

		JFreeChart chart = ChartFactory.createHistogram("Histograma",
				"Nivel de gris", "Apariciones", dataset, orientation, show,
				tooltips, urls);
		return chart.createBufferedImage(400, 200);
	}

}
