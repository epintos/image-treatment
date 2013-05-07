package gui.tp3;

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

public class Tp3 extends JMenu {

	private static final long serialVersionUID = 1L;

	public Tp3() {
		super("TP 3");
		this.setEnabled(true);

		JMenuItem supressNoMaxs = new JMenuItem("Supresión de No Máximos");
		supressNoMaxs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getWorkingImage().suppressNoMaxs();
					panel.repaint();
				}
			}
		});

		JMenuItem thresholdWithHysteresis = new JMenuItem(
				"Umbral con histéresis");
		thresholdWithHysteresis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog thresholdWithHysteresisDialog = new ThresholdWithHysteresisDialog(
							panel);
					thresholdWithHysteresisDialog.setVisible(true);
				}
			}
		});

		JMenuItem canny = new JMenuItem("Canny");
		canny.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getWorkingImage().applyCannyBorderDetection();
					panel.repaint();
				}
			}
		});

		JMenuItem houghForLines = new JMenuItem("Hough para Líneas");
		houghForLines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getWorkingImage().houghTransformForLines();
					panel.repaint();
				}

			}
		});

		JMenuItem houghForCircles = new JMenuItem("Hough para círulos");
		houghForCircles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					panel.getWorkingImage().houghTransformForCircles();
					panel.repaint();
				}
			}
		});

		this.add(supressNoMaxs);
		this.add(new JSeparator());
		this.add(thresholdWithHysteresis);
		this.add(new JSeparator());
		this.add(canny);
		this.add(new JSeparator());
		this.add(houghForLines);
		this.add(houghForCircles);
		this.add(new JSeparator());

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
