package MazeStarter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
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

		Square start = maze.getPlayerSquare();
		State startState = new State(start, null, 0, 0);
		StateFValuePair startStateFValuePair = new StateFValuePair(startState, startState.getfValue(maze.getGoalSquare()));
		frontier.offer(startStateFValuePair);

		while (!frontier.isEmpty() ) {
			this.maxSizeOfFrontier = Integer.max(this.maxSizeOfFrontier, frontier.size());
			State cur = frontier.poll().getState();

			this.maxDepthSearched = Integer.max(this.maxDepthSearched, cur.getDepth());
			explored[cur.getX()][cur.getY()] = true;

			if (cur.isGoal(maze)) {
				cur = cur.getParent();
				this.cost++;
				while(Objects.nonNull(cur) && Objects.nonNull(cur.getParent())) {
					maze.setOneSquare(cur.getSquare(), '.');
					this.cost++;
					cur = cur.getParent();
				}

				return true;
			}

			this.noOfNodesExpanded++;
			ArrayList<State> succesors = cur.getSuccessors(explored, maze);
			for (State s: succesors) {
				frontier.offer(new StateFValuePair(s, s.getfValue(maze.getGoalSquare())));
			}
		}

		return false;
	}

}
