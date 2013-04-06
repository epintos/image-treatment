package gui.tp2;

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

public class Tp2 extends JMenu {

	private static final long serialVersionUID = 1L;

	public Tp2() {
		super("TP 2");
		this.setEnabled(true);

		JMenuItem anisotropicDiffusion = new JMenuItem("Difusión Anisotrópica");
		anisotropicDiffusion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog anisotropicDifussionDialog = new AnisotropicDifussionDialog(
							panel);
					anisotropicDifussionDialog.setVisible(true);
				}
			}
		});
		this.add(anisotropicDiffusion);
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
