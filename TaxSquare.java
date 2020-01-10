/* 
 *  TaxSquare.java
 *  Contributors: Raphael Clemente
 *                Natalie Carlson
 */

public class TaxSquare extends Square{
	/* Instance Variables */
	private double tax;
	
	public TaxSquare(String name, Square next, double tax){
		super(name, next);
		this.tax = tax;
	}

	/* Accessors */
	public double getTax(){
		return this.tax;
	}

	/* Mutators */
	public void setTax(double tax){
		this.tax = tax;
	}
    
    /* Other Methods */

    /* 
     * 	action(Player player)
     *	Deducts tax from players wallet.
     */ 
    @Override
    public void action(Player player){
        System.out.println("Taxes due: " + this.tax);
        player.deductFromWallet(this.tax);
    }
}
