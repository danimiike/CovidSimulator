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

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 500;
    private final int IMG_DIAMETER = 10;

    public MainMenu() {
        super("Covid-19 Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
        this.sldrPopulation = this.setSliderConfig(this.lblStatusPopulation, 5000, 100, 500);
        this.sldrNotVaccinated = this.setSliderConfig(this.lblStatusNotVaccinated, 100, 5, 10);
        this.sldrOneShot = this.setSliderConfig(this.lblStatusOneShot, 100, 5, 10);
        this.sldrTwoShot = this.setSliderConfig(this.lblStatusTwoShot, 100, 5, 10);
        this.sldrImmunity = this.setSliderConfig(this.lblStatusImmunity, 100, 5, 10);


        this.pnlPopulation = new JPanel();
        this.pnlPopulation.setLayout(new GridLayout(1, 3));
        this.pnlPopulation.add(this.lblPopulation);
        this.pnlPopulation.add(this.sldrPopulation);
        this.pnlPopulation.add(this.lblStatusPopulation);

        this.pnlImmunityStatus = new JPanel(new GridLayout(4, 3));
        this.pnlImmunityStatus.setBorder(new TitledBorder("Levels of Immunity - The total should be 100%"));
        this.pnlImmunityStatus.add(this.lblNotVaccinated);
        this.pnlImmunityStatus.add(this.sldrNotVaccinated);
        this.pnlImmunityStatus.add(this.lblStatusNotVaccinated);

        this.pnlImmunityStatus.add(this.lblOneShot);
        this.pnlImmunityStatus.add(this.sldrOneShot);
        this.pnlImmunityStatus.add(this.lblStatusOneShot);

        this.pnlImmunityStatus.add(this.lblTwoShot);
        this.pnlImmunityStatus.add(this.sldrTwoShot);
        this.pnlImmunityStatus.add(this.lblStatusTwoShot);

        this.pnlImmunityStatus.add(this.lblImmunity);
        this.pnlImmunityStatus.add(this.sldrImmunity);
        this.pnlImmunityStatus.add(this.lblStatusImmunity);


        this.pnlMain = new JPanel();
        this.pnlMain.setLayout(new BorderLayout(0, 20));
        this.pnlMain.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.pnlMain.add(this.pnlPopulation, BorderLayout.NORTH);
        this.pnlMain.add(this.pnlImmunityStatus, BorderLayout.CENTER);

        // TODO: Decide later if we going to continue with these two empty panel. Only aesthetics.
        this.panel3 = new JPanel();
        this.panel4 = new JPanel();

        this.pnlBtnStartSimulation = new JPanel();
        this.btnStartSimulation = new JButton("Start Simulation");
        this.btnStartSimulation.setFont(font);

        ActionListener jbtListener = new MyJButtonListener();
        this.btnStartSimulation.addActionListener(jbtListener);

        this.pnlBtnStartSimulation.add(this.btnStartSimulation);

        this.add(this.pnlTitle, BorderLayout.NORTH);
        this.add(this.pnlMain, BorderLayout.CENTER);
        this.add(this.panel3, BorderLayout.WEST);
        this.add(this.panel4, BorderLayout.EAST);
        this.add(this.pnlBtnStartSimulation, BorderLayout.SOUTH);

        this.setVisible(true);
    }//end constructor

    private class MyJButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList<Person> personList = new ArrayList<>();

            int pop = sldrPopulation.getValue();
            int countPop = pop;
            // TODO: Check the total value for the count. their sum need to be equals
            int popNotVaccinated = sldrNotVaccinated.getValue() * pop / 100;
            int popOneShot = sldrOneShot.getValue() * pop / 100;
            int popTwoShot = sldrTwoShot.getValue() * pop / 100;
            int popImmunity = sldrImmunity.getValue() * pop / 100;

            Person infected = new Person(true, true, ImmunityStatus.Status.NO_SHOT,
                    new Ball(IMG_DIAMETER, Color.RED, WINDOW_WIDTH, WINDOW_HEIGHT));
            personList.add(infected);
            for (int i = 0; i < pop; i++) {
                if (popNotVaccinated-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.BLUE, WINDOW_WIDTH, WINDOW_HEIGHT);
                    Person person = new Person(true, false, ImmunityStatus.Status.NO_SHOT, ballPerson);
                    personList.add(person);
                } else if (popOneShot-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.CYAN, WINDOW_WIDTH, WINDOW_HEIGHT);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (popTwoShot-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.YELLOW, WINDOW_WIDTH, WINDOW_HEIGHT);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (popImmunity-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.GREEN, WINDOW_WIDTH, WINDOW_HEIGHT);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (countPop-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.BLUE, WINDOW_WIDTH, WINDOW_HEIGHT);
                    Person person = new Person(true, false, ImmunityStatus.Status.NO_SHOT, ballPerson);
                    personList.add(person);
                } // end
                countPop--;
            } // end for loop

//            System.out.println(pop);
//            System.out.println(popNotVaccinated);
//            System.out.println(countOneShot);
//            System.out.println(countTwoShot);
//            System.out.println(countImmunity);
//            System.out.println(popNotVaccinated + countOneShot + countTwoShot + countImmunity);


            GUIRunner objRunenr = new GUIRunner(new GUISimulator(personList));
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
