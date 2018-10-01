package movieBookingSystem;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This class is used to create a movieSession arraylist to store each type of array
 * @author 16921494
 *
 */


public class SimpleDriver{
	ArrayList movielist;
	Time t1 = new Time(10,0,0);
	Time t2 = new Time(11,30,0);
	Time t3 = new Time(15,0,0);
	Time t4 = new Time(20,15,0);
	/**
	 * This is the constructor of this class which create many different movieSession object
	 */
	public SimpleDriver() {
		this.movielist = new ArrayList();
		MovieSession ironman = new MovieSession("Iron man",'G',t1);
		MovieSession logen = new MovieSession("Logen",'R',t3);
		MovieSession danger = new MovieSession("Danger",'M',t4);
		MovieSession antman = new MovieSession("AntMan",'G',t2);
		MovieSession batman = new MovieSession("BatMan",'G',t4);
		movielist.add(ironman);
		movielist.add(logen);
		movielist.add(antman);
		movielist.add(batman);
		movielist.add(danger);
	}
	/**
	 * This method use the Collections.sort which use the comparable interface.
	 * @param aMovieSession
	 */
	public void addMovieSession(MovieSession aMovieSession) {
		this.movielist.add(aMovieSession);
		this.sort();
	}

	/**
	 * This method is used to delete a MovieSession by the number of the MovieSession in the array
	 * list.
	 * 
	 * @param index
	 *            number
	 */
	public void removeMovieSessionItem(int index) {
		this.movielist.remove(index);
	}

	/**
	 * This method is used to get the MovieSession array by rewrite the array list into a normal array.
	 * 
	 * @return array
	 */
	public MovieSession[] getMovieSessionList() {
		MovieSession[] array = new MovieSession[this.movielist.size()];

		for (int i = 0; i < array.length; i++) {
			array[i] = (movieBookingSystem.MovieSession) this.movielist.get(i);
		}

		return array;
	}

	/**
	 * This method used to check the MovieSession array has the input MovieSession or not, if it
	 * has, return true.
	 * 
	 * @param aMovieSession
	 *            a MovieSession object.
	 * @return
	 */
	public boolean hasMovieSessionItem(MovieSession aMovieSession) {
		return this.movielist.contains(aMovieSession);
	}

	/**
	 * This method is used to clear the MovieSession array so that no MovieSession storage in this
	 * array.
	 */
	public void clear() {
		this.movielist.clear();
	}
	public void sort() {
		Collections.sort(movielist);
	}

}
