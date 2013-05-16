package gui;

import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image workingImage = null;
	private Image image = null;
    private Image lastImg = null;
    private List<Point> mask;

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

        if(mask != null){
            for(Point p: mask){
                g.setColor(Color.RED);
                g.drawRect(p.x, p.y, 1, 1);
            }

        }
	}

	/**
	 * Loads an image to the panel
	 * 
	 * @param image
	 */
	public void loadImage(Image image) {
		this.workingImage = image.clone();
		this.image = image.clone();
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
		this.workingImage = image.clone();
		this.image = image.clone();
	}
	
	public void setWorkingImage(Image workingImage) {
		this.workingImage = workingImage.clone();
	}

    public void initKeyBindings() {
        String UNDO = "Undo action key";
        String REDO = "Redo action key";
        Action undoAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                setWorkingImage(getImage());
                mask = null;
                repaint();
            }
        };
        Action redoAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

            }
        };

        getActionMap().put(UNDO, undoAction);
        getActionMap().put(REDO, redoAction);

        InputMap[] inputMaps = new InputMap[] {
                getInputMap(JComponent.WHEN_FOCUSED),
                getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT),
                getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW),
        };
        for(InputMap i : inputMaps) {
            i.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), UNDO);
            i.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), REDO);
        }
    }

    public List<Point> getMask() {
        if(mask == null)
            return null;
        Point p1 = mask.get(0);
        Point p2 = mask.get(1);
        this.mask = new ArrayList<Point>();
        for(int y = Math.min(p1.y, p2.y); y <= Math.max(p1.y, p2.y); y++){
            for(int x = Math.min(p1.x, p2.x); x <= Math.max(p1.x, p2.x); x++){
                mask.add(new Point(x, y));
            }
        }
        return mask;
    }

    public void loadMask(List<Point> o) {
        this.mask = o;
    }
}
