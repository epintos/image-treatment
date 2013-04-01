package gui.tp1;

import gui.MessageFrame;
import gui.Panel;
import gui.Window;
import gui.tp1.filters.EdgeEnhancementDialog;
import gui.tp1.filters.MeanFilterDialog;
import gui.tp1.filters.MedianFilterDialog;
import gui.tp1.noises.ExponentialNoiseDialog;
import gui.tp1.noises.GaussianNoiseDialog;
import gui.tp1.noises.RayleighNoiseDialog;
import gui.tp1.noises.SaltAndPepperNoiseDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import model.Image;
import app.ImageCreator;

public class Tp1 extends JMenu {

	private static final long serialVersionUID = 1L;

	public Tp1() {
		super("TP 1");
		this.setEnabled(true);

		JMenuItem addition = new AddImagesItem(this);

		JMenuItem substraction = new SubstractImagesItem(this);

		JMenuItem multiplication = new MultiplyImagesItem(this);

		JMenuItem multiplicationByScalar = new JMenuItem(
				"Multiplicar por escalar");
		multiplicationByScalar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog scalarMultiplier = new MultiplyByScalarDialog(panel);
					scalarMultiplier.setVisible(true);
				}
			}
		});

		JMenuItem dynamicRangeCompression = new JMenuItem(
				"Compresión de Rango Dinámico");
		dynamicRangeCompression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getWorkingImage().dynamicRangeCompression();
					panel.repaint();
				}
			}
		});

		JMenuItem negative = new JMenuItem("Negativo");
		negative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getWorkingImage().negative();
					panel.repaint();
				}
			}
		});

		JMenuItem grayLevelsHistogram = new JMenuItem("Histograma");
		grayLevelsHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog dialog = new HistogramDialog(panel);
					dialog.setVisible(true);
				}

			}
		});

		JMenuItem threshold = new JMenuItem("Umbral");
		threshold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog umbral = new ThresholdDialog(panel);
					umbral.setVisible(true);
				}
			}
		});

		JMenuItem equalize = new JMenuItem("Ecualizar");
		equalize.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					Image panelImage = panel.getWorkingImage();
					panelImage.equalize();
					panel.repaint();
				}
			}
		});

		JMenuItem contrast = new JMenuItem("Contraste");
		contrast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog contraste = new ContrastDialog(panel);
					contraste.setVisible(true);
				}
			}
		});

		JMenuItem sinteticWhiteImage = new JMenuItem("Imagen sintética 100x100");
		sinteticWhiteImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				Image img = ImageCreator.createWhiteImage(100, 100);

				panel.loadImage(img);
				panel.repaint();
			}
		});

		JMenuItem gaussianNoise = new JMenuItem("Ruido Gaussiano");
		gaussianNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog askPixel = new GaussianNoiseDialog(panel);
					askPixel.setVisible(true);
				}
			}
		});

		JMenuItem raileyghNoise = new JMenuItem("Ruido Rayleigh");
		raileyghNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog askPixel = new RayleighNoiseDialog(panel);
					askPixel.setVisible(true);
				}
			}
		});

		JMenuItem exponentialNoise = new JMenuItem("Ruido Exponencial");
		exponentialNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog askPixel = new ExponentialNoiseDialog(panel);
					askPixel.setVisible(true);
				}
			}
		});

		JMenuItem saltAndPepperNoise = new JMenuItem("Ruido Salt and pepper");
		saltAndPepperNoise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog askPixel = new SaltAndPepperNoiseDialog(panel);
					askPixel.setVisible(true);
				}
			}
		});

		JMenuItem meanFilter = new JMenuItem("Filtro de la media");
		meanFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog mediaDialog = new MeanFilterDialog(panel);
					mediaDialog.setVisible(true);
				}
			}
		});

		JMenuItem medianFilter = new JMenuItem("Filtro de la mediana");
		medianFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog medianDialog = new MedianFilterDialog(panel);
					medianDialog.setVisible(true);
				}
			}
		});

		JMenuItem edgeEnhancement = new JMenuItem("Realce de bordes");
		edgeEnhancement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog pasaAlto = new EdgeEnhancementDialog(panel);
					pasaAlto.setVisible(true);
				}
			}
		});

		this.add(addition);
		this.add(substraction);
		this.add(multiplication);
		this.add(multiplicationByScalar);
		this.add(dynamicRangeCompression);
		this.add(new JSeparator());
		this.add(negative);
		this.add(new JSeparator());
		this.add(grayLevelsHistogram);
		this.add(new JSeparator());
		this.add(contrast);
		this.add(new JSeparator());
		this.add(threshold);
		this.add(new JSeparator());
		this.add(equalize);
		this.add(new JSeparator());
		this.add(sinteticWhiteImage);
		this.add(new JSeparator());
		this.add(gaussianNoise);
		this.add(raileyghNoise);
		this.add(exponentialNoise);
		this.add(saltAndPepperNoise);
		this.add(new JSeparator());
		this.add(meanFilter);
		this.add(medianFilter);
		this.add(edgeEnhancement);
	}

	private boolean imageLoaded(Panel panel) {
		Image panelImage = panel.getWorkingImage();
		if (panelImage == null) {
			new MessageFrame("Debe cargarse una imagen antes");
			return false;
		}
		return true;
	}
}
