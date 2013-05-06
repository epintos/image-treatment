package gui.tp3;

import gui.MessageFrame;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import model.Image;

public class Tp3 extends JMenu {

	private static final long serialVersionUID = 1L;

	public Tp3() {
		super("TP 3");
		this.setEnabled(true);

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
