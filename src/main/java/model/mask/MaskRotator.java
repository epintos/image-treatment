package model.mask;

/**
 * Util that rotates a mask of size 3x3
 *
 */
public class MaskRotator {

	/**
	 * Rotates a mask 45 degrees
	 * 
	 * @param mask
	 * @return
	 */
	public static Mask rotate45(Mask mask) {
		if (!mask.isSquared() || mask.getHeight() != 3) {
			throw new IllegalArgumentException();
		}

		Mask result = new Mask(mask.getHeight());

		result.setPixel(-1, -1, mask.getValue(-1, 0));
		result.setPixel(-1, 0, mask.getValue(-1, 1));
		result.setPixel(-1, 1, mask.getValue(0, 1));

		result.setPixel(0, -1, mask.getValue(-1, -1));
		result.setPixel(0, 0, mask.getValue(0, 0));
		result.setPixel(0, 1, mask.getValue(1, 1));

		result.setPixel(1, -1, mask.getValue(0, -1));
		result.setPixel(1, 0, mask.getValue(1, -1));
		result.setPixel(1, 1, mask.getValue(1, 0));

		return result;
	}

	/**
	 * Rotates a mask 90 degrees
	 * 
	 * @param mask
	 * @return
	 */
	public static Mask rotate90(Mask mask) {
		if (!mask.isSquared() || mask.getHeight() != 3) {
			throw new IllegalArgumentException();
		}

		return rotate45(rotate45(mask));
	}

	/**
	 * Rotates a mask 135 degrees
	 * 
	 * @param mask
	 * @return
	 */
	public static Mask rotate135(Mask mask) {
		if (!mask.isSquared() || mask.getHeight() != 3) {
			throw new IllegalArgumentException();
		}

		return rotate45(rotate45(rotate45(mask)));
	}

}
