package movieBookingSystem;
/**
 * This is the subclass for seatreservation which set the price value for elderly.
 * @author 16921494
 *
 */

public class ElderlyReservation extends SeatReservation {
	public ElderlyReservation(char row,int col) {
		super(row,col);
	}
	public float getTicketPrice() {
		if(super.complementary==true) {
			return 0;
		}
		else {
			float temp = (float) (12.5*0.3);
		    return temp;
		}
	}
	public char getType() {
		return 'E';
	}
}
