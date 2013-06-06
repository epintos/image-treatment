package gui;

import gui.tp0.Tp0;
import gui.tp1.Tp1;
import gui.tp2.Tp2;
import gui.tp3.Tp3;
import gui.tp4.Tp4;

import javax.swing.*;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public Menu() {

		// Menu for TP0
		this.add(new Tp0());

		// Menu for TP1
		this.add(new Tp1());

		// Menu for TP2
		this.add(new Tp2());
		
		// Menu for TP3
		this.add(new Tp3());

        // Menu for TP3
        this.add(new Tp4());
	}

}
