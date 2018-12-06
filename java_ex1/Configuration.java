import java.io.*;

import static java.lang.System.exit;

/**
 * Configuration is a helper class that sets up everything. Parses the initial given state, and creates the goal state
 * from it. Holds the searcher algorithm as well.
 */
public class Configuration {
    private Searcher searcher;
    private int boardSize;
    private int[][] initialState;
    private int[][] goalState;

    /**
     * Setter for searcher.
     *
     * @param searcher searcher.
     */
    public void setSearcher(int searcher) {
        if (searcher == 1) this.searcher = new IDS();
        else if (searcher == 2) this.searcher = new BFS();
        else if (searcher == 3) this.searcher = new AStar();
    }

    /**
     * Setter for board size.
     *
     * @param size board size.
     */
    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    /**
     * Set the initial state by string.
     *
     * @param initState initial state.
     */
    public void setInitialState(String initState) {
        this.initialState = new int[boardSize][boardSize];
        this.goalState = new int[boardSize][boardSize];

        int i = 0, j = 0, k = 0;
        int counter = 1;
        String data[] = initState.split("-");
        for (i = 0; i < boardSize; i++)
            for (j = 0; j < boardSize; j++) {
                this.goalState[i][j] = counter++;
                initialState[i][j] = Integer.parseInt(data[k++]);

            }
        if ((i) * (j) != this.boardSize * this.boardSize)
            exit(0);
        this.goalState[i - 1][j - 1] = 0;
    }

    /**
     * Getter for searcher.
     *
     * @return searcher.
     */
    public Searcher getSearcher() {
        return searcher;
    }

    /**
     * Getter for board size.
     *
     * @return board size.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Getter for initial state.
     *
     * @return initial state.
     */
    public int[][] getInitialState() {
        return initialState;
    }

    /**
     * getter for goal state.
     *
     * @return goal state.
     */
    public int[][] getGoalState() {
        return goalState;
    }

    /**
     * Load the file from given path.
     *
     * @param path path with info.
     */
    void loadFile(String path) {
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Read searcher type
            String st = br.readLine();
            setSearcher(Integer.parseInt(st));

            // Read board size
            st = br.readLine();
            setBoardSize(Integer.parseInt(st));

            // Read initial state
            st = br.readLine();
            setInitialState(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Save output to file.
     *
     * @param filename file to save to
     * @param output   string to save
     * @throws IOException exception
     */
    public void writeToFile(String filename, String output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(output);
        writer.close();
    }
}
