package dgf.main; /**
 * Program Name:        dgf.main.MainMenu.java
 * Purpose:	            this is a class to create a Gui component to receive user inputs
 * Coder:	            Danielle Menezes de Mello Miike
 * Felipe Lopes Leite
 * Georgia Pattern
 * Date:                Jul 31, 2021
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;


public class MainMenu extends JFrame {
    private JSlider s1, s2, s3, s4, s5;
    private JLabel L1, L2, L3, L4, L5;
    private JLabel s1StatusLabel, s2StatusLabel, s3StatusLabel, s4StatusLabel, s5StatusLabel;
    private JPanel panel1, panel2, panel2_0, panel2_1, panel3, panel4, panel5;
    private int qtyPerson, popTwoShot, popOneShot, popNoShot, popNatImmunity;
    private JButton B;

    Font font = new Font("Times New Roman", Font.PLAIN, 20);
    Font sliderFont = new Font("Times New Roman", Font.PLAIN, 8);

    Container contentPane;

    public MainMenu() {
        super("Covid-19 Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(5, 5));

        JLabel welcomeLbl = new JLabel("Choose your simulation scenario:");
        welcomeLbl.setFont(font);
        panel1 = new JPanel();
        panel1.add(welcomeLbl);


        L1 = new JLabel("Size of the population");
        L2 = new JLabel("People no vaccinated");
        L3 = new JLabel("People with one shot");
        L4 = new JLabel("People with two shots");
        L5 = new JLabel("People with natural immunity");


        s1StatusLabel = new JLabel("Choose a value", JLabel.CENTER);
        s1 = new JSlider(SwingConstants.HORIZONTAL, 0, 5000, 0);
        s1.setMinorTickSpacing(100);
        s1.setMajorTickSpacing(500);
        s1.setPaintLabels(true);
        s1.setPaintTicks(true);
        s1.setFont(sliderFont);
        s1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                s1StatusLabel.setText("Value : " + ((JSlider) e.getSource()).getValue());
            }
        });

        s2StatusLabel = new JLabel("Choose a value", JLabel.CENTER);
        s2 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        s2.setMinorTickSpacing(5);
        s2.setMajorTickSpacing(10);
        s2.setPaintLabels(true);
        s2.setPaintTicks(true);
        s2.setFont(sliderFont);
        s2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                s2StatusLabel.setText("Value : " + ((JSlider) e.getSource()).getValue());
            }
        });

        s3StatusLabel = new JLabel("Choose a value", JLabel.CENTER);
        s3 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        s3.setMinorTickSpacing(5);
        s3.setMajorTickSpacing(10);
        s3.setPaintLabels(true);
        s3.setPaintTicks(true);
        s3.setFont(sliderFont);
        s3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                s3StatusLabel.setText("Value : " + ((JSlider) e.getSource()).getValue());
            }
        });

        s4StatusLabel = new JLabel("Choose a value", JLabel.CENTER);
        s4 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        s4.setMinorTickSpacing(5);
        s4.setMajorTickSpacing(10);
        s4.setPaintLabels(true);
        s4.setPaintTicks(true);
        s4.setFont(sliderFont);
        s4.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                s4StatusLabel.setText("Value : " + ((JSlider) e.getSource()).getValue());
            }
        });

        s5StatusLabel = new JLabel("Choose a value", JLabel.CENTER);
        s5 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        s5.setMinorTickSpacing(5);
        s5.setMajorTickSpacing(10);
        s5.setPaintLabels(true);
        s5.setPaintTicks(true);
        s5.setFont(sliderFont);
        s5.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                s5StatusLabel.setText("Value : " + ((JSlider) e.getSource()).getValue());
            }
        });

        panel2_0 = new JPanel();
        panel2_0.setLayout(new GridLayout(1, 3));
        panel2_0.add(L1);
        panel2_0.add(s1);
        panel2_0.add(s1StatusLabel);

        panel2_1 = new JPanel(new GridLayout(4, 3));
        panel2_1.setBorder(new TitledBorder("Levels of Immunity - The total should be 100%"));
        panel2_1.add(L2);
        panel2_1.add(s2);
        panel2_1.add(s2StatusLabel);
        panel2_1.add(L3);
        panel2_1.add(s3);
        panel2_1.add(s3StatusLabel);
        panel2_1.add(L4);
        panel2_1.add(s4);
        panel2_1.add(s4StatusLabel);
        panel2_1.add(L5);
        panel2_1.add(s5);
        panel2_1.add(s5StatusLabel);


        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 20));
        panel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel2.add(panel2_0, BorderLayout.NORTH);
        panel2.add(panel2_1, BorderLayout.CENTER);

        panel3 = new JPanel();
        panel4 = new JPanel();

        panel5 = new JPanel();
        B = new JButton("Start Simulation");
        B.setFont(font);

        ActionListener jbtListener = new MyJButtonListener();
        B.addActionListener(jbtListener);

        panel5.add(B);

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.WEST);
        this.add(panel4, BorderLayout.EAST);
        this.add(panel5, BorderLayout.SOUTH);

        this.setVisible(true);
    }//end constructor


    private class MyJButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            qtyPerson = s1.getValue();
            popNoShot = s2.getValue();
            popOneShot = s3.getValue();
            popTwoShot = s4.getValue();
            popNatImmunity = s5.getValue();

            //	try
            //	{	ver como iremos tratar isso, se for menor que 100 vamos assumir que o resto nao esta vacinado, por exemplo?
            if (popNoShot + popOneShot + popTwoShot + popNatImmunity > 100) {
                JOptionPane.showInternalMessageDialog(contentPane, "The sum of levels of immunity should be 100 or less.");
            }


        }
    } //end class


    public static void main(String[] args) {

        new MainMenu();
    }//end main


}
