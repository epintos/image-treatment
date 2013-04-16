package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class DOperatorBorderDetectorDialog extends AbstractBorderDetector {

	private static final long serialVersionUID = 1050009870293338100L;

	public DOperatorBorderDetectorDialog(final Panel panel){
		super(panel, "Detector de bordes Operador D");
	}
	
	@Override
	void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getImage();
		panelImage.applyDOperatorBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}

}
