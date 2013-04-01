package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Image;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image workingImage = null;
	private Image image = null;

	/**
	 * Paints an image in the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (workingImage != null) {
			for (int i = 0; i < workingImage.getWidth(); i++)
				for (int j = 0; j < workingImage.getHeight(); j++) {
					g.setColor(new Color(workingImage.getRGBPixel(i, j)));
					g.drawRect(i, j, 1, 1);
				}
			this.getTopLevelAncestor().setSize(workingImage.getWidth() + 7,
					workingImage.getHeight() + 53);
		}
	}

	/**
	 * Loads an image to the panel
	 * 
	 * @param image
	 */
	public void loadImage(Image image) {
		this.workingImage = image;
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

		setAllPixels(x, y, color);

		this.repaint();
		return true;
	}

	private void setAllPixels(int x, int y, double color) {
		this.workingImage.setPixel(x, y, Image.ColorChannel.RED, color);
		this.workingImage.setPixel(x, y, Image.ColorChannel.GREEN, color);
		this.workingImage.setPixel(x, y, Image.ColorChannel.BLUE, color);
	}

	public Image getWorkingImage() {
		return workingImage;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.workingImage = image;
		this.image = image;
	}
	
	public void setWorkingImage(Image workingImage) {
		this.workingImage = workingImage;
	}

}
