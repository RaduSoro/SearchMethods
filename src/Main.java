import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public static SearchMethods sm;

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
        ArrayList<Integer> nodesGenerated = new ArrayList<>();
        for (int i=0; i<= 300; i++){
            sm = new SearchMethods(gridStart);
            ArrayList<Grid> path = sm.dfs();
            nodesGenerated.add(sm.nodesGenerated);
            System.out.println(i);
            path =null;
            sm = null;
        }
        System.out.println(calculateAverage(nodesGenerated));
        Collections.sort(nodesGenerated);
        System.out.println("Smallest value "+ nodesGenerated.get(0));
        System.out.println("Biggest value "+ nodesGenerated.get(300));

//        path.forEach(node ->{
//            System.out.println(node.depth + " Depth of the below this node \n");
//            sequence.add(node.move);
//            System.out.println(node.move.toUpperCase() + " was the move made to get to this state \n");
//            node.printGrid();
//            System.out.println();
//        });
//        System.out.println(sequence);
    }
    private static double calculateAverage(List<Integer> marks) {
        Integer sum = 0;
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }
}
