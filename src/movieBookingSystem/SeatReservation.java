package movieBookingSystem;

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
