import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;

public class Tp0 extends JMenu {

	private static final long serialVersionUID = 1L;

	public Tp0() {
		super("TP 0");
		this.setEnabled(true);
		JMenuItem loadImage = new JMenuItem("Cargar imagen");
		loadImage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser selector = new JFileChooser();
				selector.showOpenDialog(Tp0.this);
				File arch = selector.getSelectedFile();

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (arch != null) {
					Image image = null;

					try {
						image = ImageLoader.loadImage(arch);
					} catch (ImageReadException ex) {
						new MessageFrame("No se pudo cargar la imagen");
					} catch (IOException ex) {
						new MessageFrame("No se pudo cargar la imagen");
					}

					if (image != null) {
						panel.loadImage(image);
						panel.repaint();
					}

				}

			}
		});
		JMenuItem loadRaw = new JMenuItem("Cargar imagen Raw");
		loadRaw.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser selector = new JFileChooser();
				selector.showOpenDialog(Tp0.this);
				File arch = selector.getSelectedFile();

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (arch != null) {
					JDialog rawParams = new RawImageDialog(panel, arch);
					rawParams.setVisible(true);
				}
			}
		});
		JMenuItem saveImage = new JMenuItem("Guardar imagen");
		saveImage.setEnabled(false);
		saveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				selector.setApproveButtonText("Save");
				selector.showSaveDialog(Tp0.this);

				File arch = selector.getSelectedFile();

				if (arch != null) {
					Image image = (((Window) getTopLevelAncestor()).getPanel()
							.getImage());
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
		JMenuItem exit = new JMenuItem("Salir");
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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

		JMenuItem binaryImage = new JMenuItem("Imagen binaria");
		binaryImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());

				JDialog binaryImage = new CreateBinaryImageDialog(panel);

				binaryImage.setVisible(true);

			}
		});

		this.add(loadImage);
		this.add(loadRaw);
		this.add(saveImage);
		this.add(binaryImage);
		 this.add(degradeBW);
		this.add(degradeColor);
		this.add(new JSeparator());
		this.add(exit);

	}

}
