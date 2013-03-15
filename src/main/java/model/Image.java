package model;

public interface Image {

	public static enum ColorChannel {
		RED, GREEN, BLUE
	}

	public static enum ImageType {
		RGB, GRAYSCALE
	}

	public static enum ImageFormat {
		BMP, PGM, PPM, RAW
	}

	public static final int GRAY_LEVEL_AMOUNT = 256;

	/**
	 * Sets pixel in the correspoding RGB color channel
	 */
	public void setPixel(int x, int y, ColorChannel channel, double color);

	/**
	 * Sets pixel in the correspoding RGB color channel
	 */
	public void setRGBPixel(int x, int y, int rgb);

	public int getRGBPixel(int x, int y);

	public int getHeight();

	public int getWidth();

	public ImageType getType();

	public ImageFormat getImageFormat();

	// For TP 1
	/**
	 * Adds to images of the same size
	 * 
	 * @param img
	 * @return
	 */
	public Image add(Image img);

	/**
	 * Substracts to images of the same size
	 * 
	 * @param img
	 * @return
	 */
	public Image substract(Image img);

	/**
	 * Multiplies to images of the same size
	 * 
	 * @param img
	 * @return
	 */
	public Image multiply(Image img);
	
	/**
	 * Multiply image by an scalar
	 * @param scalar
	 */
	public void multiply(double scalar);
	
	public void dynamicRangeCompression();
	
	public void negative();

}
