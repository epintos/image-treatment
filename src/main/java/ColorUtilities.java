import java.awt.Color;
import java.awt.image.BufferedImage;

import org.apache.sanselan.ImageFormat;

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

	public static void populateEmptyBufferedImage(BufferedImage emptyImage,
			Image image) {
		int height = image.getHeight();
		int width = image.getWidth();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgbPixel = image.getRGBPixel(x, y);
				emptyImage.setRGB(x, y, rgbPixel);
			}
		}
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
	
	public static ImageFormat toSanselanImageFormat(Image.ImageFormat fmt){
		if( fmt == Image.ImageFormat.BMP ){
			return ImageFormat.IMAGE_FORMAT_BMP;
		}
		if( fmt == Image.ImageFormat.PGM ){
			return ImageFormat.IMAGE_FORMAT_PGM;
		}
		if( fmt == Image.ImageFormat.PPM ){
			return ImageFormat.IMAGE_FORMAT_PPM;
		}
		if( fmt == Image.ImageFormat.RAW ){
			return ImageFormat.IMAGE_FORMAT_UNKNOWN;
		}
		throw new IllegalArgumentException();
	}

}
