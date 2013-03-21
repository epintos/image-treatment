package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import app.ColorUtilities;

public class ColorImage implements Image, Cloneable {

	private ImageType type;
	private ImageFormat format;
	private Channel red;
	private Channel green;
	private Channel blue;

	public ColorImage(int height, int width, ImageFormat format, ImageType type) {
		if (format == null) {
			throw new IllegalArgumentException("ImageFormat can't be null");
		}

		// Initialize a channel for each RGB color
		this.red = new Channel(width, height);
		this.green = new Channel(width, height);
		this.blue = new Channel(width, height);

		this.format = format;
		this.type = type;
	}

	public ColorImage(BufferedImage bi, ImageFormat format, ImageType type) {
		this(bi.getHeight(), bi.getWidth(), format, type);
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				Color c = new Color(bi.getRGB(x, y));

				red.setPixel(x, y, c.getRed());
				green.setPixel(x, y, c.getGreen());
				blue.setPixel(x, y, c.getBlue());
			}
		}
	}

	public void setRGBPixel(int x, int y, int rgb) {
		this.setPixel(x, y, ColorChannel.RED, ColorUtilities.getRedFromRGB(rgb));
		this.setPixel(x, y, ColorChannel.GREEN,
				ColorUtilities.getGreenFromRGB(rgb));
		this.setPixel(x, y, ColorChannel.BLUE,
				ColorUtilities.getBlueFromRGB(rgb));
	}

	public void setPixel(int x, int y, ColorChannel channel, double color) {

		if (!red.validPixel(x, y)) {
			throw new IllegalArgumentException("Invalid pixels on setPixel");
		}

		if (channel == ColorChannel.RED) {
			red.setPixel(x, y, color);
			return;
		}
		if (channel == ColorChannel.GREEN) {
			green.setPixel(x, y, color);
			return;
		}
		if (channel == ColorChannel.BLUE) {
			blue.setPixel(x, y, color);
			return;
		}
		throw new IllegalStateException();
	}

	public int getRGBPixel(int x, int y) {
		int red = this.red.truncatePixel(getPixelFromChannel(x, y,
				ColorChannel.RED));
		int green = this.green.truncatePixel(getPixelFromChannel(x, y,
				ColorChannel.GREEN));
		int blue = this.blue.truncatePixel(getPixelFromChannel(x, y,
				ColorChannel.BLUE));
		return new Color(red, green, blue).getRGB();
	}

	public double getPixelFromChannel(int x, int y, ColorChannel channel) {
		if (channel == ColorChannel.RED) {
			return red.getPixel(x, y);
		}
		if (channel == ColorChannel.GREEN) {
			return green.getPixel(x, y);
		}
		if (channel == ColorChannel.BLUE) {
			return blue.getPixel(x, y);
		}
		throw new IllegalStateException();
	}

	public int getHeight() {
		return red.getHeight();
	}

	public int getWidth() {
		return red.getWidth();
	}

	public ImageType getType() {
		return type;
	}

	public ImageFormat getImageFormat() {
		return format;
	}

	public Image add(Image img) {
		ColorImage ci = (ColorImage) img;

		this.red.add(ci.red);
		this.green.add(ci.green);
		this.blue.add(ci.blue);
		return this;
	}

	public Image multiply(Image img) {
		ColorImage ci = (ColorImage) img;

		this.red.multiply(ci.red);
		this.green.multiply(ci.green);
		this.blue.multiply(ci.blue);
		return this;
	}

	public Image substract(Image img) {
		ColorImage ci = (ColorImage) img;

		this.red.substract(ci.red);
		this.green.substract(ci.green);
		this.blue.substract(ci.blue);
		return this;
	}

	public void multiply(double scalar) {
		this.red.multiply(scalar);
		this.green.multiply(scalar);
		this.blue.multiply(scalar);
	}

	public void dynamicRangeCompression() {
		double maxRed = -Double.MAX_VALUE;
		double maxGreen = -Double.MAX_VALUE;
		double maxBlue = -Double.MAX_VALUE;
		
		//Calculates R
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				double redPixel = red.getPixel(i, j);
				double greenPixel = green.getPixel(i, j);
				double bluePixel = blue.getPixel(i, j);

				maxRed = Math.max(maxRed, redPixel);
				maxGreen = Math.max(maxGreen, greenPixel);
				maxBlue = Math.max(maxBlue, bluePixel);
			}
		}

		this.red.dynamicRangeCompression(maxRed);
		this.green.dynamicRangeCompression(maxGreen);
		this.blue.dynamicRangeCompression(maxBlue);
	}

	public void negative() {
		this.red.negative();
		this.blue.negative();
		this.green.negative();
	}

	public double[] getHistogramPixels() {
		double[] result = new double[this.getHeight() * this.getWidth()];

		for (int i = 0; i < result.length; i++) {
			result[i] = getGraylevelFromPixel(
					(int) Math.floor(i / this.getWidth()), i % this.getHeight());
		}

		return result;
	}

	private double getGraylevelFromPixel(int x, int y) {
		double red = this.red.getPixel(x, y);
		double green = this.green.getPixel(x, y);
		double blue = this.blue.getPixel(x, y);

		return (red + green + blue) / 3.0;
	}

	public void threshold(double value) {
		this.red.threshold(value);
		this.blue.threshold(value);
		this.green.threshold(value);
	}

	public void equalize() {
		this.red.equalize();
		this.green.equalize();
		this.blue.equalize();
	}
	
	public void contrast(double r1, double r2, double y1, double y2) {
		this.red.contrast(r1, r2, y1, y2);
		this.blue.contrast(r1, r2, y1, y2);
		this.green.contrast(r1, r2, y1, y2);
	}

}
