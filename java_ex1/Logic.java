import java.util.List;

/**
 * An interface for logic types, that will provide the logic on how to get the next possible states.
 */
public interface Logic {
    /**
     * Returns a list of next possible states.
     *
     * @param initialState current state.
     * @return list of next possible states.
     */
    List<State> getNextStates(State initialState);
}
