/* 
 *  Player.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player{
    /* Constant Variables */
	final double WALLET_SIZE = 1500.0;
    final double RAILROAD_INIT_RENT = 25.0;

    /* Instance Variables */
    private String name, gamePiece;
	private double wallet;
	private Square location;
    private boolean jail;
    private List<PropertySquare> properties = new ArrayList<>();
    private List<RailroadSquare> railroads = new ArrayList<>();

    /* Default Constructor */
	public Player(){
        this.name = "default";
		this.wallet = WALLET_SIZE;
		this.gamePiece = "default";
		this.location = null;
        this.jail = false;
	}

    /* Overloaded Constructor */
	public Player(String name, String gamePiece, Square location){
        this.name = name;
		this.wallet = WALLET_SIZE;
		this.gamePiece = gamePiece;
		this.location = location;
        this.jail = false;
	}

    /* Accessors */
    public String getName(){
        return this.name;
    }

	public double getWallet(){
		return this.wallet;
	}

    public String getGamePiece(){
        return this.gamePiece;
    }

    public Square getLocation(){
        return this.location;
    }

    public boolean getJail(){
        return this.jail;
    }

    /* Mutators */
    public void setName(String name){
        this.name = name;
    }

	public void addToWallet(double money){
		this.wallet = this.wallet + money;
	}

	public void deductFromWallet(double money){
		this.wallet = this.wallet - money;
	}

	public void setGamePiece(String gamePiece){
		this.gamePiece = gamePiece;
	}

	public void setLocation(Square location){
		this.location = location;
	}
        
    public void setJail(boolean jail){
        this.jail = jail;
    }
    
    /* Other Methods */

    /* 
     *  haveRealEstate()
     *  Cycle through the player's list of properties and prompt them
     *  with an option to buy a house for that property. 
     */
    public void haveRealEstate(){
        Scanner input = new Scanner(System.in);

        if (!properties.isEmpty()){
            for (PropertySquare property : properties){
                System.out.print("You own " + property.getName() + 
                                    ". Would you like to buy a house? Enter y or n: ");
                char answer = input.next().charAt(0);

                if (answer == 'Y' || answer == 'y'){
                    property.addHouse(this);
                    System.out.println(" This property now has " + 
                                        property.getNumHouses() + " houses.");
                }
            }        
        }
    }
        
    /* 
     *  addProperty(PropertySquare purchase)
     *  Add a property to the players property list. 
     */
    public void addProperty(PropertySquare purchase){
        this.properties.add(purchase);
    }

    /* 
     *  addRailroad(RailroadSquare railroad)
     *  Add a railroad to the player's railroad list.
     */
    public void addRailroad(RailroadSquare railroad){
    	this.railroads.add(railroad);
    	updateRailroads();
    }

    /* 
     *  updateRailroads()
     *  Update the rent of all owned railroads (if applicable).
     */
    public void updateRailroads(){
    	for(RailroadSquare railroad : railroads)
    		railroad.setRent(RAILROAD_INIT_RENT * railroads.size());
    }
}