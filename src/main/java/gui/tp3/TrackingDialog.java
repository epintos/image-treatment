package gui.tp3;

import gui.Panel;
import model.ColorImage;
import model.mask.MaskFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian@redmintlabs.com on 16/05/13 at 12:05
 */
public class TrackingDialog extends JDialog {
    List<Point> mask = new ArrayList<Point>();

    public TrackingDialog(final Panel panel) {
        setTitle("Tracking");
        setBounds(1, 1, 250, 130);
        Dimension size = getToolkit().getScreenSize();
        setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
        this.setResizable(false);
        setLayout(null);

        JPanel pan1 = new JPanel();
        pan1.setBounds(0, 0, 250, 40);


        JLabel msg = new JLabel("Clickea área a trakear...");
        msg.setSize(250, 40);

        final JButton okButton = new JButton("OK");
        okButton.setEnabled(false);
        okButton.setSize(250, 40);
        okButton.setBounds(0, 50, 250, 50);

        final MouseListener listener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();
                model.Image img = panel.getImage();
                if(x >= 0 && y >= 0 && x < img.getWidth() && y < img.getHeight()){

                    mask.add(new Point(x, y));
                    panel.loadMask(mask);
                    panel.repaint();
                    if(mask.size() == 2){
                        okButton.setEnabled(true);
                        panel.removeMouseListener(panel.getMouseListeners()[0]);
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        };


        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                model.Image aux = ((ColorImage)panel.getImage()).clone();
                aux.applyMask(MaskFactory.buildGaussianMask(5, 5));
                aux.tracking(panel.getMask());
                panel.repaint();
                panel.removeMouseListener(listener);
                dispose();
            }
        });

        panel.addMouseListener(listener);

        pan1.add(msg);
        this.add(pan1);
        this.add(okButton);
    }
}
