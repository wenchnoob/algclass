package MazeStarter_2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 *
 * Worked with Grant on this.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {

		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();
		State first = new State(this.maze.getPlayerSquare(), null, 0, 0);
		frontier.offer(new StateFValuePair(first, maze.getGoalSquare()));

		while (!frontier.isEmpty()) {
			this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, frontier.size());
			State cur = frontier.poll().getState();
			this.maxDepthSearched = Integer.max(this.maxDepthSearched, cur.getDepth());
			explored[cur.getY()][cur.getX()] = true;

			if (cur.isGoal(this.maze)) {
				this.cost = cur.getGValue();
				this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, frontier.size());
				this.maxDepthSearched = Integer.max(this.maxDepthSearched, cur.getDepth());

				cur = cur.getParent();
				while(cur.getParent() != null) {
					maze.setOneSquare(cur.getSquare(), '.');
					cur = cur.getParent();
				}
				return true;
			}

			this.noOfNodesExpanded += 1;

			ArrayList<State> successors = cur.getSuccessors(explored, this.maze);
			for (State s: successors) {
				frontier.offer(new StateFValuePair(s, maze.getGoalSquare()));
			}
		}

		return false;
	}

}
