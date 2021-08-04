package dgf.main;

/**
 * Program Name: dgf.main.Person.java
 * Purpose:	this is a class to create a dgf.main.Person object that will be used in the
 * Covid-19 Simulation application.
 * Coder:	Danielle Menezes de Mello Miike
 * Felipe Lopes Leite
 * Georgia Pattern
 * Date: Jul 31, 2021
 */

public class Person {

    protected boolean isAlive;
    protected boolean isInfected;
    protected int immunityStatus;
    protected int xCoord, yCoord, diameter;

    //these represent the number of pixels that the object will �move� in each drawing cycle. The value of each can
    //range from -5 to +5 pixels. At instantiation time these values should be randomly generated and assigned so that
    //a particular dgf.main.Person object could be drawn anywhere on the drawing surface.. These values will also be modified if the object
    //collides with another object so that the objects will probably change direction after the collision.
    boolean xFlag, yFlag;

    //this is used to determine how long an infected object remains infections. It starts at zero and only starts
    //incrementing when the object�s infected status changes to true. One of the assumptions we�ll use in our model is
    //that an infected person will be infectious for a maximum of 7 days, so we�ll arbitrarily set the end of the infectious
    //stage at a count as 150 cycles
    protected int cycleCounter;

    public Person(boolean isAlive, boolean isInfected, int immunityStatus, int x, int y, int diam, boolean xF, boolean yF) {//user inputs
        this.isAlive = isAlive;
        this.isInfected = isInfected;
        this.immunityStatus = immunityStatus;



        this.xCoord = x;
        this.yCoord = y;
        this.diameter = diam;
        this.xFlag = xF;
        this.yFlag = yF;
    } //end constructor

    //getters and setters

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getDiameter() {
        return diameter;
    }

    public boolean isxFlag() {
        return xFlag;
    }

    public boolean isyFlag() {
        return yFlag;
    }

    public int getCycleCounter() {
        return cycleCounter;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public boolean getIsInfected() {
        return isInfected;
    }

    public int getImmunityStatus() {
        return immunityStatus;
    }


    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setyFlag(boolean yFlag) {
        this.yFlag = yFlag;
    }

    public void setxFlag(boolean xFlag) {
        this.xFlag = xFlag;
    }

    public void setCycleCounter(int cycleCounter) {
        this.cycleCounter = cycleCounter;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setIsInfected(boolean isInfected) {
        this.isInfected = isInfected;
    }

    public void setImmunityStatus(int immunityStatus) {
        this.immunityStatus = immunityStatus;
    }


}


//end class