
/**
 * Program Name: Person.java
 * Purpose:	this is a class to create a Person object that will be used in the
 *         	Covid-19 Simulation application.
 * Coder:	Danielle Menezes de Mello Miike
 * 			Felipe Lopes Leite
 * 			Georgia Pattern
 * Date: Jul 31, 2021
 */

public class Person
{
	// TODO: Statistics analysis
	protected boolean isAlive;
	protected boolean isInfected;
	protected int immunityStatus;
	//ver se vamos colorir as pessoas aqui na classe ou no main
	
	
	//these will hold the position where the object will be drawn on the drawing panel. These are randomly 
	//generated when the object is instantiated. You should ensure that the values are within a range so that a person 
	//object will not be placed outside of the drawing surface of your JPanel
	protected int xCoord;
	protected int yCoord;
	
	//these represent the number of pixels that the object will �move� in each drawing cycle. The value of each can 
	//range from -5 to +5 pixels. At instantiation time these values should be randomly generated and assigned so that 
	//a particular Person object could be drawn anywhere on the drawing surface.. These values will also be modified if the object 
	//collides with another object so that the objects will probably change direction after the collision.
	protected int xIncrementValue;
	protected int yIncrementValue;
	
	//this is used to determine how long an infected object remains infections. It starts at zero and only starts 
	//incrementing when the object�s infected status changes to true. One of the assumptions we�ll use in our model is 
	//that an infected person will be infectious for a maximum of 7 days, so we�ll arbitrarily set the end of the infectious 
	//stage at a count as 150 cycles	
	protected int cycleCounter;
	
	public Person(boolean isAlive, boolean isInfected, int immunityStatus){//user inputs
		this.isAlive = isAlive;
		this.isInfected = isInfected;
		this.immunityStatus = immunityStatus;
		
	} //end constructor
	
	//getters and setters
	
	public int getxCoord()
	{
		return xCoord;
	}
	public int getyCoord()
	{
		return yCoord;
	}
	public int getxIncrementValue()
	{
		return xIncrementValue;
	}
	public int getyIncrementValue()
	{
		return yIncrementValue;
	}
	public int getCycleCounter()
	{
		return cycleCounter;
	}
	public boolean getIsAlive()
	{
		return isAlive;
	}
	public boolean getIsInfected()
	{
		return isInfected;
	}
	public int getimmunityStatus()
	{
		return immunityStatus;
	}
	
	
	public void setxCoord(int xCoord)
	{
		this.xCoord = xCoord;
	}
	public void setyCoord(int yCoord)
	{
		this.yCoord = yCoord;
	}
	public void setxIncrementValue(int xIncrementValue)
	{
		this.xIncrementValue = xIncrementValue;
	}
	public void setyIncrementValue(int yIncrementValue)
	{
		this.yIncrementValue = yIncrementValue;
	}
	public void setCycleCounter( int cycleCounter)
	{
		this.cycleCounter = cycleCounter;
	}
	public void setIsAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}
	public void setIsInfected(boolean isInfected)
	{
		this.isInfected = isInfected;
	}
	public void setImmunityStatus(int immunityStatus)
	{
		this.immunityStatus = immunityStatus;
	}
	

}


//end class