import java.util.ArrayList;

public class Main {
    private static String actor = "•";

    private static String[][] startState = {{"#","#","#","#"},{"#","#","#","#"},{"#","#","#","#"},{"A","B","C",actor}};
    private static String[][] goalState = {{"#","#","#","#"},{"#","A","#","#"},{"#","B","#","#"},{"#","C","#",actor}};
    private static String[][] testState =  {
            {"#","#","#","#"},
            {"#","A","#","#"},
            {"#","B","#",actor},
            {"#","C","#","#"}
    };
    public static Grid gridStart = new Grid(startState,actor);
    public static Grid gridFinish = new Grid(goalState,actor);
    public static Grid gridTest = new Grid(testState,actor);
    public static SearchMethods sm = new SearchMethods(gridTest,gridFinish);

    public static void main(String args[])
    {
        gridTest.printGrid();
        System.out.println(gridTest.getActorAllowedMovement());
        ArrayList<Grid> generatedNodes = sm.expandNode(gridTest);
        generatedNodes.forEach(grid -> {
            grid.printGrid();
            System.out.println();
            System.out.println(sm.isGoalState());

        });
    }
}
