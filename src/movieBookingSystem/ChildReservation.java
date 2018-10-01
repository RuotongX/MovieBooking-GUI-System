package movieBookingSystem;
/**
 * This is the subclass for seatreservation which set the price value for child.
 * @author 16921494
 *
 */

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
