package MazeStarter_2;

/**
 * A data-structure to keep (state,fValue) pairs. This class will helpful to add
 * states to priority queues. You can add a state with the f() value to a queue
 * by adding (state,f() value) pair to the priority queue.
 * 
 * You do not need to change this class!!!!!!!
 */
public class StateFValuePair implements Comparable<StateFValuePair> {
	private State state;
	private double fValue;

	public StateFValuePair(State state, Square goal) {
		this.state = state;
		this.fValue = state.getGValue() + Math.sqrt(Math.pow(state.getX() - goal.X, 2) + Math.pow(state.getY() - goal.Y, 2));
	}

	/**
	* Constructor
	* @param state that contains the coordinate and gValue 
	* @param fValue it is f() = g() + h()
	*/
	public StateFValuePair(State state, double fValue) {
		this.state = state;
		this.fValue = fValue;
	}
	/** 
	* @return state (coordinate and gValue instate)
	*/
	public State getState() {
		return state;
	}
	/**
	* @return fValue  f() = g() + h()
	*/
	public double getFValue() {
		return fValue;
	}

	@Override
	/**
	 * Compares StateFValuePairs
	 * @param o other StateFValuePair to compare to this
	 * @return if this fValues is less returns -1, if this is greater returns 1, if equal returns 0
	 */
	
	public int compareTo(StateFValuePair o) {
		if (this.fValue > o.fValue)
			return 1;
		else if (this.fValue < o.fValue)
			return -1;

		return 0;
	}
}
