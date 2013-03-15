package gui;

import gui.tp0.Tp0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	private Panel panel = new Panel();

	private Menu menuBar = new Menu();

	public Window() {
		setTitle("TPS Analisis y Tratamiento de Imagenes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 250, 275);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 3 - getWidth() / 3, size.height / 3
				- getHeight() / 3);
		setResizable(false);
		this.setMinimumSize(new Dimension(600, 600));
		panel.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		add(panel);
	}

	public Panel getPanel() {
		return panel;
	}

	public void enableTools() {
		menuBar.getComponent(0).setEnabled(true);
		((Tp0) menuBar.getComponent(0)).saveImage.setEnabled(true);
	}

}
