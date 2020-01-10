/* 
 *  Monopoly.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Random;
import java.lang.String;
import java.io.FileNotFoundException;

public class Monopoly{
	/* Constant Variables */
	private final String BOARD_FILE = "squares.txt";
	private final String CHANCE_FILE = "chance.txt";
	private final String COMMUNITY_FILE = "community.txt";
    private final String GAMEPIECES_FILE = "pieces.txt";
	private final int ARRAY_SIZE = 5;    
	private final double GO_VALUE = 200.0;
	private final double DOCTOR_FEE = 100.0;
	private final double SCHOOL_FEE = 200.0;
	private final double PFD_FEE = 350.0;
	private final double LOTTERY_FEE = 1000.0;
	private final double FREEPARKING_FEE = 50.0;

	/* Instance Variables */
	private Square go;
	private Card[] chanceCards;
	private Card[] communityCards;
    private String[] gamePieces; 

    /* Default Constructor */
	public Monopoly(){
		this.go = null;
		this.chanceCards = new Card[ARRAY_SIZE];
		this.communityCards = new Card[ARRAY_SIZE];
        this.gamePieces = new String[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++){
			this.chanceCards[i] = null;
			this.communityCards[i] = null;
            this.gamePieces[i] = " ";
		}
		initBoard();
		initCards();
        initGamePieces();
	}

	/* Accessors */
	public Square getGo(){
		return this.go;
	}

	/* Mutators */
	public void setGo(Square go){
		this.go = go;
	}

	/* Other Methods */

	/* 
     * 	initBoard()
	 * 	Reads in BOARD_FILE and creates our circular linked list.
     */ 
	public void initBoard(){
		try{
			Scanner input = new Scanner(new FileInputStream(BOARD_FILE));
			Square prev = null;
			int type;

			while (input.hasNextLine()){
				String[] line = input.nextLine().split(",");
				type = Integer.parseInt(line[0]);

				if (go == null){
					go = new Square(line[1], go);
					prev = go;          
				} else {
					switch (type){
						/* Corner/Unique Squares */
						case 1:
							prev.setNext(new Square(line[1], go));
							prev = prev.getNext();
							break;
						/* Property Squares */
						case 2:
							prev.setNext(new PropertySquare(line[1], go,Double.parseDouble(line[3]),line[2], Double.parseDouble(line[4]),Double.parseDouble(line[5]),Double.parseDouble(line[6]),Double.parseDouble(line[7]),Double.parseDouble(line[8])));
							prev = prev.getNext();
							break;
						/* Tax Squares */
						case 3:
							prev.setNext(new TaxSquare(line[1], 
															go,
															Double.parseDouble(line[2])));
							prev = prev.getNext();
							break;
						/* Railroad Squares */
						case 4:
							prev.setNext(new RailroadSquare(line[1], 
																go, 
																Double.parseDouble(line[2]), 
																Double.parseDouble(line[3])));
							prev = prev.getNext();
							break;
						/* Utility Squares */
						case 5:
							prev.setNext(new UtilitySquare(line[1],
																go, 
																Double.parseDouble(line[2]),
																Double.parseDouble(line[3])));
							prev = prev.getNext();
							break;
					}
				}
			}

			input.close();

		} catch (FileNotFoundException e){
			System.out.println(e);
		}
	}

	/* 
     * 	initCards()
	 * 	Reads in CHANCE_FILE and COMMUNITY_FILE and puts them into our arrays.
     */ 
	public void initCards(){
		try{
			Scanner chanceInput = new Scanner(new FileInputStream(CHANCE_FILE));
			Scanner communityInput = new Scanner(new FileInputStream(COMMUNITY_FILE));

			for (int i = 0; i < ARRAY_SIZE; i++){
				chanceCards[i] = new Card(chanceInput.nextLine());
				communityCards[i] = new Card(communityInput.nextLine());
			}

			chanceInput.close();
			communityInput.close();

		} catch (FileNotFoundException e){
			System.out.println(e);
		}
	}
        
	/* 
     * 	initGamePieces()
	 * 	Reads in GAMEPIECES_FILE and puts them into our array.
     */         
    public void initGamePieces(){
		try{
			Scanner gamePiecesInput = new Scanner(new FileInputStream(GAMEPIECES_FILE));

			for (int i = 0; i < ARRAY_SIZE; i++)
				gamePieces[i] = gamePiecesInput.nextLine();

			gamePiecesInput.close();

		} catch (FileNotFoundException e){
			System.out.println(e);
		}
	}
        
	/* 
     * 	getRandomGamePiece()
	 * 	Returns a random game piece that has not been selected before.
     */     
	public String getRandomGamePiece(){
        Random rand = new Random();
        String piece;
        int num = 0;

        do {
            num = rand.nextInt(5);
        } while (gamePieces[num].equals("null"));

        piece = gamePieces[num];
        gamePieces[num] = "null";

        return piece;
    }

	/* 
     * 	movePlayer(Player player, int roll)
	 * 	Moves the player across the board by updating their current location.
	 *  Determined by dice roll.
     */ 
	public void movePlayer(Player player, int roll){
		Square currentSpot = player.getLocation();

		for (int i = 0; i < roll; i++){
			currentSpot = currentSpot.getNext();

			if (currentSpot == go){
				player.addToWallet(GO_VALUE);
                System.out.println(player.getGamePiece() + " passed go.");
            }
		}
		player.setLocation(currentSpot);
	}

	/* 
     * 	movePlayerToSpot(Player player, String target)
	 * 	Move the player to the specified square.
	 */ 
	public void movePlayerToSpot(Player player, String target){
		Square currentSpot = player.getLocation();

		while (!currentSpot.getName().equals(target)){
			currentSpot = currentSpot.getNext();

			if (currentSpot == go){
				player.addToWallet(GO_VALUE);
                System.out.println(player.getGamePiece() + " passed go.");
            }
		}
		player.setLocation(currentSpot);
	}

	/* 
     * 	movePlayerToSpecificSquareType(Player player, String squareType)
	 * 	Move the player to the specified square type.
	 */ 
	public void movePlayerToSpecificSquareType(Player player, String squareType){
		Square currentSpot = player.getLocation();

		while (!currentSpot.getClass().getName().equals(squareType)){
			currentSpot = currentSpot.getNext();

			if (currentSpot == go){
				player.addToWallet(GO_VALUE);
                System.out.println(player.getGamePiece() + " passed go.");
            }
		}
		player.setLocation(currentSpot);
	}

	/* 
     * 	communityChest(Player player)
	 * 	Picks a random card from the community array and executes that cards action.
	 */	
    public void communityChest(Player player){
      Random rand = new Random();
      int num = rand.nextInt(5);

      System.out.println(player.getGamePiece() + " drew \"" + communityCards[num].getName() +
      						"\" from the Community Cards.");

      switch (communityCards[num].getName()){
      	case "Advance to Go":
      		player.setLocation(go);
      		player.addToWallet(GO_VALUE);			
      		break;
      	case "Doctors Fees":
      		player.deductFromWallet(DOCTOR_FEE);
      		break;
      	case "Go to Jail":
			goToJail(player);
      		break;
       	case "School Fees":
      		player.deductFromWallet(SCHOOL_FEE);
      		break;
      	case "PFD":
      		player.addToWallet(PFD_FEE);
      		break;
      	}
    }

	/* 
     * 	chance(Player player)
	 * 	Picks a random card from the chance array and executes that cards action.
	 */	    
    public void chance(Player player){
      Random rand = new Random();
      int num = rand.nextInt(5);

      System.out.println(player.getGamePiece() + " drew \"" + chanceCards[num].getName() +
      						"\" from the Chance Cards.");

      switch (chanceCards[num].getName()){
      	case "Take A Walk On The Boardwalk":
   			movePlayerToSpot(player, "Boardwalk");
      		break;
      	case "Advance To Illinois Ave.":
   			movePlayerToSpot(player, "Illinois Avenue");
      		break;
      	case "Advance To Nearest Utility":
			movePlayerToSpecificSquareType(player, "UtilitySquare");
      		break;
       	case "Advance To Nearest Railroad":
			movePlayerToSpecificSquareType(player, "RailroadSquare");
      		break;
      	case "You Won The Lottery":
      		player.addToWallet(LOTTERY_FEE);
      		break;
      	}
    }

	/* 
     * 	freeParking(Player player)
	 * 	Adds the FREEPARKING_FEE to the players wallet.
	 */	    
    public void freeParking(Player player){
        player.addToWallet(FREEPARKING_FEE);
    }

   	/* 
     * 	goToJail(Player player)
	 * 	Moves the player to jail.
	 */	
    public void goToJail(Player player){
        player.setJail(true);

        Square currentSpot = player.getLocation();

        while(!currentSpot.getName().equals("Jail/Just Visiting")){
            currentSpot = currentSpot.getNext();
        }
        player.setLocation(currentSpot);
    }
        
    /* Debugging methods to verify circular linked list/arrays were initialized */
	public void printBoard(){
		Square current = go;
		System.out.println(current.getName());

		current = current.getNext();

		while (current != go){
			System.out.println(current.getName());
			current = current.getNext();
		}
	}

	public void printArrays(){
		for (int i = 0; i < ARRAY_SIZE; i++)
			System.out.println(chanceCards[i].getName());
		
		for (int i = 0; i < ARRAY_SIZE; i++)
			System.out.println(communityCards[i].getName());	

		for (int i = 0; i < ARRAY_SIZE; i++)
			System.out.println(gamePieces[i]);		
	}			
}