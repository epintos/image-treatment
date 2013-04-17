package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class KirshOperatorBorderDetectorDialog extends AbstractBorderDetector {
	
	private static final long serialVersionUID = 6604093729930411155L;

	public KirshOperatorBorderDetectorDialog(final Panel panel){
		super(panel, "Detector de bordes Kirsh");
	}
	
	@Override
	void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getWorkingImage();
		panelImage.applyKirshBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}
}
