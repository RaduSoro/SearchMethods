public class Main {
    public static Grid grid = new Grid();
    public static String[][] startState = grid.getStartState();
    public static String[][] goalState = grid.getGoalState();

    public static void main(String args[])
    {
        grid.printGrid(goalState);
    }
}
