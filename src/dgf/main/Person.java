package dgf.main;

/**
 * Program Name: dgf.main.Person.java
 * Purpose:	This is a class to create a dgf.main.Person object that will be used in the
 * 			Covid-19 Simulation application.
 * Coder:	Danielle Menezes de Mello Miike
 * 			Felipe Lopes Leite
 *			 Georgia Pattern
 * Date: 	Jul 31, 2021
 */

public class Person {

    private boolean isAlive;
    private boolean isInfected;
    protected int timeInfected;


    private ImmunityStatus.Status immunityStatus;

    private Ball ball;

    private int cycleCounter;

    public Person(boolean isAlive, boolean isInfected, int timeInfected, ImmunityStatus.Status immunityStatus, Ball ball) {//user inputs
        this.isAlive = isAlive;
        this.isInfected = isInfected;
        this.immunityStatus = immunityStatus;
        this.timeInfected = timeInfected;
        this.cycleCounter = 0;
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

    public ImmunityStatus.Status getImmunityStatus() {
        return immunityStatus;
    }

    public Ball getBall() {
        return ball;
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

    public void setImmunityStatus(ImmunityStatus.Status immunityStatus) {
        this.immunityStatus = immunityStatus;
    }

    public void setBallProperties(Ball newBall) {
        this.ball = newBall;
    }



}


//end class