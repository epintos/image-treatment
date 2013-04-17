package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class RobertsBorderDetectorDialog extends AbstractBorderDetector {

	private static final long serialVersionUID = -5661031286993034501L;

	public RobertsBorderDetectorDialog(final Panel panel) {
		super(panel, "Detección de bordes Roberts");
	}
	
	public void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getWorkingImage();
		panelImage.applyRobertsBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}
	
}
