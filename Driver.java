/* 
 *  Driver.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

import java.util.Scanner;
import java.util.Random;

public class Driver{
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        
        int GAME_LIMIT = 10;
        int NUM_PLAYERS = 0;
        int diceRoll = 0;

        Monopoly m = new Monopoly();        
        Player[] players;


        /* Set the amount of players that will be playing */
        do{
            System.out.println("How many players? 2-5");
            NUM_PLAYERS = input.nextInt();
            input.nextLine();
        } while (NUM_PLAYERS < 2 || NUM_PLAYERS > 5);

        players = new Player[NUM_PLAYERS];

    	/* Initialize the players with their names and game pieces */
        for (int i = 0; i < NUM_PLAYERS; i++){
            System.out.print("Enter name for player # " + (i + 1) + ": ");
            players[i] = new Player(input.nextLine(), m.getRandomGamePiece(), m.getGo());

            System.out.println(players[i].getName() + " is the " + players[i].getGamePiece());
        }
        
        /* Loop through the game GAME_LIMIT times */
        for (int i = 0; i < GAME_LIMIT; i++){
        	/* Loop through the players */
            for (int j = 0; j < NUM_PLAYERS; j++){

            	/* Player is in jail, skip turn */
                if (players[j].getJail() == true){
                    System.out.println(players[j].getGamePiece() + " is in Jail.  Turned skipped.");
                    players[j].setJail(false);
                } else {
                    System.out.println("\n" + players[j].getName() + "'s turn.");

                    /* Prompt player to build houses on properties first */
                    players[j].haveRealEstate();

                    /* Player rolls dice */
                    diceRoll = rand.nextInt(12) + 1;

					/* Move the player diceRoll times */
                    m.movePlayer(players[j], diceRoll);

                    System.out.println( "Dice roll = " + diceRoll + ". " +
                            			players[j].getGamePiece() + " landed on " + 
                            			players[j].getLocation().getName() + " with " + 
                            			players[j].getWallet() + " dollars.");    

                    /* The different actions that can occur depending on what the player landed on */
                    switch(players[j].getLocation().getName()){

                    	/* Go does nothing because players wallet is updated in the movePlayer method */
                        case "GO":
                            break;

                        /* Calls the communityChest function and executes the specified action */
                        case "Community Chest":
                            m.communityChest(players[j]);
                            break;

                        /* Calls the chance function and executes the specified action */                            
                        case "Chance":
                            m.chance(players[j]);
                            break;

                        /* Does nothing because player landed on Just Visiting */                            
                        case "Jail/Just Visiting":
                            break;

                        /* Calls the freeParking function and executes the specified action */                            
                        case "Free Parking":
                            m.freeParking(players[j]);
                            break;

                        /* Calls the goToJail function and puts the player in jail */                            
                        case "Go to jail":
                            m.goToJail(players[j]);
                            break;

                        /* All other types of squares */                            
                        default:
                             players[j].getLocation().action(players[j]);
                             break;
                    }
                    System.out.println();
                }
            }         
        }
    }
}