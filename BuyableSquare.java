/* 
 *  BuyableSquare.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;

public class BuyableSquare extends Square{
	/* Instance Variables */
	protected double price;
	protected Player owner;
	protected boolean owned;

    /* Default Constructor */    
	public BuyableSquare(){
		super();
		this.price = 0.0;
		this.owner = null;
		this.owned = false;
	}

	/* Overloaded Constructor */
	public BuyableSquare(String name, Square next, double price, Player owner, boolean owned){
		super(name, next);
		this.price = price;
		this.owner = owner;
		this.owned = owned;
	}

	/* Accessors */
	public double getPrice(){
		return this.price;
	}

	public Player getOwner(){
		return this.owner;
	}

	public boolean getOwned(){
		return this.owned;
	}

	/* Mutators */
	public void setPrice(double price){
		this.price = price;
	}

	public void setOwner(Player owner){
		this.owner = owner;
	}

	public void setOwned(boolean owned){
		this.owned = owned;
	}
}