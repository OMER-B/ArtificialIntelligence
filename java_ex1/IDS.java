import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * IDS searcher algorithm. A type of searcher that uses the DFS algorithm to determine the optimal route to the target
 * state of the board. It limits the DFS with a greater limit with each call to make it finite. uses a stack.
 */
public class IDS implements Searcher {
    private int size;
    private State finalState;
    private int limit;

    @Override
    public State Search(Logic logic, State startingState, State goalState) {
        State result;
        this.limit = 0;
        while (true) {
//            System.out.println("#" + limit + 1);
            result = DFS_L(logic, startingState, goalState); // DFS to the limit.
            if (result != null) return result; // Return result when found. DFS_L is complete so there is one.
            this.limit++; // Else increase the limit for next iteration.
        }
    }

    /**
     * DFS Algorithm
     *
     * @param logic
     * @param startingState
     * @param goalState
     * @param limit
     * @return
     */
    private State DFS_L(Logic logic, State startingState, State goalState) {
        this.size = 0;
        Stack<State> stack = new Stack<>();
        stack.clear();
        stack.push(startingState);
        State currentState;
        while (!stack.isEmpty()) {
            currentState = stack.pop();
            if (currentState.getCost() > this.limit) continue; // If the state's depth equals to the limit, ignore it
            this.size++;
//            System.out.println(currentState);
            if (currentState.equals(goalState)) {
                this.finalState = currentState;
                return this.finalState; // Return final state for traceback
            }
            List<State> list = new ArrayList<>();
            list = logic.getNextStates(currentState);
            Collections.reverse(list);
            stack.addAll(list); // Else check all of its next states
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCost() {
        return this.finalState.getCost();
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
