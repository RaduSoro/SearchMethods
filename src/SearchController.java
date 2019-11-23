import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchController {
    private String actor = "•";

    private String[][] startState = {{"#","#","#","#"},{"#","#","#","#"},{"#","#","#","#"},{"A","B","C",actor}};
//    private String[][] goalState = {{"#","#","#","#"},{"#","A","#","#"},{"#","B","#","#"},{"#","C","#",actor}};
//    private String[][] testState =  {
//            {"#","#","#","#"},
//            {"#","A","#","#"},
//            {"#",actor,"B","#"},
//            {"#","C","#","#"}
//    };
    public SearchMethods sm;
    ArrayList<Integer> nodesGenerated = new ArrayList<>();
    public void doIterativeDeepening(){
        Grid gridStart = new Grid(startState,actor);
        sm = new SearchMethods(gridStart);
        ArrayList<Grid> test = sm.IDS(20);
        for (Grid gridS: test) {
            System.out.println(gridS.manhattanScore);
            gridS.printGrid();
//            System.out.println(gridS.depth);
        }
        Grid lastGrid = test.get(0);
        System.out.println(lastGrid.getManhattanDistance("A")+"   A");
        System.out.println(lastGrid.getManhattanDistance("B")+"   B");
        System.out.println(lastGrid.getManhattanDistance("C")+"   C");
        System.out.println(lastGrid.manhattanScore+"   GRID");
        //TODO add manhattan disance on root.
//        System.out.println(lastGrid.getManhattanScore()+"   GRID");
    }

    public void doAStar(){
        Grid gridStart = new Grid(startState,actor);
        sm = new SearchMethods(gridStart);
        ArrayList<Grid> test = sm.aStarSeach();
        for (Grid gridS: test) {
            System.out.println(gridS.depth);
            gridS.printGrid();
//            System.out.println(gridS.depth);
        }
//        Grid lastGrid = test.get(0);
//        System.out.println(lastGrid.getManhattanDistance("A")+"   A");
//        System.out.println(lastGrid.getManhattanDistance("B")+"   B");
//        System.out.println(lastGrid.getManhattanDistance("C")+"   C");
//        System.out.println(lastGrid.manhattanScore+"   GRID");
//        System.out.println(lastGrid.getManhattanScore()+"   GRID");
    }

    public void doDFS(){
     Grid gridStart = new Grid(startState,actor);
        for (int i=0; i<= 250; i++){
            sm = new SearchMethods(gridStart);
            ArrayList<Grid> path = sm.dfs();
            nodesGenerated.add(sm.nodesGenerated);
            System.out.println(i);
            path =null;
            sm = null;
            if (i%50==0) System.gc();
        }
        System.out.println(calculateAverage(nodesGenerated));
        Collections.sort(nodesGenerated);
        System.out.println("Smallest value "+ nodesGenerated.get(0));
        System.out.println("Biggest value "+ nodesGenerated.get(250));
    }

    private double calculateAverage(List<Integer> marks) {
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
