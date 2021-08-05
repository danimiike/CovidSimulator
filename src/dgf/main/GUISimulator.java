package dgf.main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUISimulator extends JPanel {

    private ArrayList<Person> personList;
    private final int LAG_TIME = 200; // time in milliseconds between re-paints of screen
    private Timer time;//Timer class object that will fire events every LAG_TIME interval
    private final int WIDTH = 700, HEIGHT = 450;//size of JPanel
    private final int IMG_DIM = 10; //size of ball to be drawn

    public GUISimulator(ArrayList<Person> list) {

        this.personList = list;
        this.time = new Timer(LAG_TIME, new BounceListener());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);


        this.time.start();

    } // end ctor

    //*********************************NEW****************************//


    public void paintComponent(Graphics g)//The Graphics object 'g' is your paint brush
    {
        //call super version of this method to "throw the bucket of paint onto the canvas"
        // and cover up any previous image.
        //NOTE: try commenting this out to see the effect of not repainting.
        super.paintComponents(g);

        g.setColor(Color.PINK);
        //REIVSISION HERE: need to access the Ball object's state values in the call to
        // fillOval
        //REVISION JULY 15: iterate through the loop to paint the balls onto the panel
        // and set the color using the Ball object's color value
        for (int i = 0; i < this.personList.size(); i++) {
            //get the color
            g.setColor(this.personList.get(i).getBall().getColor());
            g.fillOval(this.personList.get(i).getBall().getxCoord(), this.personList.get(i).getBall().getyCoord(),
                    this.personList.get(i).getBall().getDiameter(), this.personList.get(i).getBall().getDiameter());
        }
        //draw a circle shape


    }//end paintComponent over-ride

    private class BounceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < personList.size(); i++) {
                calcPosition(personList.get(i).getBall());
            }
            int deltaX;
            int deltaY;
            int firstBallX, firstBallY, secondBallX, secondBallY;

            for (int i = 0; i < personList.size() - 1; i++) {
                //get the x and y co-ords of  first ball of the pair
                firstBallX = personList.get(i).getBall().getxCoord();
                firstBallY = personList.get(i).getBall().getyCoord();

                //Inner loop gets the second ball of the pair
                //start inner loop counter at i+1 so we don't compare the first ball to itself.
                for (int j = i + 1; j < personList.size(); j++) {
                    secondBallX = personList.get(j).getBall().getxCoord();
                    secondBallY = personList.get(j).getBall().getyCoord();

                    //now calculate deltaX and deltaY for the pair of balls
                    deltaX = firstBallX - secondBallX;
                    deltaY = firstBallY - secondBallY;
                    //square them to get rid of negative values, then add them and take square root of total
                    // and compare it to ball diameter held in IMG_DIM
                    if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= IMG_DIM)//if true, they have touched
                    {
                        //REVSION HERE: not using the xFlag and yFlag anymore, so now we  adjust
                        // the xIncrement and yIncrement by multiplying by -1
                        personList.get(i).getBall().setxIncrement(personList.get(i).getBall().getxIncrement() * -1);
                        personList.get(i).getBall().setyIncrement(personList.get(i).getBall().getyIncrement() * -1);

                        //now do the secondBall
                        personList.get(j).getBall().setxIncrement(personList.get(j).getBall().getxIncrement() * -1);
                        personList.get(j).getBall().setyIncrement(personList.get(j).getBall().getyIncrement() * -1);

                        //ALSO, to get a bit of directional change generate a new set of random values for the xIncrement
                        //  and yIncrement of each ball involved in the collision and assign them.
                        int firstBallnewxIncrement = (int) (Math.random() * 11 - 5);
                        int firstBallnewyIncrement = (int) (Math.random() * 11 - 5);
                        int secondBallnewxIncrement = (int) (Math.random() * 11 - 5);
                        int secondBallnewyIncrement = (int) (Math.random() * 11 - 5);

                        //this will prevent balls from "getting stuck" on the borders.
                        personList.get(i).getBall().setxIncrement(firstBallnewxIncrement);
                        personList.get(i).getBall().setyIncrement(firstBallnewyIncrement);
                        personList.get(j).getBall().setxIncrement(secondBallnewxIncrement);
                        personList.get(j).getBall().setyIncrement(secondBallnewyIncrement);


                        //IN VERSION FIVE change the color of a blue ball to red.
                        if (personList.get(i).getBall().getColor().equals(Color.RED) && personList.get(j).getBall().getColor().equals(Color.BLUE)) {
                            //change second ball to color of first ball
                            personList.get(j).getBall().setColor(personList.get(i).getBall().getColor());
                        }
                        if (personList.get(j).getBall().getColor().equals(Color.RED) && personList.get(i).getBall().getColor().equals(Color.BLUE)) {
                            //second ball is red, so change first ball to color of second ball
                            personList.get(i).getBall().setColor(personList.get(j).getBall().getColor());
                            ;
                        }
                    }//end if
                }//end inner for
            }//end outer loop

            //call repaint(), which in turn calls paintComponent()
            repaint();

        }//end method
    }

    public void calcPosition(Ball ball) {

        //check if near boundary. If so, then apply negative operator to the relevant increment
        //Changed the operators to >= and <= from == to fix the "disappearing ball" problem
        if (ball.getxCoord() >= WIDTH - ball.getDiameter()) {
            //we are at right side, so change xIncrement to a negative
            ball.setxIncrement(ball.getxIncrement() * -1);
        }
        if (ball.getxCoord() <= 0)//changed operator to <=
        {
            //if true, we're at left edge, flip the flag
            ball.setxIncrement(ball.getxIncrement() * -1);
            ;
        }
        if (ball.getyCoord() >= HEIGHT - ball.getDiameter()) {
            ball.setyIncrement(ball.getyIncrement() * -1);
        }
        if (ball.getyCoord() <= 0) {
            //if true, we're at left edge, flip the flag
            ball.setyIncrement(ball.getyIncrement() * -1);
            ;
        }
        //adjust the ball positions using the getters and setters
        ball.setxCoord(ball.getxCoord() + ball.getxIncrement());
        ball.setyCoord(ball.getyCoord() + ball.getyIncrement());
    }//end calcPosition


} // end class
