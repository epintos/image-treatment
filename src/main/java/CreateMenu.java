
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class CreateMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public CreateMenu() {
		super("TP 0");
		this.setEnabled(true);

		// JMenuItem degradeBW = new JMenuItem("Degrade de grises");
		// degradeBW.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// Panel panel = (((Window) getTopLevelAncestor()).getPanel());
		//
		// JDialog degrade = new DegradeDialog(panel, false);
		//
		// degrade.setVisible(true);
		//
		// }
		// });
		//
		// JMenuItem degradeColor = new JMenuItem("Degrade de colores");
		// degradeColor.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// Panel panel = (((Window) getTopLevelAncestor()).getPanel());
		//
		// JDialog degrade = new DegradeDialog(panel, true);
		//
		// degrade.setVisible(true);
		//
		// }
		// });

		JMenuItem binaryImage = new JMenuItem("Imagen binaria");
		binaryImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Panel panel = (((Window) getTopLevelAncestor()).getPanel());

				JDialog binaryImage = new CreateBinaryImageDialog(panel);

				binaryImage.setVisible(true);

			}
		});

		// this.add(degradeColor);
		// this.add(degradeBW);
		this.add(binaryImage);

		this.add(new JSeparator());

	}

}
