package model.mask;

/**
 * Util for creating differentes masks
 * 
 */
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
	 * @param sigma
	 * @return
	 */
	public static Mask buildGaussianMask(int size, double sigma) {
        if (size % 2 == 0) {
            size++;
        }
		Mask mask = new Mask(size);
		double total = 0;
		for (int i = -mask.getWidth() / 2; i <= mask.getWidth() / 2; i++) {
			for (int j = -mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
				double gaussianFunction = (1 / (2 * Math.PI * Math
						.pow(sigma, 2)))
						* Math.exp(-((Math.pow(i, 2) + Math.pow(j, 2))
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

	/**
	 * Robert's Mask | 1 0 | | 0 1 | | 0 -1 | |-1 0 |
	 * 
	 * @return
	 */
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

	/**
	 * Prewitt's Mask |-1 -1 -1 | |-1 0 1 | | 0 0 0 | |-1 0 1 | | 1 1 1 | |-1 0
	 * 1 |
	 * 
	 * @return
	 */
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

	/**
	 * Sobel's Mask |-1 -2 -1 | |-1 0 1 | | 0 0 0 | |-2 0 2 | | 1 2 1 | |-1 0 1
	 * |
	 * 
	 * @return
	 */
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

	/**
	 * Matrix A | 1 1 1 | | 1 -2 1 | |-1 -1 -1 |
	 * 
	 * @return
	 */
	public static FourMaskContainer buildMaskA() {
		Mask mask0 = buildMaskA0();
		Mask mask45 = MaskRotator.rotate45(mask0);
		Mask mask90 = MaskRotator.rotate90(mask0);
		Mask mask135 = MaskRotator.rotate135(mask0);

		return new FourMaskContainer(mask0, mask45, mask90, mask135);
	}

	private static Mask buildMaskA0() {
		Mask mask = new Mask(3);

		mask.setPixel(-1, -1, 1);
		mask.setPixel(-1, 0, 1);
		mask.setPixel(-1, 1, 1);

		mask.setPixel(0, -1, 1);
		mask.setPixel(0, 0, -2);
		mask.setPixel(0, 1, 1);

		mask.setPixel(1, -1, -1);
		mask.setPixel(1, 0, -1);
		mask.setPixel(1, 1, -1);

		return mask;
	}

	/**
	 * Kirsh's Matrix B | 5 5 5 | |-3 0 -3 | |-3 -3 -3 |
	 * 
	 * @return
	 */
	public static FourMaskContainer buildMaskBKirsh() {
		Mask mask0 = buildMaskBKirsh0();
		Mask mask45 = MaskRotator.rotate45(mask0);
		Mask mask90 = MaskRotator.rotate90(mask0);
		Mask mask135 = MaskRotator.rotate135(mask0);

		return new FourMaskContainer(mask0, mask45, mask90, mask135);
	}

	private static Mask buildMaskBKirsh0() {
		Mask mask = new Mask(3);

		mask.setPixel(-1, -1, 5);
		mask.setPixel(-1, 0, 5);
		mask.setPixel(-1, 1, 5);

		mask.setPixel(0, -1, -3);
		mask.setPixel(0, 0, 0);
		mask.setPixel(0, 1, -3);

		mask.setPixel(1, -1, -3);
		mask.setPixel(1, 0, -3);
		mask.setPixel(1, 1, -3);

		return mask;
	}

	/**
	 * Matrix C | 1 1 1 | | 0 0 0 | |-1 -1 -1 |
	 * 
	 * @return
	 */
	public static FourMaskContainer buildMaskC() {
		Mask mask0 = buildMaskC0();
		Mask mask45 = MaskRotator.rotate45(mask0);
		Mask mask90 = MaskRotator.rotate90(mask0);
		Mask mask135 = MaskRotator.rotate135(mask0);

		return new FourMaskContainer(mask0, mask45, mask90, mask135);
	}

	private static Mask buildMaskC0() {
		Mask mask = new Mask(3);

		mask.setPixel(-1, -1, 1);
		mask.setPixel(-1, 0, 1);
		mask.setPixel(-1, 1, 1);

		mask.setPixel(0, -1, 0);
		mask.setPixel(0, 0, 0);
		mask.setPixel(0, 1, 0);

		mask.setPixel(1, -1, -1);
		mask.setPixel(1, 0, -1);
		mask.setPixel(1, 1, -1);

		return mask;
	}

	/**
	 * Matrix D | 1 2 1 | | 0 0 0 | |-1 -2 -1 |
	 * 
	 * @return
	 */
	public static FourMaskContainer buildMaskD() {
		Mask mask0 = buildMaskD0();
		Mask mask45 = MaskRotator.rotate45(mask0);
		Mask mask90 = MaskRotator.rotate90(mask0);
		Mask mask135 = MaskRotator.rotate135(mask0);

		return new FourMaskContainer(mask0, mask45, mask90, mask135);
	}

	private static Mask buildMaskD0() {
		Mask mask = new Mask(3);

		mask.setPixel(-1, -1, 1);
		mask.setPixel(-1, 0, 2);
		mask.setPixel(-1, 1, 1);

		mask.setPixel(0, -1, 0);
		mask.setPixel(0, 0, 0);
		mask.setPixel(0, 1, 0);

		mask.setPixel(1, -1, -1);
		mask.setPixel(1, 0, -2);
		mask.setPixel(1, 1, -1);

		return mask;
	}

	/**
	 * Laplace Mask
	 *  |  0 -1  0 |
	 *  | -1  4 -1 | 
	 *  |  0 -1  0 |
	 * 
	 * @return
	 */
	public static Mask buildLaplaceMask() {
		Mask mask = new Mask(3);

		mask.setPixel(-1, -1, 0);
		mask.setPixel(-1, 0, -1);
		mask.setPixel(-1, 1, 0);

		mask.setPixel(0, -1, -1);
		mask.setPixel(0, 0, 4);
		mask.setPixel(0, 1, -1);

		mask.setPixel(1, -1, 0);
		mask.setPixel(1, 0, -1);
		mask.setPixel(1, 1, 0);

		return mask;
	}

	public static Mask buildLaplaceGaussianMask(int size, double sigma) {

        if (size % 2 == 0) {
            size++;
        }

		Mask mask = new Mask(size);

		for (int i = -mask.getWidth() / 2; i <= mask.getWidth() / 2; i++) {
			for (int j = -mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
				//1/(sqrt(2*pi)*sigma^3)
				double factor = Math.pow(
						Math.sqrt(2 * Math.PI) * Math.pow(sigma, 3), -1);
				//e^(-(x^2+y^2)/(2*sigma^2))
				double exp = -(Math.pow(i, 2) + Math.pow(j, 2))
						/ (2 * Math.pow(sigma, 2));
				//2-((x^2+y^2)/(sigma^2))
				double term = 2 - (Math.pow(i, 2) + Math.pow(j, 2))
						/ Math.pow(sigma, 2);
				double pixelValue = -1 * factor * term * Math.pow(Math.E, exp);
				mask.setPixel(i, j, pixelValue);
			}
		}
		return mask;
	}

}
