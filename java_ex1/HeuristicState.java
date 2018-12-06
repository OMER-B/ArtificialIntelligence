/**
 * A decorator class for State. Provides more elements that define a heuristic state, such as heuristic cost and
 * priority. Holds the decorator state and implements Comparable.
 */
public class HeuristicState implements Comparable {
    private State state;
    private int heuristicCost;
    private HeuristicState parent;
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Set heuristic parent.
     *
     * @param parent
     */
    public void setParent(HeuristicState parent) {
        this.parent = parent;
    }

    /**
     * Getter for heuristic parent.
     *
     * @return Heuristic parent.
     */
    public HeuristicState getParent() {
        return parent;
    }

    /**
     * Constructor for Heuristic State
     *
     * @param state State to decorate.
     */
    public HeuristicState(State state) {
        this.state = state;
    }

    /**
     * Getter for decorated state.
     *
     * @return Decorated state.
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for decorated state.
     *
     * @param state state to set.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter for heuristic cost.
     *
     * @return heuristic cost.
     */
    public int getHeuristicCost() {
        return this.heuristicCost;
    }

    /**
     * Setter for heuristic cost.
     *
     * @param heuristicCost heuristic cost to set.
     */
    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    /**
     * Getter for cost of the state.
     *
     * @return cost of the state.
     */
    public int getCost() {
        return this.state.getCost();
    }

    @Override
    public int compareTo(Object o) {
        HeuristicState other = (HeuristicState) o;
        int thisCost = this.heuristicCost + this.state.getCost();
        int otherCost = other.heuristicCost + other.state.getCost();
        return thisCost - otherCost;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
