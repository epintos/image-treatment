package app;

import java.awt.Color;

import model.ColorImage;
import model.Image;

public class ImageCreator {

	public static Image createBinaryImage(int height, int width) {
		Image binaryImage = new ColorImage(height, width,
				Image.ImageFormat.BMP, Image.ImageType.GRAYSCALE);

		Color blackColor = Color.BLACK;
		Color whiteColor = Color.WHITE;

		// Generates a black square with a smaller white square inside
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Analyzes if the point is in the black or white square
				boolean fitsInSquareByWidth = (x > width / 4 && x < 3 * (width / 4));
				boolean fitsInSquareByHeight = (y > height / 4 && y < 3 * (height / 4));
				boolean fitsInSquare = (fitsInSquareByWidth && fitsInSquareByHeight);
				Color colorToApply = fitsInSquare ? whiteColor : blackColor;
				binaryImage.setRGBPixel(x, y, colorToApply.getRGB());
			}
		}

		return binaryImage;
	}

	public static Image createWhiteImage(int height, int width) {
		Image whiteImage = new ColorImage(height, width,
				Image.ImageFormat.BMP, Image.ImageType.GRAYSCALE);

		Color whiteColor = Color.WHITE;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				whiteImage.setRGBPixel(x, y, whiteColor.getRGB());
			}
		}

		return whiteImage;
	}

	public static Image createDegrade(boolean isColor, int height, int width,
			int color1, int color2) {

		Image degrade = null;

		if (isColor) {
			degrade = new ColorImage(height, width, Image.ImageFormat.BMP,
					Image.ImageType.RGB);
		} else {
			degrade = new ColorImage(height, width, Image.ImageFormat.BMP,
					Image.ImageType.RGB);
		}

		Color c1 = new Color(color1);
		Color cAux = new Color(color1);
		Color c2 = new Color(color2);

		float redFactor = (float) (c2.getRed() - c1.getRed()) / height;
		float greenFactor = (float) (c2.getGreen() - c1.getGreen()) / height;
		float blueFactor = (float) (c2.getBlue() - c1.getBlue()) / height;

		float red = 0;
		float green = 0;
		float blue = 0;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				degrade.setRGBPixel(x, y, c1.getRGB());
			}
			red = red + redFactor;
			green = green + greenFactor;
			blue = blue + blueFactor;

			c1 = new Color(cAux.getRGB() + (int) ((int) red * 0x010000)
					+ (int) ((int) green * 0x000100)
					+ (int) ((int) blue * 0x000001));
		}

		return degrade;
	}

}
