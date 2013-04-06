package model.borderDetector;


public class LeclercBorderDetector implements BorderDetector {

	private double sigma;
	
	public LeclercBorderDetector(double sigma){
		this.sigma = sigma;
	}
	
	@Override
	public double g(double x) {
		return Math.exp(-Math.pow(Math.abs(x), 2) / Math.pow(sigma, 2));
	}

}
