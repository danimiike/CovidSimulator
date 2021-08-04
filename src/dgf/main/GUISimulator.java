package dgf.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GUISimulator extends  JFrame {

//    private List<> = new ArrayList<>();

    public GUISimulator(){
        super("Epidemic Transmission Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(5, 5));



        this.setVisible(true);
    } // end ctor

} // end class
