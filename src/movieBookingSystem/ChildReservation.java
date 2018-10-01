package movieBookingSystem;

public class ChildReservation extends SeatReservation {
	public ChildReservation(char row,int col) {
		super(row,col);
	}
	public float getTicketPrice() {
		if(super.complementary==true) {
			return 0;
		}
		else {
		    return (float) 8;
		}
	}
	public char getType() {
		return 'C';
	}
}
