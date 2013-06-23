package gui.tp4;

import gui.ExtensionFilter;
import gui.MessageFrame;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import model.Image;
import mpi.cbg.fly.Feature;
import mpi.cbg.fly.SIFT;
import app.ImageLoader;

public class Tp4 extends JMenu {

    private static final long serialVersionUID = 1L;
    
    private final FileFilter commonTypes = new ExtensionFilter("Imágenes", new String[] {
			".pgm", ".PGM", ".ppm", ".PPM", ".bmp", ".BMP" });
    private final FileFilter raw = new ExtensionFilter("Imágenes RAW",
			new String[] { ".raw", ".RAW" });

    public Tp4() {
        super("TP 4");
        this.setEnabled(true);

//        JMenuItem rawSIFT = new JMenuItem("SIFT - Imagen RAW");
//        rawSIFT.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
//                if (imageLoaded(panel)) {
//                	Image secondaryImage = getSecondaryImage(raw);
//                    panel.getWorkingImage().detectFeatures(panel.getDrawingContainer());
//                    panel.repaint();
//                }
//            }
//        });
        
//        this.add(rawSIFT);
        
        JMenuItem sift = new JMenuItem("SIFT - Otro formato");
        sift.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                	Image secondaryImage = getSecondaryImage(commonTypes);
                	Vector<Feature> f1 = SIFT.getFeatures(panel.getWorkingImage().getBufferedImage());
                	Vector<Feature> f2 = SIFT.getFeatures(secondaryImage.getBufferedImage());
                	int hits = analyzeFeatures(f1, f2);
                	System.out.println(hits);
                    panel.getWorkingImage().detectFeatures(panel.getDrawingContainer());
                    panel.repaint();
                }
            }
        });
        
        this.add(sift);

    }

    
    protected int analyzeFeatures(Vector<Feature> f1, Vector<Feature> f2) {
//    	List<Feature> hits = new ArrayList<>();
    	int hits = 0;
    	for(Feature feature: f1) {
    		for(Feature inner: f2) {
    			if(feature.descriptorDistance(inner) < 0.1) {
    				hits++;
    			}
    		}
    	}
    	return hits;
	}

	private Image getSecondaryImage(FileFilter filter) {
    	JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(Tp4.this);

		File arch = chooser.getSelectedFile();

		if (arch != null) {
			Image image = null;

			try {
				image = ImageLoader.loadImage(arch);
			} catch (Exception ex) {
                ex.printStackTrace();
				new MessageFrame("No se pudo cargar la imagen");
			}
			
			return image;
		}
		
		return null;

	}

    private boolean imageLoaded(Panel panel) {
        Image panelImage = panel.getWorkingImage();
        if (panelImage == null) {
            new MessageFrame("Debe cargarse una imagen antes");
            return false;
        }
        return true;
    }
}
