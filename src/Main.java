import java.util.ArrayList;

public class Main {
    private static String actor = "•";

    private static String[][] startState = {{"#","#","#","#"},{"#","#","#","#"},{"#","#","#","#"},{"A","B","C",actor}};
    private static String[][] goalState = {{"#","#","#","#"},{"#","A","#","#"},{"#","B","#","#"},{"#","C","#",actor}};
    private static String[][] testState =  {
            {"#","#","#","#"},
            {"#","A","#","#"},
            {"#",actor,"B","#"},
            {"#","C","#","#"}
    };
    public static Grid gridStart = new Grid(startState,actor);
    public static Grid gridFinish = new Grid(goalState,actor);
    public static Grid gridTest = new Grid(testState,actor);
    public static SearchMethods sm = new SearchMethods(gridStart);

    public static void main(String args[])
    {
//        gridStart.printGrid();
//        System.out.println(gridTest.getActorAllowedMovement());
//        ArrayList<Grid> generatedNodes = sm.expandNode(gridTest);
//        generatedNodes.forEach(grid -> {
//            grid.printGrid();
//            System.out.println();
//            System.out.println(sm.isGoalState());
//
//        });
        ArrayList<Grid> path = sm.bfs();
        ArrayList<String> sequence = new ArrayList<>();
        path.forEach(node ->{
            System.out.println(node.depth + " Depth of the below this node \n");
            sequence.add(node.move);
            System.out.println(node.move.toUpperCase() + " was the move made to get to this state \n");
            node.printGrid();
            System.out.println();
        });
        System.out.println(sequence);
    }
}
