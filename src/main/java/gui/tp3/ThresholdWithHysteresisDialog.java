package gui.tp3;

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

public class ThresholdWithHysteresisDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public ThresholdWithHysteresisDialog(final Panel panel){
		setTitle("Umbral con histéresis");
		setBounds(1, 1, 250, 220);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Umbral inferior"));
		pan1.setBounds(0, 0, 250, 70);

		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Umbral superior"));
		pan2.setBounds(0, 70, 250, 70);

		JLabel coordLabel1 = new JLabel("Valor ");
		final JTextField lowerThresholdTextField = new JTextField("");
		lowerThresholdTextField.setColumns(3);

		JLabel colorLabel = new JLabel("Valor ");
		final JTextField higherThresholdTextField = new JTextField("");
		higherThresholdTextField.setColumns(3);
		higherThresholdTextField.setAlignmentX(LEFT_ALIGNMENT);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 140, 250, 60);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				double lowerThreshold = 0;
				double higherThreshold = 0;
				try{
					lowerThreshold = Double.valueOf(lowerThresholdTextField.getText());
					higherThreshold = Double.valueOf(higherThresholdTextField.getText());
				} catch(NumberFormatException ex){
					new MessageFrame("Los datos ingresados son invalidos");
					return;
				}
				
				Image panelImage = panel.getWorkingImage();
				panelImage.thresholdWithHysteresis(lowerThreshold, higherThreshold);
				panel.repaint();
				dispose();
			}
		});

		pan1.add(coordLabel1);
		pan1.add(lowerThresholdTextField);

		pan2.add(colorLabel);
		pan2.add(higherThresholdTextField);

		this.add(pan1);
		this.add(pan2);
		this.add(okButton);

	};

}
