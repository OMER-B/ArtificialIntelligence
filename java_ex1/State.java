/**
 * A state class that represents a node in the graph. Every states holds the current board, its parent state, the depth,
 * the letter that got to the state.
 */
public class State {
    private int[][] state;
    private State previousState;
    private int depth;
    private char letter;
    private int stateSize;

    /**
     * Copy constructor of state.
     *
     * @param currentState state to copy from.
     */
    public State(State currentState) {
        this.stateSize = currentState.stateSize; // Size between states is the same.
        this.state = new int[stateSize][stateSize];
        for (int i = 0; i < stateSize; i++)  // Copy the array
            System.arraycopy(currentState.state[i], 0, this.state[i], 0, stateSize);
        this.depth = currentState.depth;
        this.letter = currentState.letter;
        if (currentState.previousState != null) { // If a previous state exists
            this.previousState = new State(currentState.previousState);
            this.depth = this.previousState.depth + 1; // This state's depth is the previous state's depth + 1.
        }
    }

    /**
     * Constructor for state (first state uses this constructor).
     *
     * @param state array of starting state
     * @param size  stateSize of array
     */
    public State(int[][] state, int size) {
        this.stateSize = size;
        this.state = state;
        this.depth = 0;
    }

    /**
     * Override for Object.equals.
     *
     * @param other State to compare with.
     * @return true if equal, false otherwise.
     */
    public boolean equals(State other) {
        for (int i = 0; i < this.stateSize; i++) {
            for (int j = 0; j < this.stateSize; j++) {
                if (this.state[i][j] != other.state[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.stateSize; i++) {
            for (int j = 0; j < this.stateSize; j++) {
                result.append(Integer.toString(this.state[i][j])).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Getter for size
     *
     * @return the state size.
     */
    public int getSize() {
        return stateSize;
    }

    /**
     * Getter for the 2D array of the state.
     *
     * @return the [][] representation of the state.
     */
    public int[][] getState() {
        return state;
    }

    /**
     * Setter for the letter of the state.
     *
     * @param c set the state's letter to param.
     */
    public void setLetter(char c) {
        this.letter = c;
    }

    /**
     * Setter for previous state.
     *
     * @param s parent state
     */
    public void setPreviousState(State s) {
        this.previousState = s;
    }

    /**
     * Setter for state.
     *
     * @param state int[][] of state.
     */
    public void setState(int[][] state) {
        this.state = state;
    }

    /**
     * Getter for previous state.
     *
     * @return parent state.
     */
    public State getPreviousState() {
        return previousState;
    }

    /**
     * Getter for state's cost.
     *
     * @return cost of the state.
     */
    public int getCost() {
        return depth;
    }

    /**
     * Setter for the cost.
     *
     * @param cost cost of the state.
     */
    public void setCost(int cost) {
        this.depth = cost;
    }

    /**
     * Getter for the letter.
     *
     * @return letter of the state.
     */
    public char getLetter() {
        return letter;
    }
}
