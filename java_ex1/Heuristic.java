/**
 * Interface for heuristic functions. Has a function that returns the function's value, by a given initial state and
 * a goal state.
 */
public interface Heuristic {
    /**
     * Calculate the heuristic function and returns its value.
     *
     * @param initialState current state
     * @param goalState    goal state
     * @return value the function provided.
     */
    int getCost(HeuristicState initialState, HeuristicState goalState);
}
