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
	private List<Point> mask = null;

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
		if (mask != null) {
			for (Point p : mask) {
				g.setColor(Color.GREEN);
				g.drawRect(p.x, p.y, 1, 1);
			}

		}
	}

	public void loadImage(Image imagen) {
		this.image = imagen;
		((Window) getTopLevelAncestor()).enableTools();
	}

	public void loadMask(List<Point> mask) {
		this.mask = mask;
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

	public List<Point> getMask() {
		if (mask == null)
			return null;
		Point p1 = mask.get(0);
		Point p2 = mask.get(1);
		this.mask = new ArrayList<Point>();
		for (int y = Math.min(p1.y, p2.y); y <= Math.max(p1.y, p2.y); y++) {
			for (int x = Math.min(p1.x, p2.x); x <= Math.max(p1.x, p2.x); x++) {
				mask.add(new Point(x, y));
			}
		}
		return mask;
	}

}
