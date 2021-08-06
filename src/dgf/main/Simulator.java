package dgf.main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    protected static JLabel lblPopContracted = new JLabel("Infected:-");
    protected static JLabel lblPopFullyContracted = new JLabel("Fully Vaccinated Infected:-");
    protected static JLabel lblPopPartContracted = new JLabel("Partially Vaccinated Infected:-");
    protected static JLabel lblPopNoShotContracted = new JLabel("Non Vaccinated Infected:-");


//            , lblNonVacContracted, lblPartVacContracted, lblFullyVacContracted, lblRecovered, lblDied;

    private int cPopContracted = 0, cNonVacContracted = 0, cPartVacContracted = 0, cFullyVacContracted = 0, cRecovered = 0, cDied = 0;

    public Simulator(ArrayList<Person> arr) {
        time = new Timer(LAG_TIME, new BounceListener());
        personList = arr;
        this.setLayout(new BorderLayout(5, 5));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH_SIMULATOR, WINDOW_HEIGHT_SIMULATOR));
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

    private void checkIfLive() {
        for (Person person : personList) {
            if (person.getCycleCounter() == CYCLE_INFECTED_LIMIT) {
                double probabilityOfDeath = Math.random() * 100;
                switch (person.getImmunityStatus()) {
                    case NO_IMMUNITY -> liveOrDie(person, probabilityOfDeath, 90);
                    case ONE_SHOT -> liveOrDie(person, probabilityOfDeath, 95);
                    case TWO_SHOT -> liveOrDie(person, probabilityOfDeath, 99);
                    case IMMUNE -> liveOrDie(person, probabilityOfDeath, 99.7);
                }
            }
        }
    } // end checkLive

    private void liveOrDie(Person person, double probability, double probabilityOfLive) {
        if (probability >= probabilityOfLive) {
            person.setIsAlive(false);
            person.getBall().setColor(Color.black);
            cDied++;
        } else {
            person.setIsInfected(false);
            person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
            person.getBall().setColor(Color.GREEN);
            person.setCycleCounter(0);
            if (person.timeInfected <= 1)
                cRecovered++;
        }
    }

    private void checkIfGetInfected(int uninfected_idx) {
        double probability = Math.random() * 100;
        switch (personList.get(uninfected_idx).getImmunityStatus()) {
            case IMMUNE:
                changeColor(personList.get(uninfected_idx), probability, 90);
                break;
            case TWO_SHOT:
                changeColor(personList.get(uninfected_idx), probability, 90);
                if (personList.get(uninfected_idx).timeInfected < 1)
                    cFullyVacContracted++;
                break;
            case ONE_SHOT:
                changeColor(personList.get(uninfected_idx), probability, 60);
                if (personList.get(uninfected_idx).timeInfected < 1)
                    cPartVacContracted++;
                break;
            default:
                changeColor(personList.get(uninfected_idx), probability, 20);
                if (personList.get(uninfected_idx).timeInfected < 1)
                    cNonVacContracted++;
                break;
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
                case "Pause":
                    time.stop();
                    break;
                case "Resume":
                    time.start();
                    break;
                default:
                    break;
            } // end switch
        } // end
    }

    private class ReportLabelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class BounceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Person person : personList)
                if (person.getIsAlive())
                    calcPosition(person.getBall());

            checkCollision();
            checkIfLive();

            if (cycleCounter < CYCLE_COUNTER_LIMIT) {
                for (Person person : personList)
                    if (person.getIsInfected())
                        person.setCycleCounter(person.getCycleCounter() + 1);

                System.out.printf("Main Cycle %d\n", cycleCounter + 1);
                cycleCounter++;
                int totalIntected = 0;
                for (Person person : personList) {
                    if (person.getIsInfected())
                        totalIntected++;
                }
                lblPopContracted.setText("Infected: " + totalIntected);
                lblPopFullyContracted.setText("Fully Vaccinated Infected: " + cFullyVacContracted);
                lblPopPartContracted.setText("Partially Vaccinated Infected: " + cPartVacContracted);
                lblPopNoShotContracted.setText("Non Vaccinated Infected: " + cNonVacContracted);
                
                
                repaint();
            } else {
                for (Person person : personList) {
                    if (person.timeInfected == 1)
                        cPopContracted++;
                }
                double percTotal = cPopContracted * 100.0 / personList.size();
                double percNonVac = cNonVacContracted * 100.0 / personList.size();
                double percPartVac = cPartVacContracted * 100.0 / personList.size();
                double percFullyVac = cFullyVacContracted * 100.0 / personList.size();
                double percRecovered = cRecovered * 100.0 / personList.size();
                double percDied = cDied * 100.0 / personList.size();

                System.out.printf("Total population who contracted the disease %-10.2f (%d)\n", percTotal, cPopContracted);
                System.out.printf("Total non vac who contracted the disease %-10.2f (%d)\n", percNonVac, cNonVacContracted);
                System.out.printf("Total parc vac who contracted the disease %-10.2f (%d)\n", percPartVac, cPartVacContracted);
                System.out.printf("Total fully vac who contracted the disease %-10.2f (%d)\n", percFullyVac, cFullyVacContracted);
                System.out.printf("Total recovered who contracted the disease %-10.2f (%d)\n", percRecovered, cRecovered);
                System.out.printf("Total died who contracted the disease %-10.2f (%d)\n", percDied, cDied);

                time.stop();
            }
            // TODO: Report in percentage of everything in a else. Create a output file and print at the screen

        } // end actionPerformed override method


    } // enn inner listener class

} // end Simulator class
