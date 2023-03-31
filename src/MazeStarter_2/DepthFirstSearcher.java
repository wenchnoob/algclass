package MazeStarter_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

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

		State first = new State(this.maze.getPlayerSquare(), null, 0, 0);
		LinkedList<State> stack = new LinkedList<State>();
		stack.offerFirst(first);
	
		while (!stack.isEmpty() ) {
			this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, stack.size());
			State cur = stack.pollFirst();
			this.maxDepthSearched = Integer.max(this.maxDepthSearched, cur.getDepth());
			explored[cur.getY()][cur.getX()] = true;

			if (cur.isGoal(this.maze)) {
				this.cost = cur.getGValue();

				cur = cur.getParent();
				while(cur.getParent() != null) {
					maze.setOneSquare(cur.getSquare(), '.');
					cur = cur.getParent();
				}
				return true;
			}

			this.noOfNodesExpanded += 1;
			ArrayList<State> successors = cur.getSuccessors(explored, this.maze);
			Collections.reverse(successors);
			for (State s: successors) {
				stack.offerFirst(s);
			}
		}

		return false;
	}
}
