package gui.tp1;

import gui.MessageFrame;
import gui.Panel;

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

public class ContrastDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public ContrastDialog(final Panel panel) {

		setTitle("Contraste");
		setBounds(1, 1, 250, 170);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Valores originales"));
		pan1.setBounds(0, 0, 250, 50);

		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Valores nuevos"));
		pan2.setBounds(0, 50, 250, 50);

		JLabel r1Label = new JLabel("r1 = ");
		final JTextField r1Field = new JTextField("0");
		r1Field.setColumns(3);

		JLabel r2Label = new JLabel(", r2 = ");
		final JTextField r2Field = new JTextField("0");
		r2Field.setColumns(3);

		JLabel newR1Label = new JLabel("y1 = ");
		final JTextField y1Field = new JTextField("0");
		y1Field.setColumns(3);

		JLabel newR2Label = new JLabel("y2 = ");
		final JTextField y2Field = new JTextField("0");
		y2Field.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int r1;
				int r2;
				int y1;
				int y2;

				try {
					r1 = Integer.valueOf(r1Field.getText());
					r2 = Integer.valueOf(r2Field.getText());
					y1 = Integer.valueOf(y1Field.getText());
					y2 = Integer.valueOf(y2Field.getText());

				} catch (NumberFormatException ex) {
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}
				
				if(!(r1 < y1) || !(r2 < y2)){
					new MessageFrame("r1 debe ser menor a y1, y r2 menor a y2");
					return;
				}

				Image panelImage = panel.getWorkingImage();
				panelImage.contrast(r1, r2, y1, y2);
				panel.repaint();
				dispose();
			}
		});

		pan1.add(r1Label);
		pan1.add(r1Field);
		pan1.add(r2Label);
		pan1.add(r2Field);

		pan2.add(newR1Label);
		pan2.add(y1Field);
		pan2.add(newR2Label);
		pan2.add(y2Field);

		this.add(pan1);
		this.add(pan2);
		this.add(okButton);

	}

}
