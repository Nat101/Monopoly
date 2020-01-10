/* 
 *  Square.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

public class Square{
	/* Instance Variables */
	protected String name;
	protected Square next;

	/* Default Constructor */
	public Square(){
		this.name = "default";
		this.next = null;
	}

	/* Overloaded Constructor */
	public Square(String name, Square next){
		this.name = name;
		this.next = next;
	}

	/* Accessors */
	public String getName(){
		return this.name;
	}

	public Square getNext(){
		return this.next;
	}

	/* Mutators */
	public void setName(String name){
		this.name = name;
	}

	public void setNext(Square next){
		this.next = next;
	}
   	
   	/* Other Methods */
    public void action(Player player){
        
    }
}