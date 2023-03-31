package MazeStarter_2;

/**
 * Main Application.
 * 
 * You do not need to change this class.
 */
public class FindPath {

	/**
	 * @param args
	 *            [0]: file name, [1]: astar (a-star), bfs (breadth first search), dfs (depth first search)
	 */
	public static void main(String[] args) {
		Maze maze = IO.readInputFile(args[0]);

		Searcher searcher;

		if (args[1].equals("astar")){
			searcher = new AStarSearcher(maze);
		}
		else if (args[1].equals("dfs")){
			searcher = new DepthFirstSearcher(maze);
		}
		else if (args[1].equals("bfs")){
			searcher = new BreadthFirstSearcher(maze);
		}
		else{
			System.out.println("Invalid search. Ending");
			searcher = null;
			System.exit(0);
		}
		if (searcher.search()) {
			IO.printOutput(searcher.getModifiedMaze(), searcher.getCost(),
					searcher.getNoOfNodesExpanded(),
					searcher.getMaxDepthSearched(),
					searcher.getMaxSizeOfFrontier());
		} else {
			System.out.println("No Solution");
		}
	}
}
