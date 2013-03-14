package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import app.ColorUtilities;

public class ColorImage implements Image, Cloneable {

	private ImageType type;
	private ImageFormat format;
	private SingleChannel red;
	private SingleChannel green;
	private SingleChannel blue;

	public ColorImage(int height, int width, ImageFormat format, ImageType type) {
		if (format == null) {
			throw new IllegalArgumentException("ImageFormat can't be null");
		}
		this.red = new SingleChannel(width, height);
		this.green = new SingleChannel(width, height);
		this.blue = new SingleChannel(width, height);

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
		this.setPixel(x, y, Channel.RED, ColorUtilities.getRedFromRGB(rgb));
		this.setPixel(x, y, Channel.GREEN, ColorUtilities.getGreenFromRGB(rgb));
		this.setPixel(x, y, Channel.BLUE, ColorUtilities.getBlueFromRGB(rgb));
	}

	public void setPixel(int x, int y, Channel channel, double color) {

		if (!red.validPixel(x, y)) {
			throw new IllegalArgumentException("Invalid pixels on setPixel");
		}

		if (channel == Channel.RED) {
			red.setPixel(x, y, color);
			return;
		}
		if (channel == Channel.GREEN) {
			green.setPixel(x, y, color);
			return;
		}
		if (channel == Channel.BLUE) {
			blue.setPixel(x, y, color);
			return;
		}
		throw new IllegalStateException();
	}

	public int getRGBPixel(int x, int y) {
		int red = this.red
				.truncatePixel(getPixelFromChannel(x, y, Channel.RED));
		int green = this.green.truncatePixel(getPixelFromChannel(x, y,
				Channel.GREEN));
		int blue = this.blue.truncatePixel(getPixelFromChannel(x, y,
				Channel.BLUE));
		return new Color(red, green, blue).getRGB();
	}

	public double getPixelFromChannel(int x, int y, Channel channel) {
		if (channel == Channel.RED) {
			return red.getPixel(x, y);
		}
		if (channel == Channel.GREEN) {
			return green.getPixel(x, y);
		}
		if (channel == Channel.BLUE) {
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

}
