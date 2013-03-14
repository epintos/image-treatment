

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image = null;
	private List<Coord> mask = null;
	
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		 
		if(image != null){
			for(int i = 0; i < image.getWidth(); i++)
				for(int j = 0; j < image.getHeight(); j++){
					g.setColor(new Color(image.getRGBPixel(i, j)));
					g.drawRect(i, j, 1, 1);
				}
			this.getTopLevelAncestor().setSize( image.getWidth()+7, image.getHeight()+53);
		}
		if(mask != null){
			for(Point p: mask){
				g.setColor(Color.GREEN);
				g.drawRect(p.x, p.y, 1, 1);
			}
				
		}
	}
	
	public void loadImage(Image imagen) {
		this.image = imagen;
		((Window) getTopLevelAncestor()).enableTools();
	}
	
	public void loadMask(List<Coord> mask) {
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
		} catch (NumberFormatException ex){
			new MessageFrame("Los valores ingresados son incorrectos");
			return false;
		}
		
		//TODO: Create method to avoid this 
		this.image.setPixel(x, y, Image.Channel.RED, color);
		this.image.setPixel(x, y, Image.Channel.GREEN, color);
		this.image.setPixel(x, y, Image.Channel.BLUE, color);
		
		this.repaint();
		return true;
	}
	
	public Image getImage(){
		return image;
	}
	
	public List<Coord> getMask(){
		if(mask == null)
			return null;
		Coord p1 = mask.get(0);
		Coord p2 = mask.get(1);
		this.mask = new ArrayList<Coord>();
		for(int y = Math.min(p1.y, p2.y); y <= Math.max(p1.y, p2.y); y++){
			for(int x = Math.min(p1.x, p2.x); x <= Math.max(p1.x, p2.x); x++){
				mask.add(new Coord(x, y));
			}
		}
		return mask;
	}
	
}
