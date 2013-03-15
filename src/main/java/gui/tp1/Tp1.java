package gui.tp1;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Tp1 extends JMenu{

	private static final long serialVersionUID = 1L;
	
	
	public Tp1(){
		super("TP 1");
		this.setEnabled(true);
		
		JMenuItem addition = new AddImagesItem(this);
	    
	    JMenuItem substraction = new SubstractImagesItem(this);
	    
	    JMenuItem multiplication = new MultiplyImagesItem(this);
	    
	    this.add(addition);
	    this.add(substraction);
	    this.add(multiplication);
	}
}
