package Algo;
import java.util.Comparator;

/**
 * Implements {@link Comparator}
 * Sort {@link LineAlgo2} by its PI.
 * @author Gal
 *
 */
public class sortByPI implements Comparator<LineAlgo2> {

	@Override
	public int compare(LineAlgo2 o1, LineAlgo2 o2) {
		if(o1.p < o2.p)return 1;
		else if(o1.p > o2.p) return -1;
		else return 0;
		
	}
}
