package gui.tp4;

import gui.Panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Image;

public class HarrisCornerDetectorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public HarrisCornerDetectorDialog(final Panel panel) {
		setTitle("Harris");
		setBounds(1, 1, 350, 160);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Detectar esquinas"));
		pan1.setBounds(0, 0, 350, 90);

		JLabel maskSizeLabel = new JLabel("Mask Size ");
		final JTextField maskSize = new JTextField("3");
		maskSize.setColumns(3);

		JLabel sigmaLabel = new JLabel("Sigma");
		final JTextField sigma = new JTextField("0.25");
		sigma.setColumns(3);

		JLabel kLabel = new JLabel("k");
		final JTextField k = new JTextField("0.06");
		k.setColumns(3);

		JLabel thresholdLabel = new JLabel("Threshold");
		final JTextField threshold = new JTextField("70");
		threshold.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 90, 350, 40);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Image panelImage = panel.getWorkingImage();
				panelImage.applyHarrisCornerDetector(
						Integer.parseInt(maskSize.getText()),
						Double.valueOf(sigma.getText()),
						Double.valueOf(threshold.getText()),
						Double.valueOf(k.getText()));
				panel.repaint();
				dispose();
			}
		});

		pan1.add(maskSizeLabel);
		pan1.add(maskSize);

		pan1.add(sigmaLabel);
		pan1.add(sigma);

		pan1.add(kLabel);
		pan1.add(k);

		pan1.add(thresholdLabel);
		pan1.add(threshold);

		this.add(pan1);
		this.add(okButton);

	};

}
