package model;

import java.awt.Point;

import model.borderDetector.BorderDetector;
import model.mask.Mask;

public interface Image {

	/**
	 * Color channels
	 */
	public static enum ColorChannel {
		RED, GREEN, BLUE
	}

	/**
	 * Image color type
	 */
	public static enum ImageType {
		RGB, GRAYSCALE
	}

	/**
	 * Supported image format
	 */
	public static enum ImageFormat {
		BMP, PGM, PPM, RAW
	}

	public static final int GRAY_LEVEL_AMOUNT = 256;

	/******************** For TP 0 and others ********************/

	/**
	 * Sets pixel in the correspoding RGB color channel
	 */
	public void setPixel(int x, int y, ColorChannel channel, double color);

	/**
	 * Sets pixel in the correspoding RGB color channel
	 */
	public void setRGBPixel(int x, int y, int rgb);

	/**
	 * Returns the RGB of a pixel in x,y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getRGBPixel(int x, int y);

	/**
	 * Returns the height of the image
	 * 
	 * @return
	 */
	public int getHeight();

	/**
	 * Returns the width of the image
	 * 
	 * @return
	 */
	public int getWidth();

	/**
	 * Returns the image color type
	 * 
	 * @return RGB or GRAYSCALE
	 */
	public ImageType getType();

	/**
	 * Returns the format of the image
	 * 
	 * @return BMP, PGM, PPM or RAW
	 */
	public ImageFormat getImageFormat();

	/******************** For TP 1 ********************/

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
	 * 
	 * @param scalar
	 */
	public void multiply(double scalar);

	public void dynamicRangeCompression();

	/**
	 * Applies the negative to hte image
	 */
	public void negative();

	/**
	 * Returns gray pixels for drawing an histogram
	 * 
	 * @return
	 */
	public double[] getHistogramPixels();

	/**
	 * Threshold effect using value as the transformation value
	 * 
	 * @param value
	 */
	public void threshold(double value);

	/**
	 * Equalizes the image
	 */
	public void equalize();

	/**
	 * Increase the contrast
	 * 
	 * @param r1
	 * @param r2
	 * @param y1
	 * @param y2
	 */
	public void contrast(double r1, double r2, double y1, double y2);

	/**
	 * Generates additive Gaussean noise
	 * @param standardDeviation
	 */
	public void gausseanNoise(double mean, double standardDeviation);

	/**
	 * Generates multiplicative Rayleigh noise
	 * @param mean
	 */
	public void rayleighNoise(double epsilon);

	/**
	 * Generates multiplicative Exponential noise
	 * @param mean
	 */
	public void exponentialNoise(double mean);

	/**
	 * Generates Impulsive noise (Salt and Pepper)
	 * @param po
	 * @param p1
	 */
	public void saltAndPepperNoise(double po, double p1);
	
	/**
	 * Applies a mask
	 * @param mask
	 */
	public void applyMask(Mask mask);
	
	/**
	 * Applies median mask
	 * @param point
	 */
	public void applyMedianMask(Point point);
	
	public Image clone();
	
	/******************** For TP 2 ********************/
	
	/**
	 * Anisotropic difussion with lambda = 0.25
	 * @param iterations
	 * @param bd
	 */
	public void applyAnisotropicDiffusion(int iterations, BorderDetector bd);

	public void applyRobertsBorderDetection(SynthesizationType st);
	
	public void applyPrewittBorderDetection(SynthesizationType st);
	
	public void applySobelBorderDetection(SynthesizationType st);
	
	public void synthesize(SynthesizationType st, Image ... chnls);

}
