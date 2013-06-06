package gui.tp4;

import gui.MessageFrame;
import gui.Panel;
import gui.Window;
import model.Image;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tp4 extends JMenu {

    private static final long serialVersionUID = 1L;

    public Tp4() {
        super("TP 4");
        this.setEnabled(true);

        JMenuItem supressNoMaxs = new JMenuItem("Detectar Features");
        supressNoMaxs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel panel = (((Window) getTopLevelAncestor()).getPanel());
                if (imageLoaded(panel)) {
                    panel.getWorkingImage().detectFeatures(panel.getDrawingContainer());
                    panel.repaint();
                }
            }
        });

        this.add(supressNoMaxs);

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
