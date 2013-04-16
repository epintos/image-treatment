package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class AOperatorBorderDetectorDialog extends AbstractBorderDetector {

	private static final long serialVersionUID = -8076582376997221788L;

	public AOperatorBorderDetectorDialog(final Panel panel){
		super(panel, "Detector de bordes Operator A");
	}
	
	@Override
	void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getImage();
		panelImage.applyAOperatorBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}
}
