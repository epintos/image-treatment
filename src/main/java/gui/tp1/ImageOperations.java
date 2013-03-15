package gui.tp1;

import gui.MessageFrame;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.Image;

import org.apache.sanselan.ImageReadException;

import app.ImageLoader;

public abstract class ImageOperations extends JMenuItem {
	Tp1 tp1Menu;
	private static final long serialVersionUID = 1L;

	public ImageOperations(String s, final Tp1 t){
		super(s);
		tp1Menu = t;
		
		this.addActionListener(new ActionListener() {
	
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		JFileChooser selector = new JFileChooser();
	    		selector.showOpenDialog(t);
				File arch = selector.getSelectedFile();
				
				Panel panel = (((Window) t.getTopLevelAncestor()).getPanel());
				if(arch != null){
					Image image = null;
					
					try{
						image = ImageLoader.loadImage(arch);
					} catch (ImageReadException ex){
						new MessageFrame("No se pudo cargar la imagen");
					} catch (IOException ex){
						new MessageFrame("No se pudo cargar la imagen");
					}
					if(image.getHeight() != panel.getImage().getHeight()
						|| image.getWidth() != panel.getImage().getWidth()) {
	
			    		new MessageFrame("Las imagenes deben ser del mismo tamaño");
						return;
					}
					try {
						doOperation(panel.getImage(), image);	 			    		
			    		panel.repaint();
			    	} 
					catch(IllegalArgumentException i){			    		
			    		new MessageFrame(i.getMessage());			    		
			    	}
				}
				
			}
		});
	}
	
	protected abstract void doOperation(Image img1, Image img2);

}
