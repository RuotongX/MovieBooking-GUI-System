package movieBookingSystem;

public class AdultReservation extends SeatReservation{
	public AdultReservation(char row,int col) {
		super(row,col);
	}
	public float getTicketPrice() {
		if(super.complementary==true) {
			return 0;
		}
		else {
		    return (float) 12.50;
		}
	}
	public char getType() {
		return 'A';
	}
}
