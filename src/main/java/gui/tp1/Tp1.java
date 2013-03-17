package gui.tp1;

import gui.MessageFrame;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import model.Image;

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
					panel.getImage().dynamicRangeCompression();
					panel.repaint();
				}
			}
		});

		JMenuItem negative = new JMenuItem("Negativo");
		negative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getImage().negative();
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
					Image panelImage = panel.getImage();
					panelImage.equalize();
					panel.repaint();
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
		this.add(threshold);
		this.add(new JSeparator());
		this.add(equalize);
	}

	private boolean imageLoaded(Panel panel) {
		Image panelImage = panel.getImage();
		if (panelImage == null) {
			new MessageFrame("Debe cargarse una imagen antes");
			return false;
		}
		return true;
	}
}
