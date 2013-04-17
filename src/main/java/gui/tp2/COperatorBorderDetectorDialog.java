package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class COperatorBorderDetectorDialog extends AbstractBorderDetector {

	private static final long serialVersionUID = -6540462641356385299L;

	public COperatorBorderDetectorDialog(final Panel panel){
		super(panel, "Detector de bordes Operador C");
	}
	
	@Override
	void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getWorkingImage();
		panelImage.applyCOperatorBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}

}
