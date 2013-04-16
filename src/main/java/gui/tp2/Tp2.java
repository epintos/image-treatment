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

		JMenuItem gaussianFilter = new JMenuItem("Filtro Gaussiano");
		gaussianFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog mediaDialog = new GaussianFilter(panel);
					mediaDialog.setVisible(true);
				}
			}
		});

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

		JMenuItem roberts = new JMenuItem("Detección de bordes Roberts");
		roberts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog robertsBorderDetectorDialog = new RobertsBorderDetectorDialog(
							panel);
					robertsBorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem prewitt = new JMenuItem("Detección de bordes Prewitt");
		prewitt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog prewittBorderDetectorDialog = new PrewittBorderDetectorDialog(
							panel);
					prewittBorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem sobel = new JMenuItem("Detección de bordes Sobel");
		sobel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog sobelBorderDetectorDialog = new SobelBorderDetectorDialog(
							panel);
					sobelBorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem aOperator = new JMenuItem("Detector de bordes Operador A");
		aOperator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog maskABorderDetectorDialog = new AOperatorBorderDetectorDialog(
							panel);
					maskABorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem kirshOperator = new JMenuItem("Detector de bordes Kirsh");
		kirshOperator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog maskBKirshBorderDetectorDialog = new KirshOperatorBorderDetectorDialog(
							panel);
					maskBKirshBorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem cOperator = new JMenuItem("Detector de bordes Operador C");
		cOperator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog maskCBorderDetectorDialog = new COperatorBorderDetectorDialog(
							panel);
					maskCBorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem dOperator = new JMenuItem("Detector de bordes Operador D");
		dOperator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog maskDBorderDetectorDialog = new DOperatorBorderDetectorDialog(
							panel);
					maskDBorderDetectorDialog.setVisible(true);
				}
			}
		});

		JMenuItem laplacian = new JMenuItem("Laplaciano");
		laplacian.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel panel = (((Window) getTopLevelAncestor()).getPanel());
				if (imageLoaded(panel)) {
					JDialog laplaceBorderDetectorDialog = new LaplaceBorderDetectorDialog(
							panel, "Laplaciano");
					laplaceBorderDetectorDialog.setVisible(true);
				}
			}
		});

		this.add(gaussianFilter);
		this.add(new JSeparator());
		this.add(anisotropicDiffusion);
		this.add(new JSeparator());
		this.add(roberts);
		this.add(prewitt);
		this.add(sobel);
		this.add(new JSeparator());
		this.add(aOperator);
		this.add(kirshOperator);
		this.add(cOperator);
		this.add(dOperator);
		this.add(new JSeparator());
		this.add(laplacian);
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
