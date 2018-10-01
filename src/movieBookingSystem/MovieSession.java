package movieBookingSystem;
import java.util.ArrayList;

import javax.swing.JFrame;


public class MovieSession implements Comparable{
	private String movieName;
	private char rating = ' ';
	private Time sessionTime;
	private SeatReservation[][] sessionSeats;
	static int NUM_ROWS = 8;
	static int NUM_COLS = 6;
//	ArrayList<SeatReservation> reservations = new ArrayList<SeatReservation>();
	
	public MovieSession(String movieName,char rating,Time sessionTime) {
		this.movieName = movieName;
		this.rating = rating;
		this.sessionTime = sessionTime;
		sessionSeats = new SeatReservation[NUM_COLS][NUM_ROWS];
	}
	public static int convertRowToIndex(char rowLetter) {
		switch(rowLetter) {
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		}
		return -1;
	}
	public static char converIndexToRow(int rowIndex) {
		switch(rowIndex) {
		case 0:
			return 'A';
		case 1:
			return 'B';
		case 2:
			return 'C';
		case 3:
			return 'D';
		case 4:
			return 'E';
		case 5:
			return 'F';
		case 6:
			return 'G';
		case 7:
			return 'H';
		}
		return ' ';
	}
	public char getRating() {
		return this.rating;
	}
	public String getMovieName() {
		return movieName;
	}
	public Time getSessionTime() {
		return sessionTime;
	}
	public SeatReservation getSeats(char row,int col) {
		int r = this.convertRowToIndex(row);
		try {
			return sessionSeats[r][col];
		} catch(Exception e) {
			return null;
		}
	}
	public boolean isSeatAvailable(char row,int col) {
		int r = this.convertRowToIndex(row);
		if(this.sessionSeats[r][col]!=null) {
			return false;
		} else {
			return true;
		}
	}
	public boolean applyBookings(ArrayList reservations) {
		boolean child = false;
		int counter = 0;
		for(int j = 0;j<reservations.size();j++) {
			SeatReservation sr = (SeatReservation) reservations.get(j);
			if(sr.getType() == 'C'&&this.rating == 'M') {
				child = true;
			} else if(sr.getType() == 'A'&&this.rating == 'M'){
				counter++;
			} else if(sr.getType() == 'E'&&this.rating == 'M'){
				counter++;
			}
		}
		if(child == true && counter == 0) {
			return false;
		}
		for(int i =0;i<reservations.size();i++) {
			SeatReservation sr = (SeatReservation) reservations.get(i);
			int row = this.convertRowToIndex(sr.getRow());
			SeatReservation srn;
			try {
				srn = sessionSeats[row][sr.getCol()];
			} catch(Exception e) {
				srn = null;
			}
			if(sr.getType() == 'C'&&this.rating=='R') {
				System.out.println("Row: "+sr.getRow()+"Col: "+sr.getCol()+" is child can't watch the R rating movie. This ticket cancel");
				return false;
			} 
			else if(srn!=null) {
				System.out.println("Row: "+sr.getRow()+"Col: "+sr.getCol()+" has been booked, the booking fail");
				return false;
			} else {
				this.sessionSeats[row][sr.getCol()] = sr;
			}
		}
		
		return true;
	}
	public void printSeats() {
		for(int i = 0;i<this.NUM_ROWS;i++) {
			for(int j =0;j<this.NUM_COLS;j++) {
				char temp = '_';
				if (this.sessionSeats[i][j]!=null) {
					temp = this.sessionSeats[i][j].getType();
				}
				System.out.print("|"+temp+"| ");
			}
			System.out.println();
		}
		
	}
	public String toString() {
		return "Movie Name: "+this.movieName+"        Rating: "+this.rating+"        Session Time: "+this.sessionTime.toString();
	}
	public int CompareTo(Object o) {
		MovieSession m = (MovieSession) o;
		if(m.getSessionTime().timecalaulator()<this.getSessionTime().timecalaulator()) {
			return 1;
		}
		else if(m.getSessionTime().timecalaulator()==this.getSessionTime().timecalaulator()) {
			int a = m.getMovieName().charAt(0);
			int b= this.getMovieName().charAt(0);
			if(a<b) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
	
	public static int getNUM_ROWS() {
		return NUM_ROWS;
	}
	
	public static int getNUM_COLS() {
		return NUM_COLS;
	}
	
	public static void main(String[] args) {
		JFrame frame = new MovieBookingGUI();
		frame.setVisible(true);
	}
}
