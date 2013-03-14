import java.awt.Color;

public class ColorImage implements Image, Cloneable {

	private ImageType type;
	private ImageFormat format;
	private SingleChannel red;
	private SingleChannel green;
	private SingleChannel blue;

	private SingleChannel Y;
	private SingleChannel Cb;
	private SingleChannel Cr;

	public ColorImage(int height, int width, ImageFormat format, ImageType type) {
		if (format == null) {
			throw new IllegalArgumentException("ImageFormat can't be null");
		}
		this.red = new SingleChannel(width, height);
		this.green = new SingleChannel(width, height);
		this.blue = new SingleChannel(width, height);

		this.Y = new SingleChannel(width, height);
		this.Cb = new SingleChannel(width, height);
		this.Cr = new SingleChannel(width, height);

		this.format = format;
		this.type = type;
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

}
