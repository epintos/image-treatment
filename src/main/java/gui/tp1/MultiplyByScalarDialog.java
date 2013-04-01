package gui.tp1;

import gui.MessageFrame;
import gui.Panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Image;

public class MultiplyByScalarDialog extends JDialog {

	private static final long serialVersionUID = -5340277548569963815L;

	public MultiplyByScalarDialog(final Panel panel) {
		setTitle("Multiplicación por escalar");
		setBounds(1, 1, 250, 120);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, 250, 50);

		JLabel umbralLabel = new JLabel("Escalar = ");
		final JTextField umbral = new JTextField("0");
		umbral.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double scalar;

				try {
					scalar = Double.valueOf(umbral.getText());
				} catch (NumberFormatException ex) {
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}
				Image panelImage = panel.getWorkingImage();

				panelImage.multiply(scalar);
				panel.repaint();
				dispose();
			}
		});

		pan1.add(umbralLabel);
		pan1.add(umbral);

		this.add(pan1);
		this.add(okButton);
	}

}
