package gui.tp1;

import gui.ExtensionFilter;
import gui.MessageFrame;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import model.Image;
import app.ImageLoader;

public abstract class ImageOperations extends JMenuItem {
	Tp1 tp1Menu;
	private static final long serialVersionUID = 1L;

	public ImageOperations(String s, final Tp1 t) {
		super(s);
		tp1Menu = t;

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				Panel panel = (((Window) t.getTopLevelAncestor()).getPanel());
				Image panelImage = panel.getImage();
				if (panelImage == null) {
					new MessageFrame("Debe cargarse una imagen antes");
					return;
				}
				FileFilter type = new ExtensionFilter("Imágenes", new String[] {
						".pgm", ".PGM", ".ppm", ".PPM", ".bmp", ".BMP" });
				chooser.addChoosableFileFilter(type);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(type);
				chooser.showOpenDialog(t);
				File arch = chooser.getSelectedFile();

				if (arch != null) {
					Image image = null;

					try {
						image = ImageLoader.loadImage(arch);
					} catch (Exception ex) {
						new MessageFrame("No se pudo cargar la imagen");
					}

					if (image.getHeight() != panelImage.getHeight()
							|| image.getWidth() != panelImage.getWidth()) {

						new MessageFrame(
								"Las imagenes deben ser del mismo tamaño");
						return;
					}
					try {
						doOperation(panelImage, image);
						panel.repaint();
					} catch (IllegalArgumentException i) {
						new MessageFrame(i.getMessage());
					}
				}

			}
		});
	}

	protected abstract void doOperation(Image img1, Image img2);

}
