package gui.tp3;

import gui.Panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Image;

public class SusanBorderDetectorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public SusanBorderDetectorDialog(final Panel panel){
		setTitle("Susan");
		setBounds(1, 1, 250, 170);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Detectar bordes"));
		pan1.setBounds(0, 0, 250, 50);

		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Detectar esquinas"));
		pan2.setBounds(0, 50, 250, 50);

		JLabel coordLabel1 = new JLabel("Bordes = ");
		final JCheckBox borderDetectorCheckBox = new JCheckBox();

		JLabel colorLabel = new JLabel("Esquinas = ");
		final JCheckBox cornerDetectorCheckBox = new JCheckBox();

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				boolean borderDetector = borderDetectorCheckBox.isSelected();
				boolean cornerDetector = cornerDetectorCheckBox.isSelected();

				Image panelImage = panel.getWorkingImage();
				panelImage.applySusanMask(borderDetector, cornerDetector);
				panel.repaint();
				dispose();
			}
		});

		pan1.add(coordLabel1);
		pan1.add(borderDetectorCheckBox);

		pan2.add(colorLabel);
		pan2.add(cornerDetectorCheckBox);

		this.add(pan1);
		this.add(pan2);
		this.add(okButton);

	};

}
