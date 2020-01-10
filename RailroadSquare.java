/* 
 *  RailroadSquare.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;

public class RailroadSquare extends BuyableSquare{
	/* Instance Variables */
	private double rent;

	/* Default Constructor */
	public RailroadSquare(){
		super();
		this.rent = 0.0;
	}

	/* Overloaded Constructor */
	public RailroadSquare(String name, Square next, double price, double rent){
		super(name, next, price, null, false);
		this.rent = rent;
	}

	/* Accessors */
	public double getRent(){
		return this.rent;
	}

	/* Mutators */
	public void setRent(double rent){
		this.rent = rent;
	}
        
    /* Other Methods */

    /* 
     * 	action(Player player)
     *	If the railroad is not owned, prompts the player to buy it.
     * 	Otherwise, deducts the rent from the owner and adds the money to 
     *	the owner of the railroad.
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
                    player.addRailroad(this);                	
                    player.deductFromWallet(price);
                }
            }
        }
        else{
            System.out.println("This propery is owned by " + owner.getName() +
                    ". You owe " + this.rent);
            player.deductFromWallet(this.rent);
            owner.addToWallet(this.rent);  
        }
	} 
}