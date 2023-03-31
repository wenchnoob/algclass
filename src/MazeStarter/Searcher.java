package MazeStarter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Abstract searcher class. 
 * 
 * You do not need to change this class.
 */
public abstract class Searcher {
	protected Maze maze;
	protected int cost = 0;
	protected int noOfNodesExpanded = 0;
	protected int maxDepthSearched = 0;
	protected int maxSizeOfFrontier = 0;

	/**
	 * Descendants of this class must implement this method.
	 * 
	 * @return true is the search finds a solution, false otherwise.
	 */
	public abstract boolean search();

	/**
	 * @param maze
	 *            the initial maze
	 */
	public Searcher(Maze maze) {
		this.maze = maze;
	}

	/**
	 * The total cost (distance) is maintained during the search process.
	 * 
	 * @return the cost of the solution.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * The number of nodes expanded is maintained during the search process.
	 * 
	 * @return the number of nodes expanded
	 */
	public int getNoOfNodesExpanded() {
		return noOfNodesExpanded;
	}

	/**
	 * The maximum depth of the search tree reached is maintained during the
	 * search process.
	 * 
	 * @return the maximum depth searched
	 */
	public int getMaxDepthSearched() {
		return maxDepthSearched;
	}

	/**
	 * Maximum size of the frontier seen is maintained during the search
	 * process.
	 * 
	 * @return the maximum size of the frontier list at any point during the
	 *         search
	 */
	public int getMaxSizeOfFrontier() {
		return maxSizeOfFrontier;
	}

	/**
	 * When search finds a solution, it modifies the given maze.
	 * 
	 * @return the maze with a “.” in each square that is part of the solution
	 *         path
	 */
	public Maze getModifiedMaze() {
		return maze;
	}

	protected boolean checkAndExpand(boolean[][] explored, LinkedList<State> stack, State cur) {
		this.maxDepthSearched = Integer.max(this.maxDepthSearched, cur.getDepth());
		this.noOfNodesExpanded++;
		explored[cur.getX()][cur.getY()] = true;

		if (cur.isGoal(maze)) {
			cur = cur.getParent();
			this.cost++;
			while(Objects.nonNull(cur) && Objects.nonNull(cur.getParent())) {
				maze.setOneSquare(cur.getSquare(), '.');
				this.cost++;
				cur = cur.getParent();
			}

//			for (int i = 0; i < maze.getNoOfRows(); i++) {
//				for (int j = 0; j < maze.getNoOfCols(); j++) {
//					char v = maze.getSquareValue(i, j);
//					if (v != 'S' && v != 'G' && v != '.' && explored[i][j]) {
//						maze.setOneSquare(new Square(i, j), 'X');
//					}
//				}
//			}

			return true;
		}

		ArrayList<State> succesors = cur.getSuccessors(explored, maze);
		for (State s: succesors) {
			stack.push(s);
		}
		return false;
	}
}
