package gui.tp1;

import model.Image;

public class SubstractImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public SubstractImagesItem(Tp1 t) {
		super("Restar Imagenes", t);
	}

	@Override
	protected void doOperation(Image img1, Image img2) {
		img1.substract(img2);

	}

}
