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

	public HarrisCornerDetectorDialog(final Panel panel){
		setTitle("Harris");
		setBounds(1, 1, 250, 125);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Detectar esquinas"));
		pan1.setBounds(0, 0, 250, 60);


		JLabel coordLabel1 = new JLabel("Sigma ");
		final JTextField  threshold = new JTextField("0.5");
		threshold.setColumns(3);


		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 60, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

				Image panelImage = panel.getWorkingImage();
				panelImage.applyHarrisCornerDetector(3, Double.valueOf(threshold.getText()));
				panel.repaint();
				dispose();
			}
		});

		pan1.add(coordLabel1);
		pan1.add(threshold);

		this.add(pan1);
		this.add(okButton);

	};

}
