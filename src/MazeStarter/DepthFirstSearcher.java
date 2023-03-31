package MazeStarter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Depth-First Search (DFS)
 * 
 * You should fill the search() method of this class.
 */
public class DepthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public DepthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main depth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		LinkedList<State> stack = new LinkedList<State>();
		Square start = maze.getPlayerSquare();
		State startState = new State(start, null, 0, 0);
		stack.push(startState);

		while (!stack.isEmpty() ) {
			this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, stack.size());
			State cur = stack.pop();
			if (checkAndExpand(explored, stack, cur)) return true;
		}

		return false;
	}
}
