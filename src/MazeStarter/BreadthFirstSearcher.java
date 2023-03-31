package MazeStarter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Breadth-First Search (BFS)
 * 
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public BreadthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main breadth first search algorithm.
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
			State cur = stack.removeLast();
			this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, stack.size());
			if (checkAndExpand(explored, stack, cur)) return true;
		}

		return false;
	}
}
