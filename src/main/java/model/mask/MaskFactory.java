package model.mask;

public class MaskFactory {

	/**
	 * Builds a mean mask
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public static Mask buildMeanMask(int width, int height) {
		Mask mask = new Mask(width, height);
		double totalPixels = width * height;
		for (int i = -width / 2; i <= width / 2; i++) {
			for (int j = -height / 2; j <= height / 2; j++) {
				mask.setPixel(i, j, 1 / totalPixels);
			}
		}
		return mask;
	}

	/**
	 * Builds an edge enhancement mask
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public static Mask buildEdgeEnhancementMask(int width, int height) {
		Mask mask = new Mask(width, height);
		double pixelAmount = width * height;
		for (int i = -mask.getWidth() / 2; i <= mask.getWidth() / 2; i++) {
			for (int j = -mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
				mask.setPixel(i, j, -1);
			}
		}
		mask.setPixel(0, 0, (pixelAmount - 1));
		return mask;
	}
}
