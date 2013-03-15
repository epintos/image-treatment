package gui.tp1;

import model.Image;

public class MultiplyImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public MultiplyImagesItem(Tp1 t) {
		super("Multiplicar Imagenes", t);
	}

	@Override
	protected void doOperation(Image panelImage, Image image) {
		panelImage.multiply(image);

	}

}
