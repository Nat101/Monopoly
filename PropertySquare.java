/* 
 *  PropertySquare.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;

public class PropertySquare extends BuyableSquare{
    /* Constant Variables */
	private final int ARRAY_SIZE = 4;
	private final double HOUSE_COST = 50.0;

	/* Instance Variables */
	private String color;
	private boolean[] houses;
	private double rent, oneHouse, twoHouse, threeHouse, fourHouse;

	/* Default Constructor */
	public PropertySquare(){
		super();
		this.color = "None";
		this.rent = 0.0;
		this.oneHouse = 0.0;
		this.twoHouse = 0.0;
		this.threeHouse = 0.0;
		this.fourHouse = 0.0;
		this.houses = new boolean[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++)
			this.houses[i] = false;
	}

	/* Overloaded Constructor */
	public PropertySquare(String name, Square next, double price, String color, double rent, double oneHouse, double twoHouse, double threeHouse, double fourHouse){
		super(name, next, price, null, false);
		this.color = color;
		this.rent = rent;
		this.oneHouse = oneHouse;
		this.twoHouse = twoHouse;
		this.threeHouse = threeHouse;
		this.fourHouse = fourHouse;
		this.houses = new boolean[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++)
			this.houses[i] = false;
	}

	/* Accessors */
	public String getColor(){
		return this.color;
	}

	public double getRent(){
		return this.rent;
	}

	public double getOneHouse(){
		return this.oneHouse;
	}

	public double getTwoHouse(){
		return this.twoHouse;
	}

	public double getThreeHouse(){
		return this.threeHouse;
	}

	public double getFourHouse(){
		return this.fourHouse;
	}

	/* Mutators */
	public void setColor(String color){
		this.color = color;
	}

	public void setRent(double rent){
		this.rent = rent;
	}

	public void setOneHouse(double oneHouse){
		this.oneHouse = oneHouse;
	}

	public void setTwoHouse(double twoHouse){
		this.twoHouse = twoHouse;
	}

	public void setThreeHouse(double threeHouse){
		this.threeHouse = threeHouse;
	}

	public void setFourHouse(double fourHouse){
		this.fourHouse = fourHouse;
	}

	/* Other Methods */

    /* 
     * 	addHouse(Player player)
     *	Add a house to the property and deduct the cost from
     * 	the players wallet.
     */ 
	public void addHouse(Player player){
		for (int i = 0; i < ARRAY_SIZE; i++){
			if (!houses[i]){
				switch(i){
					case 0:
						setRent(oneHouse);
						break;
					case 1:
						setRent(twoHouse);
						break;						
					case 2:
						setRent(threeHouse);
						break;
					case 3:
						setRent(fourHouse);
						break;
				}
				houses[i] = true;				
				System.out.print("House purchased.");
				return;
			}
		}
		System.out.println("Property full");
	}

    /* 
     * 	getNumHouses()
	 *  Returns the number of houses on this property.
     */ 
    public int getNumHouses(){
        int count = 0;
        for (int i = 0; i < ARRAY_SIZE; i++){
			if (houses[i])
				count++;
        }
        return count;
    }
    
    /* 
     * 	action(Player player)
     *	If the property is not owned, prompts the player to buy it.
     * 	Otherwise, deducts the rent from the owner and adds the money to 
     *	the owner of the property.
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
                	player.addProperty(this);
                    player.deductFromWallet(this.price);
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