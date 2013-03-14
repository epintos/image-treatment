package model;

public interface Image {

	public static enum Channel {
		RED, GREEN, BLUE
	}

	public static enum ImageType {
		RGB, GRAYSCALE
	}

	public static enum ImageFormat {
		BMP, PGM, PPM, RAW
	}

	public static final int GRAY_LEVEL_AMOUNT = 256;

	public void setPixel(int x, int y, Channel channel, double color);

	public void setRGBPixel(int x, int y, int rgb);

	public int getRGBPixel(int x, int y);

	public int getHeight();

	public int getWidth();

	public ImageType getType();

	public ImageFormat getImageFormat();

}
