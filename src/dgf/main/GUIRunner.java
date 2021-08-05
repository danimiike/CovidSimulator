package dgf.main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUIRunner extends JFrame {

    private JMenu menu;
    private JMenuItem elemMenu;
    private JPanel pnlGui;

    public GUIRunner(GUISimulator guiObj) {
        super("Epidemic Transmission Simulation");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(new BorderLayout());//ANONYMOUS object
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);

        this.menu = new JMenu();
        this.elemMenu = new JMenuItem("Option");
        this.pnlGui = new JPanel();

        this.menu.add(this.elemMenu);
        this.pnlGui.add(guiObj);


//        this.add(this.menu, BorderLayout.NORTH);
        this.add(this.pnlGui, BorderLayout.CENTER);
		this.setVisible(true);
    }
}
