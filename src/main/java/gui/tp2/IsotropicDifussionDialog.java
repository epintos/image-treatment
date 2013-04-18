package gui.tp2;

import gui.MessageFrame;
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
import model.borderDetector.BorderDetector;

public class IsotropicDifussionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public IsotropicDifussionDialog(final Panel panel) {
		setTitle("Difusión Isotrópica");
		setBounds(1, 1, 250, 120);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Parametros"));
		pan1.setBounds(0, 0, 250, 50);

		JLabel iterationsLabel = new JLabel("Iteraciones ");
		final JTextField iterationsField = new JTextField("7");
		iterationsField.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int iterations = 0;
				try {
					iterations = Integer.valueOf(iterationsField.getText());

				} catch (NumberFormatException ex) {
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}
				Image panelImage = panel.getWorkingImage();
				panelImage.applyAnisotropicDiffusion(iterations,
						new BorderDetector() {
							@Override
							public double g(double x) {
								return 1;
							}
						});
				panel.repaint();
				dispose();
			}
		});

		pan1.add(iterationsLabel);
		pan1.add(iterationsField);

		this.add(pan1);
		this.add(okButton);

	};

}
