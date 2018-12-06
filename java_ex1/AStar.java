import java.util.*;

/**
 * A* searcher algorithm. A type of searcher that uses a heuristic function to determine the optimal route to the target
 * state of the board. It uses a priority quueue.
 */
public class AStar implements Searcher {
    private HeuristicState finalState;
    private ManhattanDistance heuristic = new ManhattanDistance();
    private int size = 0;

    @Override
    public State Search(Logic logic, State startingState, State goalState) {
        HeuristicState startingHeuristicState = new HeuristicState(startingState);
        HeuristicState goalHeuristicState = new HeuristicState(goalState);
        PriorityQueue<HeuristicState> queue = new PriorityQueue<>();
        queue.add(startingHeuristicState);
        HeuristicState currentHeuristicState;
        while (!queue.isEmpty()) {
            currentHeuristicState = queue.remove();
            this.size++;
            currentHeuristicState.setPriority(this.size);
//            System.out.println("#" + this.size);
//            System.out.println(currentHeuristicState);
            if (currentHeuristicState.getState().equals(goalHeuristicState.getState())) {
                this.finalState = currentHeuristicState;
                return this.finalState.getState();
            } // Return final state for traceback
            List<State> successors = logic.getNextStates(currentHeuristicState.getState());
            for (State s : successors) {
                HeuristicState hs = new HeuristicState(s);
                hs.setParent(currentHeuristicState);
                hs.setHeuristicCost(this.heuristic.getCost(currentHeuristicState, goalHeuristicState));
                queue.add(hs);
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCost() {
        HeuristicState temp = this.finalState;
        int cost = 0;
        while (temp.getParent() != null) {
            temp = temp.getParent();
            cost++;
        }
        return cost;
    }

    @Override
    public String getPath() {
        StringBuilder result = new StringBuilder("");
        State temp = this.finalState.getState();
        while (temp.getPreviousState() != null) {
//            System.out.println(temp);
            result.append(temp.getLetter());
            temp = temp.getPreviousState();
        }
        result.reverse();
        return result.toString();
    }
}
