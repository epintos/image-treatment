package gui.tp1;

import model.Image;

public class AddImagesItem extends ImageOperations {

	private static final long serialVersionUID = 1L;

	public AddImagesItem(Tp1 t) {
		super("Sumar Imagenes", t);
	}

	@Override
	protected void doOperation(Image panelImage, Image image) {
		panelImage.add(image);
	}

}
