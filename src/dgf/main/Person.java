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

    protected Ball ball;

    //this is used to determine how long an infected object remains infections. It starts at zero and only starts
    //incrementing when the object�s infected status changes to true. One of the assumptions we�ll use in our model is
    //that an infected person will be infectious for a maximum of 7 days, so we�ll arbitrarily set the end of the infectious
    //stage at a count as 150 cycles
    protected int cycleCounter;

    public Person(boolean isAlive, boolean isInfected, int immunityStatus, Ball ball) {//user inputs
        this.isAlive = isAlive;
        this.isInfected = isInfected;
        this.immunityStatus = immunityStatus;

        this.ball = ball;
    } //end constructor

    //getters and setters
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