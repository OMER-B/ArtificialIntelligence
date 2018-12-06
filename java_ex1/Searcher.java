/**
 * A searcher interface for types of searchers, such as A*, IDS, BFS, etc.
 */
public interface Searcher {
    /**
     * Searcher algorithm.
     *
     * @param logic         logic to search by.
     * @param startingState starting state of the board.
     * @param goalState     goal state of the board.
     * @return returns the final state of the board.
     */
    State Search(Logic logic, State startingState, State goalState);

    /**
     * Returns size of the closed list.
     *
     * @return size of closed list.
     */
    int getSize();

    /**
     * Returns cost to develop all the states.
     *
     * @return cost of development.
     */
    int getCost();

    /**
     * Returns a string of the path to the final state.
     *
     * @return path to state.
     */
    String getPath();
}
