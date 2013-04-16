package gui.tp2;

import gui.Panel;
import model.Image;
import model.SynthesizationType;

public class SobelBorderDetectorDialog extends AbstractBorderDetector {

	private static final long serialVersionUID = 6499905393490803277L;

	public SobelBorderDetectorDialog(final Panel panel) {
		super(panel, "Detección de bordes Sobel");
	}

	public void applyFunction(SynthesizationType synthesizationType) {
		Image panelImage = panel.getImage();
		panelImage.applySobelBorderDetection(synthesizationType);
		panel.repaint();
		dispose();
	}
}
