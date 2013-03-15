package gui.tp1;

import model.Image;

public class MultiplyImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public MultiplyImagesItem(Tp1 t) {
		super("Multiplicar Imagenes", t);
	}

	@Override
	protected void doOperation(Image img1, Image img2) {
		img1.multiply(img2);

	}

}
