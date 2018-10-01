package movieBookingSystem;
/**
 * This class is used to give the interface to the class which need to use the collections, the collections.sort will use this
 * interface method. And interface will be implement and the method will be overrride.
 * @author 16921494
 *
 * @param <E>
 */

public interface Comparable<E> {
	public int CompareTo(Object o);
}
