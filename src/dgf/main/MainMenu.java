/**
 * Program Name:        dgf.main.MainMenu.java
 * Purpose:	            this is a class to create a Gui component to receive user inputs
 * Coder:	            Danielle Menezes de Mello Miike
 * Felipe Lopes Leite
 * Georgia Pattern
 * Date:                Jul 31, 2021
 */

package dgf.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;


public class MainMenu extends JFrame {

    private JSlider sldrPopulation, sldrNotVaccinated, sldrOneShot, sldrTwoShot, sldrImmunity;
    private JLabel lblPopulation, lblNotVaccinated, lblOneShot, lblTwoShot, lblImmunity;
    private JLabel lblStatusPopulation, lblStatusNotVaccinated, lblStatusOneShot, lblStatusTwoShot, lblStatusImmunity;
    private JPanel pnlTitle, pnlMain, pnlPopulation, pnlImmunityStatus, panel3, panel4, pnlBtnStartSimulation;
    //   private int qtyPerson, popTwoShot, popOneShot, popNoShot, popNatImmunity;
    JButton btnStartSimulation;

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
        this.pnlTitle = new JPanel();
        this.pnlTitle.add(welcomeLbl);

        // Initialize labels
        this.lblPopulation = new JLabel("Size of the population");
        this.lblNotVaccinated = new JLabel("People not Vaccinated");
        this.lblOneShot = new JLabel("People with one shot");
        this.lblTwoShot = new JLabel("People with two shots");
        this.lblImmunity = new JLabel("People with natural immunity");
        this.lblStatusPopulation = new JLabel("Choose a value: ", JLabel.CENTER);
        this.lblStatusNotVaccinated = new JLabel("Choose a value: ", JLabel.CENTER);
        this.lblStatusOneShot = new JLabel("Choose a value: ", JLabel.CENTER);
        this.lblStatusTwoShot = new JLabel("Choose a value: ", JLabel.CENTER);
        this.lblStatusImmunity = new JLabel("Choose a value: ", JLabel.CENTER);


        // initialize sliders
        sldrPopulation = this.setSliderConfig(this.lblStatusPopulation, 5000, 100, 500);
        sldrNotVaccinated = this.setSliderConfig(this.lblStatusNotVaccinated, 100, 5, 10);
        sldrOneShot = this.setSliderConfig(this.lblStatusOneShot, 100, 5, 10);
        sldrTwoShot = this.setSliderConfig(this.lblStatusTwoShot, 100, 5, 10);
        sldrImmunity = this.setSliderConfig(this.lblStatusImmunity, 100, 5, 10);


        pnlPopulation = new JPanel();
        pnlPopulation.setLayout(new GridLayout(1, 3));
        pnlPopulation.add(this.lblPopulation);
        pnlPopulation.add(this.sldrPopulation);
        pnlPopulation.add(this.lblStatusPopulation);

        pnlImmunityStatus = new JPanel(new GridLayout(4, 3));
        pnlImmunityStatus.setBorder(new TitledBorder("Levels of Immunity - The total should be 100%"));
        pnlImmunityStatus.add(this.lblNotVaccinated);
        pnlImmunityStatus.add(this.sldrNotVaccinated);
        pnlImmunityStatus.add(this.lblStatusNotVaccinated);

        pnlImmunityStatus.add(this.lblOneShot);
        pnlImmunityStatus.add(this.sldrOneShot);
        pnlImmunityStatus.add(this.lblStatusOneShot);

        pnlImmunityStatus.add(this.lblTwoShot);
        pnlImmunityStatus.add(this.sldrTwoShot);
        pnlImmunityStatus.add(this.lblStatusTwoShot);

        pnlImmunityStatus.add(this.lblImmunity);
        pnlImmunityStatus.add(this.sldrImmunity);
        pnlImmunityStatus.add(this.lblStatusImmunity);


        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout(0, 20));
        pnlMain.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        pnlMain.add(this.pnlPopulation, BorderLayout.NORTH);
        pnlMain.add(this.pnlImmunityStatus, BorderLayout.CENTER);

        // TODO: Decide later if we going to continue with these two empty panel. Only aesthetics.
        panel3 = new JPanel();
        panel4 = new JPanel();

        pnlBtnStartSimulation = new JPanel();
        btnStartSimulation = new JButton("Start Simulation");
        btnStartSimulation.setFont(font);

        ActionListener jbtListener = new MyJButtonListener();
        btnStartSimulation.addActionListener(jbtListener);

        pnlBtnStartSimulation.add(this.btnStartSimulation);

        this.add(this.pnlTitle, BorderLayout.NORTH);
        this.add(this.pnlMain, BorderLayout.CENTER);
        this.add(this.panel3, BorderLayout.WEST);
        this.add(this.panel4, BorderLayout.EAST);
        this.add(this.pnlBtnStartSimulation, BorderLayout.SOUTH);

        this.setVisible(true);
    }//end constructor

    private class MyJButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList<Person> arrayList = new ArrayList<>();

            int pop = sldrPopulation.getValue();
            // TODO: Check the total value for the count. their sum need to be equals
            int countNotVaccinated =sldrNotVaccinated.getValue() * pop / 100;
            int countOneShot = sldrOneShot.getValue() * pop / 100;
            int countTwoShot = sldrTwoShot.getValue() * pop / 100;
            int countImmunity = sldrImmunity.getValue() * pop / 100;

//            System.out.println(pop);
//            System.out.println(countNotVaccinated);
//            System.out.println(countOneShot);
//            System.out.println(countTwoShot);
//            System.out.println(countImmunity);
//            System.out.println(countNotVaccinated + countOneShot + countTwoShot + countImmunity);

            Person one = new Person(true, true, 10, new Ball(10, Color.RED,800,500));
            Person two = new Person(true, true, 10, new Ball(10, Color.BLUE,800,500));
            arrayList.add(one);
            arrayList.add(two);
            
//            JFrame frame = new JFrame("Epidemic Transmission Simulation");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    		frame.setLayout(new FlowLayout() );//ANONYMOUS object
//    		frame.setSize(800,500);
//    		frame.setLocationRelativeTo(null);
//    		frame.getContentPane().setBackground(Color.lightGray);
//            
            new GUISimulator(arrayList);
//            frame.setVisible(true);	
        }
    }

    private JSlider setSliderConfig(JLabel statusLabel, int max, int minorSpace, int majorSpace) {
//        statusLabel = new JLabel("Choose a value: ", JLabel.CENTER);
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, max, 0);
        slider.setMinorTickSpacing(minorSpace);
        slider.setMajorTickSpacing(majorSpace);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setFont(sliderFont);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                statusLabel.setText("Value : " + ((JSlider) e.getSource()).getValue());
            }
        });
        return slider;
    }

    public static void main(String[] args) {
        new MainMenu();
        
    }//end main
} // end class
