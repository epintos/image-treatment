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

import model.mask.MaskFactory;

public class IsotropicDifussionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public IsotropicDifussionDialog(final Panel panel){
		setTitle("Difusión Isotrópica");
		setBounds(1, 1, 250, 170);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Tamaño mascara"));
		pan1.setBounds(0, 0, 250, 50);

		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Sigma"));
		pan2.setBounds(0, 50, 250, 50);

		JLabel coordLabel1 = new JLabel("Tamaño = ");
		final JTextField coordX = new JTextField("7");
		coordX.setColumns(3);

		JLabel colorLabel = new JLabel("S= ");
		final JTextField sigma = new JTextField("1");
		sigma.setColumns(3);
		sigma.setAlignmentX(LEFT_ALIGNMENT);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int x = 0;
				double s = 0;
				try{
					x = Integer.valueOf(coordX.getText());
					s = Double.valueOf(sigma.getText());

				} catch(NumberFormatException ex){
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}
				panel.getImage().applyMask(MaskFactory.buildGaussianMask(x, s));
				panel.repaint();
				dispose();
			}
		});

		pan1.add(coordLabel1);
		pan1.add(coordX);

		pan2.add(colorLabel);
		pan2.add(sigma);

		this.add(pan1);
		this.add(pan2);
		this.add(okButton);

	};

}
