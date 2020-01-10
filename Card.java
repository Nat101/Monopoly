/* 
 * 	Card.java
 * 	Contributors: Raphael Clemente
 * 				  Natalie Carlson
 */

public class Card{
	/* Instance Variables */
	private String name;

	/* Default Constuctor */
	public Card(){
		this.name = "default";
	}

	/* Overloaded Constructor */
	public Card(String name){
		this.name = name;
	}

	/* Accessors */
	public String getName(){
		return this.name;
	}

	/* Mutators */
	public void setName(String name){
		this.name = name;
	}
}