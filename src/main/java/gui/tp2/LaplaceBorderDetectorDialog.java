package gui.tp2;

import gui.MessageFrame;
import gui.Panel;
import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaplaceBorderDetectorDialog extends JDialog {

	private static final long serialVersionUID = 6556538252540069572L;

	final Panel panel;

	public LaplaceBorderDetectorDialog(final Panel panel, final String title) {
		this.panel = panel;
		setTitle(title);

		setBounds(1, 1, 450, 310);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel laplaceTypePanel = new JPanel();
		laplaceTypePanel.setBorder(BorderFactory.createTitledBorder("Tipo:"));
		laplaceTypePanel.setBounds(0, 0, 450, 100);

		final JRadioButton laplacianRadioButton = new JRadioButton(
				"Laplaciano", true);
//		final JRadioButton laplacianVarianceRadioButton = new JRadioButton(
//				"Laplaciano con evaluación local de la varianza");
		final JRadioButton laplacianGaussianRadioButton = new JRadioButton(
				"Laplaciano del Gaussiano (Marr-Hildreth)");
		final JCheckBox zeroCrossingCheckBox = new JCheckBox("Cruzar por cero");

		JPanel sigmaPanel = new JPanel();
		sigmaPanel.setBorder(BorderFactory.createTitledBorder("LoG:"));
		sigmaPanel.setBounds(0, 170, 450, 70);

		final JTextField sigma = new JTextField("1.0");
		final JTextField maskSize = new JTextField("7");

		JLabel maskSizeLaplaceGaussian = new JLabel("Máscara:");
		maskSize.setColumns(3);
		maskSize.setEnabled(false);

		JLabel sigmaLoG = new JLabel("Sigma:");
		sigma.setColumns(3);
		sigma.setEnabled(false);

		sigmaPanel.add(maskSizeLaplaceGaussian);
		sigmaPanel.add(maskSize);
		sigmaPanel.add(sigmaLoG);
		sigmaPanel.add(sigma);

		JPanel valuePanel = new JPanel();
		valuePanel.setBorder(BorderFactory.createTitledBorder("Umbrales:"));
		valuePanel.setBounds(0, 100, 450, 70);

		final JTextField zeroCrossingThreshold = new JTextField("40");
//		final JTextField varianceThreshold = new JTextField("10");

		JLabel zeroCrossingThresholdLabel = new JLabel("Cruces por cero (threshold)");
//		JLabel varianceThresholdLabel = new JLabel("Varianza:");

		zeroCrossingThreshold.setColumns(3);
		zeroCrossingThreshold.setEnabled(false);
//		varianceThreshold.setColumns(3);
//		varianceThreshold.setEnabled(false);

		valuePanel.add(zeroCrossingThresholdLabel);
		valuePanel.add(zeroCrossingThreshold);
//		valuePanel.add(varianceThresholdLabel);
//		valuePanel.add(varianceThreshold);

		laplacianRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				laplacianRadioButton.setSelected(true);
//				laplacianVarianceRadioButton.setSelected(!laplacianRadioButton
//						.isSelected());
				laplacianGaussianRadioButton.setSelected(!laplacianRadioButton
						.isSelected());
//				varianceThreshold.setEnabled(false);
				maskSize.setEnabled(false);
				sigma.setEnabled(false);
				zeroCrossingCheckBox.setEnabled(true);
			}
		});

//		laplacianVarianceRadioButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				laplacianVarianceRadioButton.setSelected(true);
//				laplacianRadioButton.setSelected(!laplacianVarianceRadioButton
//						.isSelected());
//				laplacianGaussianRadioButton
//						.setSelected(!laplacianVarianceRadioButton.isSelected());
//				varianceThreshold.setEnabled(true);
//				maskSize.setEnabled(false);
//				sigma.setEnabled(false);
//				zeroCrossingCheckBox.setSelected(false);
//				zeroCrossingCheckBox.setEnabled(false);
//				zeroCrossingThreshold.setEnabled(false);
//			}
//		});

		laplacianGaussianRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				laplacianGaussianRadioButton.setSelected(true);
				laplacianRadioButton.setSelected(!laplacianGaussianRadioButton
						.isSelected());
//				laplacianVarianceRadioButton
//						.setSelected(!laplacianGaussianRadioButton.isSelected());
//				varianceThreshold.setEnabled(false);
				maskSize.setEnabled(true);
				sigma.setEnabled(true);
				zeroCrossingCheckBox.setEnabled(true);
			}
		});

		zeroCrossingCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				zeroCrossingThreshold.setEnabled(zeroCrossingCheckBox
						.isSelected());
			}
		});

		JButton okButton = new JButton("OK");
		okButton.setBounds(100, 240, 250, 40);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Image panelImage = panel.getWorkingImage();
				if (laplacianRadioButton.isSelected()) {
					panelImage.applyLaplaceMask();
				} /*else if (laplacianVarianceRadioButton.isSelected()) {

					String varianceStr = varianceThreshold.getText();
					int variance = 0;
					try {
						variance = Integer.valueOf(varianceStr);
					} catch (NumberFormatException ex) {
						new MessageFrame("Por favor ingrese un número");
						return;
					}
					panelImage.applyLaplaceVarianceMask(variance);
				} */else if (laplacianGaussianRadioButton.isSelected()) {

					String maskSizeStr = maskSize.getText();
					String sigmaStr = sigma.getText();
					int maskSizeValue = 0;
					double sigmaValue = 0;
					try {
						maskSizeValue = Integer.valueOf(maskSizeStr);
						sigmaValue = Double.valueOf(sigmaStr);
					} catch (NumberFormatException ex) {
						new MessageFrame("Por favor ingrese un número");
						return;
					}

					panelImage.applyLaplaceGaussianMask(maskSizeValue,
							sigmaValue);
				} else {
					throw new IllegalStateException();
				}

				if (zeroCrossingCheckBox.isSelected()) {
					String threshholdStr = zeroCrossingThreshold.getText();
					int threshold = 0;
					try {
						threshold = Integer.valueOf(threshholdStr);
					} catch (NumberFormatException ex) {
						new MessageFrame("Por favor ingrese un número");
						return;
					}
					panelImage.applyZeroCrossing(threshold);
				}

				panel.repaint();
				dispose();
			}
		});

		laplaceTypePanel.add(laplacianRadioButton);
//		laplaceTypePanel.add(laplacianVarianceRadioButton);
		laplaceTypePanel.add(laplacianGaussianRadioButton);
		laplaceTypePanel.add(zeroCrossingCheckBox);

		this.add(laplaceTypePanel);
		this.add(valuePanel);
		this.add(sigmaPanel);
		this.add(okButton);

	}

}
