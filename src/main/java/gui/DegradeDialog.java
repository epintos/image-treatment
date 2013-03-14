package gui;

import java.awt.Color;
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

import app.ImageCreator;

import model.Image;

public class DegradeDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public DegradeDialog(final Panel panel, final boolean isColor) {

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

		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Color"));
		pan2.setBounds(0, 50, 250, 50);

		JLabel altoLabel = new JLabel("Alto = ");
		final JTextField alto = new JTextField("0");
		alto.setColumns(3);

		JLabel anchoLabel = new JLabel(", Ancho = ");
		final JTextField ancho = new JTextField("0");
		ancho.setColumns(3);

		String fieldText = "0";
		if (isColor)
			fieldText = "000000";

		JLabel colorLabel1 = new JLabel("Desde:");
		final JTextField color1 = new JTextField(fieldText);
		color1.setColumns(5);

		JLabel colorLabel2 = new JLabel("Hasta:");
		final JTextField color2 = new JTextField(fieldText);
		color2.setColumns(5);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int height;
				int width;
				int c1;
				int c2;

				try {
					height = Integer.valueOf(alto.getText().trim());
					width = Integer.valueOf(ancho.getText().trim());
					if (isColor) {
						c1 = Integer.valueOf(color1.getText().trim(), 16);
						c2 = Integer.valueOf(color2.getText().trim(), 16);
					} else {
						c1 = Integer.valueOf(color1.getText().trim());
						c2 = Integer.valueOf(color2.getText().trim());
						c1 = new Color(c1, c1, c1).getRGB();
						c2 = new Color(c2, c2, c2).getRGB();
					}

				} catch (NumberFormatException ex) {
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}

				if (height <= 0 || width <= 0) {
					new MessageFrame("La imagen debe tener al menos tamaño 1x1");
					return;
				}

				Image img = ImageCreator.createDegrade(isColor, height, width,
						c1, c2);

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

		pan2.add(colorLabel1);
		pan2.add(color1);
		pan2.add(colorLabel2);
		pan2.add(color2);

		this.add(pan1);
		this.add(pan2);
		this.add(okButton);

	};

}
