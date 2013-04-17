package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class PrewittBorderDetectorDialog extends AbstractBorderDetector {

	private static final long serialVersionUID = -2603478920964502803L;

	public PrewittBorderDetectorDialog(final Panel panel) {
		super(panel, "Detección de bordes Prewitt");
	}
	
	public void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getWorkingImage();
		panelImage.applyPrewittBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}
}
