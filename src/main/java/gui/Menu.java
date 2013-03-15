package gui;

import gui.tp0.Tp0;
import gui.tp1.Tp1;

import javax.swing.JMenuBar;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public Menu() {
		
		//Menu for TP0
		this.add(new Tp0());
		
		//Menu for TP0
		this.add(new Tp1());
	}

}
