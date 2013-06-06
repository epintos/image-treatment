package gui.tp0;

import app.ImageCreator;
import app.ImageLoader;
import app.ImageSaver;
import gui.ExtensionFilter;
import gui.MessageFrame;
import gui.Panel;
import gui.Window;
import model.Image;
import org.apache.sanselan.ImageWriteException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Tp0 extends JMenu {

	public JMenuItem saveImage = new JMenuItem("Guardar imagen");

	private static final long serialVersionUID = 1L;

	public Tp0() {
		super("TP 0");
		this.setEnabled(true);
		JMenuItem loadImage = new JMenuItem("Cargar imagen");
		loadImage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				FileFilter type = new ExtensionFilter("Imágenes", new String[] {
						".pgm", ".PGM", ".ppm", ".PPM", ".bmp", ".BMP" });
				chooser.addChoosableFileFilter(type);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(type);
				chooser.showOpenDialog(Tp0.this);

				File arch = chooser.getSelectedFile();

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (arch != null) {
					Image image = null;

					try {
						image = ImageLoader.loadImage(arch);
					} catch (Exception ex) {
                        ex.printStackTrace();
						new MessageFrame("No se pudo cargar la imagen");
					}

					if (image != null) {
						// Loads the image to the panel
						panel.loadImage(image);

						// This will repaint the panel with the previous image
						// loaded
						panel.repaint();
					}

				}

			}
		});
		JMenuItem loadRaw = new JMenuItem("Cargar imagen Raw");
		loadRaw.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				FileFilter type = new ExtensionFilter("Imágenes RAW",
						new String[] { ".raw", ".RAW" });
				chooser.addChoosableFileFilter(type);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(type);
				chooser.showOpenDialog(Tp0.this);
				File arch = chooser.getSelectedFile();

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (arch != null) {
					JDialog rawParams = new RawImageDialog(panel, arch);
					rawParams.setVisible(true);
				}
			}
		});

		saveImage.setEnabled(false);
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				selector.setApproveButtonText("Save");
				selector.showSaveDialog(Tp0.this);

				File arch = selector.getSelectedFile();

				if (arch != null) {
					Image image = (((Window) getTopLevelAncestor()).getPanel()
							.getWorkingImage());
					try {
						ImageSaver.saveImage(arch, image);
					} catch (ImageWriteException ex) {
						new MessageFrame("No se pudo guardar la imagen");
					} catch (IOException ex) {
						new MessageFrame("No se pudo guardar la imagen");
					}
				}
			}
		});

		JMenuItem binaryImage = new JMenuItem("Imagen binaria");
		binaryImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());

				JDialog binaryImage = new CreateBinaryImageDialog(panel);

				binaryImage.setVisible(true);

			}
		});

		JMenuItem circleBinaryImage = new JMenuItem("Imagen binaria círculo");
		circleBinaryImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());

				Image img = ImageCreator.circle(300, 300);

				if (img != null) {
					panel.loadImage(img);
					panel.repaint();
				}

			}
		});

		JMenuItem degradeBW = new JMenuItem("Degrade de grises");
		degradeBW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());

				JDialog degrade = new DegradeDialog(panel, false);

				degrade.setVisible(true);

			}
		});

		JMenuItem degradeColor = new JMenuItem("Degrade de colores");
		degradeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());

				JDialog degrade = new DegradeDialog(panel, true);

				degrade.setVisible(true);

			}
		});

		this.add(loadImage);
		this.add(loadRaw);
		this.add(saveImage);
		this.add(new JSeparator());
		this.add(binaryImage);
		this.add(circleBinaryImage);
		this.add(degradeBW);
		this.add(degradeColor);
	}

}
