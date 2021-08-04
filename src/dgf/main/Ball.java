package dgf.main;

import java.awt.*;

public class Ball {

    private int xCoord;
    private int yCoord;
    private int diameter;
    private Color color;
    private int xIncrement;
    private int yIncrement;

    public Ball(int xCoord, int yCoord, int diameter, Color color) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.diameter = diameter;
        this.color = color;

        boolean loopFlag = true;
        while (loopFlag) {
            this.xIncrement = (int) (Math.random() * 10 - 5);
            this.yIncrement = (int) (Math.random() * 10 - 5);
            if (this.xIncrement == 0 && this.yIncrement == 0) {
                //run it again
                this.xIncrement = (int) (Math.random() * 10 - 5);
                this.yIncrement = (int) (Math.random() * 10 - 5);
            } else {
                loopFlag = false;
            } // end if-else block
        }//end loop
    } // end Ball ctor

    public Ball(int diameter, Color color, int widthValue, int heightValue) {
        this.diameter = diameter;
        this.color = color;

        int randomX, randomY;
        boolean loopFlag = true;
        while (loopFlag) {
            //generate a random value using widthValue
            randomX = (int) (Math.random() * widthValue);
            if (randomX >= 0 && randomX <= widthValue - this.diameter) {
                //we have a valid x value, assign it to xCoord
                this.xCoord = randomX;
                //System.out.println("STUB:Valid random xCoord value of " + randomX);
                loopFlag = false;
            }
        }//end while

        //reset flag1 to true to start second loop
        loopFlag = true;
        while (loopFlag) {
            //repeat for yCoord
            randomY = (int) (Math.random() * heightValue);
            if (randomY >= 0 && randomY <= heightValue - this.diameter) {
                //we have a valid y value, assign it to yCoord
                this.yCoord = randomY;
                //System.out.println("STUB:Valid random yCoord value of " + randomY);
                loopFlag = false;
            }
        }//end while

        //Added July 15 to get the values for the increments
        loopFlag = true;
        while (loopFlag) {
            this.xIncrement = (int) (Math.random() * 10 - 5);
            this.yIncrement = (int) (Math.random() * 10 - 5);
            if (this.xIncrement == 0 && this.yIncrement == 0) {
                //run it again
                this.xIncrement = (int) (Math.random() * 10 - 5);
                this.yIncrement = (int) (Math.random() * 10 - 5);
            } else {
                loopFlag = false;
            }
        }//end loop
    } // end ctor


    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getxIncrement() {
        return xIncrement;
    }

    public void setxIncrement(int xIncrement) {
        this.xIncrement = xIncrement;
    }

    public int getyIncrement() {
        return yIncrement;
    }

    public void setyIncrement(int yIncrement) {
        this.yIncrement = yIncrement;
    }
} // end class
