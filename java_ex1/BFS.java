import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

/**
 * BFS searcher algorithm. A type of searcher that uses a simple BFS algorithm, using a queue, to the determine the
 * optimal route to the target state of the board.
 */
public class BFS implements Searcher {
    private State finalState;
    private int size = 0;

    @Override
    public State Search(Logic logic, State startingState, State goalState) {
        LinkedList<State> queue = new LinkedList<>();
        queue.push(startingState);
        State currentState;
        while (!queue.isEmpty()) {
            currentState = queue.pop();
            this.size++;
//            System.out.println(currentState);
            if (currentState.equals(goalState)) {
                this.finalState = currentState;
                return currentState;
            }// Return final state for traceback
            queue.addAll(logic.getNextStates(currentState));
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getPath() {
        StringBuilder result = new StringBuilder("");
        State temp = this.finalState;
        while (temp.getPreviousState() != null) {
            result.append(temp.getLetter());
            temp = temp.getPreviousState();
        }
        result.reverse();
        return result.toString();
    }
}
