/**
 * A heuristic function of manhattan distance. Calculates the distance of two point by |x0-x1|+|y0-y1| for each cell,
 * and returns the summation of its values. It is a consistent and admissible heuristic function.
 */
public class ManhattanDistance implements Heuristic {
    @Override
    public int getCost(HeuristicState initialState, HeuristicState goalState) {
        int size = initialState.getState().getSize();
        int[][] goalMatrix = goalState.getState().getState();
        int[][] currentMatrix = initialState.getState().getState();
        int startingIndcies[] = new int[2];
        int goalIndcies[] = new int[2];
        int distance = 0;
        int i = 0, j = 0;
        for (int k = 1; k < size * size; k++) {
            startingIndcies = getIndicies(currentMatrix, k, size);
            goalIndcies = getIndicies(goalMatrix, k, size);
            distance += (Math.abs(startingIndcies[0] - goalIndcies[0]) + Math.abs(startingIndcies[1] - goalIndcies[1]));
        }
        return distance;
    }

    /**
     * Helper function for manhattan distance. returns indexes of a parameter number.
     *
     * @param matrix state of the board
     * @param num    num to find
     * @param size   size of the board.
     * @return int[] with i, j of the number in the matrix.
     */
    private int[] getIndicies(int[][] matrix, int num, int size) {
        int[] indicies = {Integer.MAX_VALUE, 0};
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == num) {
                    indicies[0] = i;
                    indicies[1] = j;
                    return indicies;
                }
            }
        }
        return indicies;
    }
}
