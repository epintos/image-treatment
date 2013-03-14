
import java.awt.Color;

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

}
