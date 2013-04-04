package gui.tp1;

import gui.Panel;
import model.Image;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				panel.repaint();
			}
		});
		
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
                panel.repaint();
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
