package MazeStarter_2;

import java.util.LinkedList;

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
		// FILL THIS METHOD

		// explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...
		State first = new State(this.maze.getPlayerSquare(), null, 0, 0);
		// Queue implementing the Frontier list
		LinkedList<State> queue = new LinkedList<State>();
		queue.offerLast(first);


		while (!queue.isEmpty()) {
			this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, queue.size());
			State cur = queue.pollFirst();
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

			for (State s: cur.getSuccessors(explored, this.maze)) queue.offerLast(s);
		}

		return false;
	}
}
