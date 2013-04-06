package model.borderDetector;

public class LorentzBorderDetector implements BorderDetector {

	private double sigma;
	
	public LorentzBorderDetector(double sigma){
		this.sigma = sigma;
	}
	
	@Override
	public double g(double x) {
		return 1/((Math.pow(Math.abs(x), 2) / Math.pow(sigma, 2)) + 1);
	}

}
