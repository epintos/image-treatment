package model;

public class Channel implements Cloneable {

	static final int MIN_CHANNEL_COLOR = 0;
	static final int MAX_CHANNEL_COLOR = 255;

	private int width;
	private int height;

	// The matrix is represented by an array, and to get a pixel(x,y) make y * this.getWidth() + x
	private double[] channel;

	public Channel(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(
					"Images must have at least 1x1 pixel size");
		}

		this.width = width;
		this.height = height;
		this.channel = new double[width * height];
	}

	/**
	 * Indicates whether a coordinate is valid for a pixel
	 * @param x
	 * @param y
	 * @return True if the pixel is valid
	 */
	public boolean validPixel(int x, int y) {
		boolean validX = x >= 0 && x < this.getWidth();
		boolean validY = y >= 0 && y < this.getHeight();
		return validX && validY;
	}

	/**
	 * Returns the Channel height
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the Channel width
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets a pixel(x,y) in the channel
	 * @param x
	 * @param y
	 * @param color
	 */
	public void setPixel(int x, int y, double color) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}

		channel[y * this.getWidth() + x] = color;
	}

	/**
	 * Returns a pixel in the position x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public double getPixel(int x, int y) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}

		return channel[y * this.getWidth() + x];
	}

	int truncatePixel(double notTruncatedValue) {
		return (int) notTruncatedValue;
	}
	
	public void add(Channel otherChannel) {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y) + otherChannel.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}
	
	public void substract(Channel otherChannel) {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y) - otherChannel.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}
	
	public void multiply(Channel otherChannel) {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y) * otherChannel.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}
}
