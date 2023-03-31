package MazeStarter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the BFS and DFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            explored[i][j] is true if (i,j) is already explored
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {
		ArrayList<State> successors = new ArrayList<>();
		State left = neighbor(explored, maze, -1, 0);
		State down = neighbor(explored, maze, 0, 1);
		State right = neighbor(explored, maze, 1, 0);
		State up = neighbor(explored, maze, 0, -1);

		if (Objects.nonNull(left)) successors.add(left);
		if (Objects.nonNull(down)) successors.add(down);
		if (Objects.nonNull(right)) successors.add(right);
		if (Objects.nonNull(up)) successors.add(up);

		return successors;
	}

	private State neighbor(boolean[][] explored, Maze maze, int xoff, int yoff) {
		int x = getX() + xoff;
		int y = getY() + yoff;

		char v = maze.getSquareValue(x, y);
		if (explored[x][y] || v == '%') return null;

		Square s = new Square(x, y);
		return new State(s, this, this.gValue + 1, this.depth + 1);
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (Objects.isNull(maze.getGoalSquare())) return false;

		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the BFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}

	public double getfValue(Square goal) {
		if (Objects.isNull(goal)) return Double.POSITIVE_INFINITY;
		return gValue + Math.sqrt(Math.pow(getX() - goal.X, 2) + Math.pow(getY() - goal.Y, 2));
	}
}
