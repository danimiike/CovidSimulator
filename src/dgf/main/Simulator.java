package dgf.main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Simulator extends JPanel {
    private ArrayList<Person> personList;
    private final int LAG_TIME = 200; // time in milliseconds between re-paints of screen
    private Timer time; //Timer class object that will fire events every LAG_TIME interval
    private final int WINDOW_WIDTH_SIMULATOR = 450;
    private final int WINDOW_HEIGHT_SIMULATOR = 300;
    private final int IMG_DIAMETER = 10;
    private static int cycleCounter = 0;
    private static final int CYCLE_COUNTER_LIMIT = 450;
    private static final int CYCLE_INFECTED_LIMIT = 150;


    public Simulator(ArrayList<Person> arr) {
        this.time = new Timer(LAG_TIME, new BounceListener());
        this.personList = arr;
        this.setLayout(new BorderLayout(5, 5));
        this.setPreferredSize(new Dimension(WINDOW_WIDTH_SIMULATOR, WINDOW_HEIGHT_SIMULATOR));
        this.setBackground(Color.LIGHT_GRAY);
        this.time.start();
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
            if (person.getCycleCounter() == CYCLE_INFECTED_LIMIT) {
                double probabilityOfDeath = Math.random() * 100;
                switch (person.getImmunityStatus()) {
                    case ONE_SHOT:
                        liveOrDie(person, probabilityOfDeath, 95);
                        break;
                    case TWO_SHOT:
                        if (probabilityOfDeath >= 99) {
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
                        if (probabilityOfDeath >= 99.7) {
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
                        if (probabilityOfDeath >= 90) {
                            person.setIsAlive(false);
                            person.getBall().setColor(Color.black);
                        } else {
                            person.setIsInfected(false);
                            person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
                            person.getBall().setColor(Color.GREEN);
                            person.setCycleCounter(0);
                        }
                        break;
                }
            }
        }
    }

    private void liveOrDie(Person person, double probability, int probabilityOfLive){
        if (probability >= probabilityOfLive) {
            person.setIsAlive(false);
            person.getBall().setColor(Color.black);
        } else {
            person.setIsInfected(false);
            person.setImmunityStatus(ImmunityStatus.Status.IMMUNE);
            person.getBall().setColor(Color.GREEN);
            person.setCycleCounter(0);
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

} // end Simulator class
