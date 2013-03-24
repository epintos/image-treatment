package gui.tp1.noises;

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

public class ExponentialNoiseDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public ExponentialNoiseDialog(final Panel panel){
		
		setTitle("Ruido exponencial");
		setBounds(1, 1, 250, 120);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel paramPanel = new JPanel();
		paramPanel.setBorder(BorderFactory.createTitledBorder("Parámetros"));
		paramPanel.setBounds(0, 0, 250, 50);

		JLabel meanLabel = new JLabel("Media = ");
		final JTextField meanTextField = new JTextField("");
		meanTextField.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				double mean = 0;
				try {
					mean = Double.valueOf(meanTextField.getText());
				} catch (NumberFormatException ex){
					new MessageFrame("Los valores ingresados son incorrectos");
					return;
				}
				Image panelImage = panel.getImage(); 
	    		panelImage.exponentialNoise(mean);
	    		panel.repaint();
	    		dispose();
			}
		});
		
		paramPanel.add(meanLabel);
		paramPanel.add(meanTextField);

		this.add(paramPanel);
		this.add(okButton);
	};

}
