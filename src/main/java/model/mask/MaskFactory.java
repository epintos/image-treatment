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

	/**
	 * Builds a Gaussian mask
	 * 
	 * @param width
	 * @param height
	 * @param sigma
	 * @return
	 */
	public static Mask buildGaussianMask(int size, double sigma) {
		Mask mask = new Mask(size);
		double total = 0;
		for (int i = -mask.getWidth() / 2; i <= mask.getWidth() / 2; i++) {
			for (int j = -mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
				double gaussianFunction = (1 / (2 * Math
						.pow(Math.PI * sigma, 2)))
						* Math.exp(-(Math.pow(i, 2) + Math.pow(j, 2)
								/ (Math.pow(sigma, 2))));
				total += gaussianFunction;
				mask.setPixel(i, j, gaussianFunction);
			}
		}
		for (int i = -mask.getWidth() / 2; i <= mask.getWidth() / 2; i++) {
			for (int j = -mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
				double oldPixel = mask.getValue(i, j);
				mask.setPixel(i, j, oldPixel / total);
			}
		}
		return mask;
	}

	public static TwoMaskContainer buildRobertsMasks() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);

		dx.setPixel(0, 0, 1);
		dx.setPixel(1, 1, -1);

		dy.setPixel(0, 1, 1);
		dy.setPixel(1, 0, -1);

		return new TwoMaskContainer(dx, dy);
	}

	public static TwoMaskContainer buildPrewittMasks() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);

		dx.setPixel(-1, -1, -1);
		dx.setPixel(-1, 0, -1);
		dx.setPixel(-1, 1, -1);
		dx.setPixel(1, -1, 1);
		dx.setPixel(1, 0, 1);
		dx.setPixel(1, 1, 1);

		dy.setPixel(-1, -1, -1);
		dy.setPixel(0, -1, -1);
		dy.setPixel(1, -1, -1);
		dy.setPixel(-1, 1, 1);
		dy.setPixel(0, 1, 1);
		dy.setPixel(1, 1, 1);

		return new TwoMaskContainer(dx, dy);
	}

	public static TwoMaskContainer buildSobelMasks() {
		int size = 3;
		Mask dx = new Mask(size);
		Mask dy = new Mask(size);

		dx.setPixel(-1, -1, -1);
		dx.setPixel(-1, 0, -2);
		dx.setPixel(-1, 1, -1);
		dx.setPixel(1, -1, 1);
		dx.setPixel(1, 0, 2);
		dx.setPixel(1, 1, 1);

		dy.setPixel(-1, -1, -1);
		dy.setPixel(0, -1, -2);
		dy.setPixel(1, -1, -1);
		dy.setPixel(-1, 1, 1);
		dy.setPixel(0, 1, 2);
		dy.setPixel(1, 1, 1);

		return new TwoMaskContainer(dx, dy);
	}
}
