/* 
 *  UtilitySquare.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;
import java.util.Random;

public class UtilitySquare extends BuyableSquare{
	/* Instance Variables */
	private double multiplier;

	/* Default Constructor */
	public UtilitySquare(){
		super();
		this.multiplier = 0.0;
	}

	/* Overloaded Constructor */
	public UtilitySquare(String name, Square next, double price, double multiplier){		
		super(name, next, price, null, false);
		this.multiplier = multiplier;
	}

	/* Accessors */
	public double getMultiplier(){
		return this.multiplier;
	}

	/* Mutators */
	public void setMultiplier(double multiplier){
		this.multiplier = multiplier;
	}

	/* Other Methods */

    /* 
     * 	getRent()
	 *  Simulate a dice roll (2, 6-sided dice) and multiply that value by the multiplier value.
	 *  Return this value as the cost of rent.
     */ 
	public double getRent(){
	    Random rand = new Random();
	    int roll = rand.nextInt(12) + 1;
	    return roll * multiplier;
	}

    /* 
     * 	action(Player player)
	 *  Simulate a dice roll (2, 6-sided dice) and multiply that value by the multiplier value.
	 *  Return this value as the cost of rent.
     */ 
	@Override
    public void action(Player player){
        Scanner input = new Scanner(System.in);

        if (!owned){
            System.out.print("For Sale. $" + this.price + " Would you like to purchase? y or n ");
            char answer = input.next().charAt(0);

            if (answer == 'Y' || answer == 'y'){
                if (player.getWallet() < this.price)
                    System.out.println("Insufficient funds");
                else{
                    this.owner = player;                    
                    this.owned = true;                    	
                    player.deductFromWallet(this.price);
                }
            }
        }
        else{
        	double rent = this.getRent();
            System.out.println("This propery is owned by " + owner.getName() +
                    ". You owe " + rent);
            player.deductFromWallet(rent);
            owner.addToWallet(rent);  
        }
	}
}