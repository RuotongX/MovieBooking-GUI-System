package movieBookingSystem;
/**
 * This is a abstract class which has adultReservation,childReservation and elderlyReservation three subclass
 * and also has the method get price method which can be used by these three subclass
 * The getType method is used to override by subclass, child return c, adult return a, elderly retrn e.
 * @author 16921494
 *
 */
public abstract class SeatReservation {
	private char row;
	private int col;
	protected boolean complementary = false;
	
	public SeatReservation(char row,int col) {
		this.row = row;
		this.col = col;
	}
	
	public float getTicketPrice() {
		return 0;
	}
	public void setComplementary(boolean complementary) {
		this.complementary = complementary;
	}
	public char getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public abstract char getType();
}
