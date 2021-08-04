package dgf.main;

import java.awt.*;

public class Ball {

    // Paint mathod
    //

    private int xCoord, yCoord, diameter;
    //these represent the number of pixels that the object will �move� in each drawing cycle. The value of each can
    //range from -5 to +5 pixels. At instantiation time these values should be randomly generated and assigned so that
    //a particular dgf.main.Person object could be drawn anywhere on the drawing surface.. These values will also be modified if the object
    //collides with another object so that the objects will probably change direction after the collision.

    //flags for ball direction
    private boolean xFlag; //if true, we move to the right by incrementing xCoord
    //if false, we move left by decrementing xCoord.
    private boolean yFlag; //if true, we move down by incrementing yCoord
    //if false, we move up by decrementing yCoord

    private Graphics graphics;

    public Ball(int xCoord, int yCoord, int diameter, boolean xFlag, boolean yFlag) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.diameter = diameter;
        this.xFlag = xFlag;
        this.yFlag = yFlag;
    }

    // Getters and Setters
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

    public boolean isxFlag() {
        return xFlag;
    }

    public void setxFlag(boolean xFlag) {
        this.xFlag = xFlag;
    }

    public boolean isyFlag() {
        return yFlag;
    }

    public void setyFlag(boolean yFlag) {
        this.yFlag = yFlag;
    }
}
