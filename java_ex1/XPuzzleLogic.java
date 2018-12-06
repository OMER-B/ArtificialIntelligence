import java.util.ArrayList;
import java.util.List;

/**
 * A logic for the XPuzzle. This logic is based on inserting to the lists by the order of UP, DOWN, LEFT, RIGHT and
 * returns a list with the next possible states, given an initial state.
 */
public class XPuzzleLogic implements Logic {
    private enum Direction {UP, DOWN, RIGHT, LEFT}

    private char[] letters = {'U', 'D', 'R', 'L'};
    private int boardSize;
    private int empty_i, empty_j;

    /**
     * Constructor for XPuzzleLogic.
     *
     * @param boardSize size of board.
     */
    public XPuzzleLogic(int boardSize) {
        this.boardSize = boardSize;
    }

    @Override
    public List<State> getNextStates(State initialState) {
        List<State> result = new ArrayList<>();
        int[][] board = initialState.getState();
        this.empty_i = 0;
        this.empty_j = 0;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (board[i][j] == 0) { // If [i][j] is 0, it's the empty cell, so save its i, j.
                    this.empty_i = i;
                    this.empty_j = j;
                }
            }
        }
        if (this.empty_i < boardSize - 1) result.add(getDirectionalState(initialState, Direction.UP)); // Can move up
        if (this.empty_i > 0) result.add(getDirectionalState(initialState, Direction.DOWN)); // Can move down
        if (this.empty_j < boardSize - 1) result.add(getDirectionalState(initialState, Direction.LEFT));// Can move left
        if (this.empty_j > 0) result.add(getDirectionalState(initialState, Direction.RIGHT)); // Can move right

        return result;
    }

    /**
     * Swap between the empty cell and the cell to its given direction.
     *
     * @param currentState Given State.
     * @param direction    Direction to swap with.
     * @return State with the cells swapped.
     */
    private State getDirectionalState(State currentState, Direction direction) {
        int i = 0, j = 0;
        switch (direction) { // Set the i, j based on given enum.
            case UP:
                i = this.empty_i + 1;
                j = this.empty_j;
                break;
            case DOWN:
                i = this.empty_i - 1;
                j = this.empty_j;
                break;
            case LEFT:
                i = this.empty_i;
                j = this.empty_j + 1;
                break;
            case RIGHT:
                i = this.empty_i;
                j = this.empty_j - 1;
                break;
            default:
                break;
        }

        State nextState = new State(currentState); // Copy constructor for the given state.
        int temp = nextState.getState()[this.empty_i][this.empty_j];
        nextState.getState()[this.empty_i][this.empty_j] = nextState.getState()[i][j];
        nextState.getState()[i][j] = temp;

        /* For traceback: */
        nextState.setLetter(this.letters[direction.ordinal()]); // Set the letter
        nextState.setPreviousState(currentState); // Set the pervious state
        nextState.setCost(nextState.getPreviousState().getCost() + 1);

        return nextState;
    }
}
