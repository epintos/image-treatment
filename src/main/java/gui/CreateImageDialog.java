package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Image;

public abstract class CreateImageDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public CreateImageDialog(final Panel panel) {

		setTitle("Crear degrade");
		setBounds(1, 1, 250, 200);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Tamaño"));
		pan1.setBounds(0, 0, 250, 50);

		JLabel altoLabel = new JLabel("Alto = ");
		final JTextField alto = new JTextField("300");
		alto.setColumns(3);

		JLabel anchoLabel = new JLabel(", Ancho = ");
		final JTextField ancho = new JTextField("300");
		ancho.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int height;
				int width;

				try {
					height = Integer.valueOf(alto.getText().trim());
					width = Integer.valueOf(ancho.getText().trim());
				} catch (NumberFormatException ex) {
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}

				if (height <= 0 || width <= 0) {
					new MessageFrame("La imagen debe tener al menos tamaño 1x1");
					return;
				}

				Image img = createBinaryImage(height, width);

				if (img != null) {
					panel.loadImage(img);
					panel.repaint();
					dispose();
				} else {
					new MessageFrame("Valores ingresados incorrectos.");
					return;
				}

			}

		});

		pan1.add(altoLabel);
		pan1.add(alto);

		pan1.add(anchoLabel);
		pan1.add(ancho);

		this.add(pan1);
		this.add(okButton);

	};

	protected abstract Image createBinaryImage(int height, int width);
}
