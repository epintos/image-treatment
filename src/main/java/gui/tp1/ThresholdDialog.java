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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Image;

public class ThresholdDialog extends JDialog implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private final Panel panel;

	public ThresholdDialog(final Panel panel) {
		this.panel = panel;
		setTitle("Umbral");
		setBounds(1, 1, 250, 120);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, 250, 50);

		JLabel umbralLabel = new JLabel("Valor transformación");

		JSlider range = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		range.addChangeListener(this);
		range.setMajorTickSpacing(10);
		range.setMinorTickSpacing(1);
		range.setPaintTicks(true);
		range.setPaintLabels(true);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setImage(panel.getWorkingImage());
				dispose();
			}
		});

		pan1.add(umbralLabel);
		pan1.add(range);

		this.add(pan1);
		this.add(okButton);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        int value = (int)source.getValue();
	        Image panelImage = panel.getImage();
	        Image modify = (Image) panelImage.clone();
			modify.threshold(value);
			panel.setWorkingImage(modify);
			panel.repaint();
	    }

	}

}
