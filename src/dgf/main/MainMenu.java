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

    // TODO: Add more attributes for the simulator panel. These will track all the number of infected person
    private JSlider sldrPopulation, sldrNotVaccinated, sldrOneShot, sldrTwoShot, sldrImmunity;
    private JLabel lblMainTitle;
    private JLabel lblPopulation, lblNotVaccinated, lblOneShot, lblTwoShot, lblImmunity;
    private JLabel lblStatusPopulation, lblStatusNotVaccinated, lblStatusOneShot, lblStatusTwoShot, lblStatusImmunity;
    private JPanel pnlTitle, pnlMain, pnlPopulation, pnlImmunityStatus, pnlBtnStartSimulation;
    //   private int qtyPerson, popTwoShot, popOneShot, popNoShot, popNatImmunity;
    JButton btnStartSimulation;
    Font font = new Font("Times New Roman", Font.PLAIN, 20);
    Font sliderFont = new Font("Times New Roman", Font.PLAIN, 8);

    private final int WINDOW_WIDTH_MAIN = 800;
    private final int WINDOW_HEIGHT_MAIN = 500;
    private final int IMG_DIAMETER = 10;
    private static int cycleCounter = 0;
    private static final int CYCLE_COUNTER_LIMITY = 450;
    private static final int CYCLE_INFECTED_LIMITY = 150;

    public MainMenu() {
        super("Covid-19 Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(5, 5));


        // Initialize the panels
        this.pnlTitle = new JPanel();
        this.pnlPopulation = new JPanel();
        this.pnlPopulation.setLayout(new GridLayout(1, 3));
        this.pnlImmunityStatus = new JPanel(new GridLayout(4, 3));
        this.pnlImmunityStatus.setBorder(new TitledBorder("Levels of Immunity - The total should be 100%"));
        this.pnlMain = new JPanel();
        this.pnlMain.setLayout(new BorderLayout(0, 20));
        this.pnlMain.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.pnlBtnStartSimulation = new JPanel();

        // Initialize labels
        this.lblMainTitle = new JLabel("Choose your simulation scenario:");
        this.lblMainTitle.setFont(font);
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

        // Initialize buttons
        this.btnStartSimulation = new JButton("Start Simulation");
        this.btnStartSimulation.setFont(font);
        this.btnStartSimulation.addActionListener(new OptionListener());

        // Populate the panels
        this.pnlTitle.add(this.lblMainTitle);
        this.pnlPopulation.add(this.lblPopulation);
        this.pnlPopulation.add(this.sldrPopulation);
        this.pnlPopulation.add(this.lblStatusPopulation);

        // immunity status panel
        // not vaccinated slider
        this.pnlImmunityStatus.add(this.lblNotVaccinated);
        this.pnlImmunityStatus.add(this.sldrNotVaccinated);
        this.pnlImmunityStatus.add(this.lblStatusNotVaccinated);
        // one shot slider
        this.pnlImmunityStatus.add(this.lblOneShot);
        this.pnlImmunityStatus.add(this.sldrOneShot);
        this.pnlImmunityStatus.add(this.lblStatusOneShot);
        // two shot slider
        this.pnlImmunityStatus.add(this.lblTwoShot);
        this.pnlImmunityStatus.add(this.sldrTwoShot);
        this.pnlImmunityStatus.add(this.lblStatusTwoShot);
        // natural immunity slider
        this.pnlImmunityStatus.add(this.lblImmunity);
        this.pnlImmunityStatus.add(this.sldrImmunity);
        this.pnlImmunityStatus.add(this.lblStatusImmunity);
        // main panel (will contain the population and the immunity status panels)
        this.pnlMain.add(this.pnlPopulation, BorderLayout.NORTH);
        this.pnlMain.add(this.pnlImmunityStatus, BorderLayout.CENTER);
        // button panel
        this.pnlBtnStartSimulation.add(this.btnStartSimulation);

        // add to the main frame
        this.add(this.pnlTitle, BorderLayout.NORTH);
        this.add(this.pnlMain, BorderLayout.CENTER);
        this.add(this.pnlBtnStartSimulation, BorderLayout.SOUTH);

        // we need to see the things. set visible to true
        this.setVisible(true);
    }//end constructor

    private class OptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Person> personList = new ArrayList<>();

            int pop = sldrPopulation.getValue();
            int countPop = pop;
            // TODO: Check the total value for the count. their sum need to be equals
            int popNotVaccinated = sldrNotVaccinated.getValue() * pop / 100;
            int popOneShot = sldrOneShot.getValue() * pop / 100;
            int popTwoShot = sldrTwoShot.getValue() * pop / 100;
            int popImmunity = sldrImmunity.getValue() * pop / 100;

            Person infected = new Person(true, true, ImmunityStatus.Status.NO_IMMUNITY,
                    new Ball(IMG_DIAMETER, Color.RED, WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN));
            personList.add(infected);

            for (int i = 0; i < pop; i++) {
                if (popNotVaccinated-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.BLUE, WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
                    Person person = new Person(true, false, ImmunityStatus.Status.NO_IMMUNITY, ballPerson);
                    personList.add(person);
                } else if (popOneShot-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.CYAN, WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (popTwoShot-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.YELLOW, WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (popImmunity-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.GREEN, WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
                    Person person = new Person(true, false, ImmunityStatus.Status.ONE_SHOT, ballPerson);
                    personList.add(person);
                } else if (countPop-- > 0) {
                    Ball ballPerson = new Ball(IMG_DIAMETER, Color.BLUE, WINDOW_WIDTH_MAIN, WINDOW_HEIGHT_MAIN);
                    Person person = new Person(true, false, ImmunityStatus.Status.NO_IMMUNITY, ballPerson);
                    personList.add(person);
                } // end
                countPop--;
            } // end for loop

            callSimulator(personList);
        } // end actionPerformed
    } // end inner listener class

    private void callSimulator(ArrayList<Person> arr) {
        this.pnlTitle.setVisible(false);
        this.pnlPopulation.setVisible(false);
        this.pnlImmunityStatus.setVisible(false);
        this.pnlMain.setVisible(false);
        this.pnlBtnStartSimulation.setVisible(false);

        this.getContentPane().setBackground(Color.BLUE);
        this.setSize(WINDOW_WIDTH_MAIN + 100, WINDOW_HEIGHT_MAIN + 100);

        this.getContentPane().add(new Simulator(arr), BorderLayout.CENTER);
//        this.pack();
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


    private class Simulator extends JPanel {
        private ArrayList<Person> personList;
        private final int LAG_TIME = 200; // time in milliseconds between re-paints of screen
        private Timer time; //Timer class object that will fire events every LAG_TIME interval
        private final int WINDOW_WIDTH = 600;
        private final int WINDOW_HEIGHT = 400;
//        private final int IMG_DIAMETER = 10; //size of ball to be drawn

        public Simulator(ArrayList<Person> arr) {
            this.time = new Timer(LAG_TIME, new BounceListener());
            this.personList = arr;
            this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
            this.setBackground(Color.LIGHT_GRAY);
            this.time.start();
        } // end ctor

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.PINK);
            for (Person person : personList) {
                g.setColor(person.getBall().getColor());
                g.fillOval(person.getBall().getxCoord(), person.getBall().getyCoord(),
                        person.getBall().getDiameter(), person.getBall().getDiameter());
            } // end for loop
        } // end paintComponent override method

        private class BounceListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Person person : personList)
                    if (person.getIsAlive())
                        calcPosition(person.getBall());

                checkCollision();
                checkIfLive();

                if (cycleCounter < CYCLE_COUNTER_LIMITY) {
                    for (Person person : personList)
                        if (person.getIsInfected())
                            person.setCycleCounter(person.getCycleCounter() + 1);

                    System.out.printf("Main Cycle %d\n", cycleCounter + 1);
                    cycleCounter++;
                    repaint();
                }
                // TODO: Report in percentage of everything in a else. Create a output file and print at the screen

            } // end actionPerformed override method
        } // enn inner listener class

        private void checkCollision() {
            int deltaX;
            int deltaY;
            int firstBallX, firstBallY;
            int secondBallX, secondBallY;

            for (int i = 0; i < personList.size() - 1; i++) {
                if (personList.get(i).getIsAlive()) {
                    firstBallX = personList.get(i).getBall().getxCoord();
                    firstBallY = personList.get(i).getBall().getyCoord();
                    for (int j = i + 1; j < personList.size(); j++) {
                        if (personList.get(j).getIsAlive()) {
                            secondBallX = personList.get(j).getBall().getxCoord();
                            secondBallY = personList.get(j).getBall().getyCoord();

                            //now calculate deltaX and deltaY for the pair of balls
                            deltaX = firstBallX - secondBallX;
                            deltaY = firstBallY - secondBallY;
                            if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= IMG_DIAMETER)//if true, they have touched
                            {
                                personList.get(i).getBall().setxIncrement(personList.get(i).getBall().getxIncrement() * -1);
                                personList.get(i).getBall().setyIncrement(personList.get(i).getBall().getyIncrement() * -1);
                                personList.get(j).getBall().setxIncrement(personList.get(j).getBall().getxIncrement() * -1);
                                personList.get(j).getBall().setyIncrement(personList.get(j).getBall().getyIncrement() * -1);

                                int firstBallNewxIncrement = (int) (Math.random() * 11 - 5);
                                int firstBallNewyIncrement = (int) (Math.random() * 11 - 5);
                                int secondBallNewxIncrement = (int) (Math.random() * 11 - 5);
                                int secondBallNewyIncrement = (int) (Math.random() * 11 - 5);

                                personList.get(i).getBall().setxIncrement(firstBallNewxIncrement);
                                personList.get(i).getBall().setyIncrement(firstBallNewyIncrement);
                                personList.get(j).getBall().setxIncrement(secondBallNewxIncrement);
                                personList.get(j).getBall().setyIncrement(secondBallNewyIncrement);

                                if (personList.get(i).getIsInfected() && !personList.get(j).getIsInfected())
                                    changeColor(j);
                                if (personList.get(j).getIsInfected() && !personList.get(i).getIsInfected())
                                    changeColor(i);
                            }//end if
                        }//end inner for loop
                    } // end outer for loop
                } // end if block
            }//end outer loop
        } // end checkCollision method

        private void checkIfLive() {
            for (Person person : personList) {
                if (person.getCycleCounter() == CYCLE_INFECTED_LIMITY) {
                    double probOfDeath = Math.random() * 101;
                    switch (person.getImmunityStatus()) {
                        case ONE_SHOT:
                            if (probOfDeath >= 95) {
                                person.setIsAlive(false);
                                person.getBall().setColor(Color.black);
//
                            } else {
                                person.setIsInfected(false);
                                person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
                                person.getBall().setColor(Color.GREEN);
                                person.setCycleCounter(0);
                            }
                            break;
                        case TWO_SHOT:
                            if (probOfDeath >= 99) {
                                person.setIsAlive(false);
                                person.getBall().setColor(Color.black);
                            } else {
                                person.setIsInfected(false);
                                person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
                                person.getBall().setColor(Color.GREEN);
                                person.setCycleCounter(0);
                            }
                            break;
                        case IMMUNE:
                            if (probOfDeath >= 99.7) {
                                person.setIsAlive(false);
                                person.getBall().setColor(Color.black);
                            } else {
                                person.setIsInfected(false);
                                person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
                                person.getBall().setColor(Color.GREEN);
                                person.setCycleCounter(0);
                            }
                            break;
                        case NO_IMMUNITY:
                            if (probOfDeath >= 90) {
                                person.setIsAlive(false);
                                person.getBall().setColor(Color.black);
                            } else {
                                person.setIsInfected(false);
                                person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
                                person.getBall().setColor(Color.GREEN);
                                person.setCycleCounter(0);
                            }
                            break;
                        default:
                            person.setIsInfected(false);
                            person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
                            person.getBall().setColor(Color.GREEN);
                            person.setCycleCounter(0);
                            break;
                    }
                }
            }
        }

        private void changeColor(int uninfected_idx) {
            double probability = Math.random() * 100;
            switch (personList.get(uninfected_idx).getImmunityStatus()) {
                case IMMUNE:
                case TWO_SHOT:
                    if (probability > 90) {
                        personList.get(uninfected_idx).getBall().setColor(Color.RED);
                        personList.get(uninfected_idx).setIsInfected(true);
                    }
                    break;
                case ONE_SHOT:
                    if (probability > 60) {
                        personList.get(uninfected_idx).getBall().setColor(Color.RED);
                        personList.get(uninfected_idx).setIsInfected(true);
                    }
                    break;
                default:
                    if (probability > 20) {
                        personList.get(uninfected_idx).getBall().setColor(Color.RED);
                        personList.get(uninfected_idx).setIsInfected(true);
                    }
                    break;
            } // end switch
        } // end changeColor method

        public void calcPosition(Ball ball) {


            //check if near boundary. If so, then apply negative operator to the relevant increment
            //Changed the operators to >= and <= from == to fix the "disappearing ball" problem
            if (ball.getxCoord() >= WINDOW_WIDTH - ball.getDiameter()) {
                //we are at right side, so change xIncrement to a negative
                ball.setxIncrement(ball.getxIncrement() * -1);
            }
            if (ball.getxCoord() <= 0)//changed operator to <=
            {
                //if true, we're at left edge, flip the flag
                ball.setxIncrement(ball.getxIncrement() * -1);
            }
            if (ball.getyCoord() >= WINDOW_HEIGHT - ball.getDiameter()) {
                ball.setyIncrement(ball.getyIncrement() * -1);
            }
            if (ball.getyCoord() <= 0) {
                //if true, we're at left edge, flip the flag
                ball.setyIncrement(ball.getyIncrement() * -1);
            }
            //adjust the ball positions using the getters and setters
            ball.setxCoord(ball.getxCoord() + ball.getxIncrement());
            ball.setyCoord(ball.getyCoord() + ball.getyIncrement());
        } // end calcPosition method
    } // end inner class GuiSimulator

    public static void main(String[] args) {
        new MainMenu();
    }//end main
} // end class
