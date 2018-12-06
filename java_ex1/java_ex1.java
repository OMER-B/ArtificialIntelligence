import java.io.IOException;

/**
 * Main function is here.
 */
public class java_ex1 {
    public static void main(String[] args) {
        final String INPUT_PATH = "input.txt";
        final String OUTPUT_PATH = "output.txt";
        Configuration cfg = new Configuration();
        cfg.loadFile(INPUT_PATH); // load the file to parser
        State initState = new State(cfg.getInitialState(), cfg.getBoardSize()); // set up initial state
        State goalState = new State(cfg.getGoalState(), cfg.getBoardSize()); // set up goal state
        Logic logic = new XPuzzleLogic(cfg.getBoardSize()); // new logic for X Puzzle
        cfg.getSearcher().Search(logic, initState, goalState); // get searcher from file
        try {
            String output = cfg.getSearcher().getPath() + " " + cfg.getSearcher().getSize() + " " + cfg.getSearcher().getCost(); // start searching
            cfg.writeToFile(OUTPUT_PATH, output); // write results
//            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}