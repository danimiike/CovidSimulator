package dgf.main;

/**
 * Program Name: dgf.main.Simulator.java
 * Purpose: This class is used to create the reports, and the simulation controls. 
 * Coder: 	Danielle Miike
 * 			Felipe Leite
 * 			Georgia Patten
 *
 * Date: Aug 1, 2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Simulator extends JPanel {
    private static ArrayList<Person> personList;
    private final int LAG_TIME = 200; // time in milliseconds between re-paints of screen
    private static Timer time; //Timer class object that will fire events every LAG_TIME interval
    private final int WINDOW_WIDTH_SIMULATOR = 450;
    private final int WINDOW_HEIGHT_SIMULATOR = 300;
    private final int IMG_DIAMETER = 10;
    private static int cycleCounter = 0;
    private static final int CYCLE_COUNTER_LIMIT = 450;
    private static final int CYCLE_INFECTED_LIMIT = 150;

    protected static JLabel lblPopContracted = new JLabel("Currently infected:-");
    protected static JLabel lblPopNoShotContracted = new JLabel("Non Vac:-");
    protected static JLabel lblPopPartContracted = new JLabel("Partially Vac:-");
    protected static JLabel lblPopFullyContracted = new JLabel("Fully Vac/Immune:-");
    protected static JLabel lblRecovered = new JLabel("Recovered:-");
    protected static JLabel lblDied = new JLabel("Died:-");

    protected static JLabel lblPopPerc = new JLabel("Got Infected:-");
    protected static JLabel lblPopNoShotPerc = new JLabel("Non Vac:-");
    protected static JLabel lblPopPartPerc = new JLabel("Partially Vac:-");
    protected static JLabel lblPopFullyPerc = new JLabel("Fully Vac/Immune:-");
    protected static JLabel lblRecoveredPerc = new JLabel("Recovered:-");
    protected static JLabel lblDiedPerc = new JLabel("Died:-");

    private DecimalFormat numberFormat = new DecimalFormat("0.00");

    protected static final String PURPOSE_STRING = """
            DGF - TECHNOLOGY
            The purpose of this program is to simulate a pandemic.
            In this program a user will be able to choose the population,
            the percentage of people unvaccinated, the percentage of people
            with their first vaccination, the percentage of people fully
            vaccinated, and the amount of people with natural immunity.
            With this information, there is an algorithm working in the
            background that measures the percentage a person will catch the
            virus. When a person collides with an infected person, they have
            a chance of catching the virus. They will then either stay as
            they are or turn red with infection. Many people by the end will
            recover but there is a chance people could die. All this information
            will be shown through our pandemic simulation.""";


    private int cPopContracted = 0, cNonVacContracted = 0, cPartVacContracted = 0, cFullyVacContracted = 0, cRecovered = 0, cDied = 0;
    private int curInfectedTotal = 0, curInfectedNonVac = 1, curInfectedOneVac = 0, curInfectedFullyVac = 0;

    public Simulator(ArrayList<Person> arr) {
        time = new Timer(LAG_TIME, new BounceListener());
        personList = arr;
        this.setLayout(new BorderLayout(5, 5));
//        this.setPreferredSize(new Dimension(WINDOW_WIDTH_SIMULATOR, WINDOW_HEIGHT_SIMULATOR));
        this.setBackground(Color.LIGHT_GRAY);
        time.start();
    }

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
                                checkIfGetInfected(j);
                            if (personList.get(j).getIsInfected() && !personList.get(i).getIsInfected())
                                checkIfGetInfected(i);
                        }//end if
                    }//end inner for loop
                } // end outer for loop
            } // end if block
        }//end outer loop
    } // end checkCollision method

    private void checkDeathOfPerson() {
        for (Person person : personList) {
            if (person.getCycleCounter() == CYCLE_INFECTED_LIMIT) {
                double probabilityOfDeath = Math.random() * 100;
                switch (person.getImmunityStatus()) {
                    case NO_IMMUNITY -> getLifeOrDeath(person, probabilityOfDeath, 90);
                    case ONE_SHOT -> getLifeOrDeath(person, probabilityOfDeath, 95);
                    case TWO_SHOT -> getLifeOrDeath(person, probabilityOfDeath, 99);
                    case IMMUNE -> getLifeOrDeath(person, probabilityOfDeath, 99.7);
                }
            }
        }
    } // end checkLive

    private void getLifeOrDeath(Person person, double probability, double probabilityOfLive) {
        if (probability >= probabilityOfLive) {
            person.setIsAlive(false);
            person.getBall().setColor(Color.black);
            cDied++;
        } else {
            person.setIsInfected(false);
            person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
            person.getBall().setColor(Color.GREEN);
            person.setCycleCounter(0);
            if (person.timeInfected == 1)
                cRecovered++;
        }
    }

    private void checkIfGetInfected(int uninfected_idx) {
        double probability = Math.random() * 100;
        switch (personList.get(uninfected_idx).getImmunityStatus()) {
            case TWO_SHOT -> {
                changeColor(personList.get(uninfected_idx), probability, 90);
                if (personList.get(uninfected_idx).timeInfected == 1 && personList.get(uninfected_idx).getIsInfected())
                    cFullyVacContracted++;
            }
            case ONE_SHOT -> {
                changeColor(personList.get(uninfected_idx), probability, 60);
                if (personList.get(uninfected_idx).timeInfected == 1 && personList.get(uninfected_idx).getIsInfected())
                    cPartVacContracted++;
            }
            default -> {
                changeColor(personList.get(uninfected_idx), probability, 20);
                if (personList.get(uninfected_idx).timeInfected == 1 && personList.get(uninfected_idx).getIsInfected())
                    cNonVacContracted++;
            }
        } // end switch
    } // end changeColor method

    private void changeColor(Person person, double probability, int chance) {
        if (probability > chance) {
            person.getBall().setColor(Color.RED);
            person.setIsInfected(true);
            person.timeInfected++;
        }
    } // end changeColor

    public void calcPosition(Ball ball) {


        //check if near boundary. If so, then apply negative operator to the relevant increment
        //Changed the operators to >= and <= from == to fix the "disappearing ball" problem
        if (ball.getxCoord() >= WINDOW_WIDTH_SIMULATOR - ball.getDiameter()) {
            //we are at right side, so change xIncrement to a negative
            ball.setxIncrement(ball.getxIncrement() * -1);
        }
        if (ball.getxCoord() <= 0)//changed operator to <=
        {
            //if true, we're at left edge, flip the flag
            ball.setxIncrement(ball.getxIncrement() * -1);
        }
        if (ball.getyCoord() >= WINDOW_HEIGHT_SIMULATOR - ball.getDiameter()) {
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

    protected static class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "Pause Simulation", "Pause" -> time.stop();
                case "Resume Simulation", "Resume" -> time.start();
                case "Authors" -> JOptionPane.showMessageDialog(null, GuiMain.AUTHOR_SCROLL_PANE, "Authors", JOptionPane.PLAIN_MESSAGE);
                case "Purpose" -> JOptionPane.showMessageDialog(null, PURPOSE_STRING, "Purpose", JOptionPane.PLAIN_MESSAGE);
            } // end switch
        } // end
    }

    private class BounceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Person person : personList)
                if (person.getIsAlive())
                    calcPosition(person.getBall());

            checkCollision();
            checkDeathOfPerson();

            if (cycleCounter < CYCLE_COUNTER_LIMIT) {
                for (Person person : personList)
                    if (person.getIsInfected())
                        person.setCycleCounter(person.getCycleCounter() + 1);

                System.out.printf("Main Cycle %d\n", cycleCounter + 1);
                cycleCounter++;

                curInfectedTotal = curInfectedNonVac = curInfectedOneVac = curInfectedFullyVac = 0;
                for (Person person : personList) {
                    if (person.getIsInfected()) {
                        curInfectedTotal++;
//                        if (person.timeInfected >= 1) {
                        switch (person.getImmunityStatus()) {
                            case NO_IMMUNITY -> curInfectedNonVac++;
                            case ONE_SHOT -> curInfectedOneVac++;
                            case TWO_SHOT -> curInfectedFullyVac++;
                        }
//                        }
                    }
                }
                lblPopContracted.setText("Got infected: " + curInfectedTotal);
                lblPopNoShotContracted.setText("Non Vac: " + curInfectedNonVac);
                lblPopPartContracted.setText("Part Vac: " + curInfectedOneVac);
                lblPopFullyContracted.setText("Fully Vac: " + curInfectedFullyVac);
                lblRecovered.setText("Recovered: " + cRecovered);
                lblDied.setText("Died: " + cDied);

                repaint();
            } else {
                for (Person person : personList) {
                    if (person.timeInfected > 0)
                        cPopContracted++;
                }
                double percTotal = cPopContracted * 100.0 / personList.size();
                double percNonVac = cNonVacContracted * 100.0 / personList.size();
                double percPartVac = cPartVacContracted * 100.0 / personList.size();
                double percFullyVac = cFullyVacContracted * 100.0 / personList.size();
                double percRecovered = cRecovered * 100.0 / personList.size();
                double percDied = cDied * 100.0 / personList.size();

                lblPopPerc.setText("Infected: " + numberFormat.format(percTotal) + "%");
                lblPopNoShotPerc.setText("Non Vac: " + numberFormat.format(percNonVac) + "%");
                lblPopPartPerc.setText("Part Vac: " + numberFormat.format(percPartVac) + "%");
                lblPopFullyPerc.setText("Fully Vac/Immune: " + numberFormat.format(percFullyVac) + "%");
                lblRecoveredPerc.setText("Recovered: " + numberFormat.format(percRecovered) + "%");
                lblDiedPerc.setText("Died: " + numberFormat.format(percDied) + "%");

                System.out.printf("Total population who contracted the disease %-10.2f (%d)\n", percTotal, cPopContracted);
                System.out.printf("Total non vac who contracted the disease %-10.2f (%d)\n", percNonVac, cNonVacContracted);
                System.out.printf("Total parc vac who contracted the disease %-10.2f (%d)\n", percPartVac, cPartVacContracted);
                System.out.printf("Total fully vac who contracted the disease %-10.2f (%d)\n", percFullyVac, cFullyVacContracted);
                System.out.printf("Total recovered who contracted the disease %-10.2f (%d)\n", percRecovered, cRecovered);
                System.out.printf("Total died who contracted the disease %-10.2f (%d)\n", percDied, cDied);

                time.stop();
            } // end if-else block
        } // end actionPerformed override method
    } // enn inner listener class

} // end Simulator class
