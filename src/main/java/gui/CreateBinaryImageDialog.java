package gui;

import app.ImageCreator;
import model.Image;

public class CreateBinaryImageDialog extends CreateImageDialog {

	private static final long serialVersionUID = 1L;

	public CreateBinaryImageDialog(Panel panel) {
		super(panel);
	}

	@Override
	protected Image createBinaryImage(int height, int width) {
		return ImageCreator.createBinaryImage(height, width);
	}

}
