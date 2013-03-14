package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Image;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image = null;

	/**
	 * Paints an image in the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image != null) {
			for (int i = 0; i < image.getWidth(); i++)
				for (int j = 0; j < image.getHeight(); j++) {
					g.setColor(new Color(image.getRGBPixel(i, j)));
					g.drawRect(i, j, 1, 1);
				}
			this.getTopLevelAncestor().setSize(image.getWidth() + 7,
					image.getHeight() + 53);
		}
	}

	/**
	 * Loads an image to the panel
	 * @param image
	 */
	public void loadImage(Image image) {
		this.image = image;
		((Window) getTopLevelAncestor()).enableTools();
	}

	public boolean setPixel(String xText, String yText, String colorText) {

		int x = 0;
		int y = 0;
		int color = 0;

		try {
			x = Integer.valueOf(xText);
			y = Integer.valueOf(yText);
			color = Integer.valueOf(colorText);
		} catch (NumberFormatException ex) {
			new MessageFrame("Los valores ingresados son incorrectos");
			return false;
		}

		// TODO: Create method to avoid this
		this.image.setPixel(x, y, Image.ColorChannel.RED, color);
		this.image.setPixel(x, y, Image.ColorChannel.GREEN, color);
		this.image.setPixel(x, y, Image.ColorChannel.BLUE, color);

		this.repaint();
		return true;
	}

	public Image getImage() {
		return image;
	}

}
