import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchController {
    private String actor = "•";

    private String[][] startState = {{"#","#","#","#"},{"#","#","#","#"},{"#","#","#","#"},{"A","B","C",actor}};

    private String[][] debugStateExample2 = {{"#","#",actor,"#"},{"#","A","#","#"},{"B","#","#","#"},{"#","C","#","#"}};
    private String[][] debugStateExample1 = {{"#","#","#","#"},{"#","A","#","#"},{"B",actor,"#","#"},{"#","C","#","#"}};

    public SearchMethods sm;
    ArrayList<Integer> nodesGenerated = new ArrayList<>();
    ArrayList<Integer> nodesExpanded = new ArrayList<>();
    ArrayList<Integer> averageDepth = new ArrayList<>();

    public void doIterativeDeepening(){
        Grid gridStart = new Grid(startState,actor);
        sm = new SearchMethods(gridStart);
        ArrayList<Grid> result = sm.IDS(20);
        Grid lastGrid = result.get(0);
        result.forEach(grid -> {
            System.out.println();
            grid.printGrid();
            System.out.println(grid.generatedByMovement);
        });
//        System.out.println(lastGrid.getManhattanScore()+"   GRID");
    }

    public void doAStar(){
        Grid gridStart = new Grid(startState,actor);
        sm = new SearchMethods(gridStart);
        ArrayList<Grid> test = sm.aStarSeach();
//        Grid lastGrid = test.get(0);
//        System.out.println(lastGrid.getManhattanDistance("A")+"   A");
//        System.out.println(lastGrid.getManhattanDistance("B")+"   B");
//        System.out.println(lastGrid.getManhattanDistance("C")+"   C");
//        System.out.println(lastGrid.manhattanScore+"   GRID");
//        System.out.println(lastGrid.getManhattanScore()+"   GRID");
    }
    public void doBFS(){
        Grid gridStart = new Grid(startState,actor);
        sm = new SearchMethods(gridStart);
        ArrayList<Grid> test = sm.bfs();
//        Grid lastGrid = test.get(0);
//        System.out.println(lastGrid.getManhattanDistance("A")+"   A");
//        System.out.println(lastGrid.getManhattanDistance("B")+"   B");
//        System.out.println(lastGrid.getManhattanDistance("C")+"   C");
//        System.out.println(lastGrid.manhattanScore+"   GRID");
//        System.out.println(lastGrid.getManhattanScore()+"   GRID");
    }

    public void doDFS(){
     Grid gridStart = new Grid(startState,actor);
        for (int i=0; i<= 0; i++){
            sm = new SearchMethods(gridStart);
            ArrayList<Grid> path = sm.dfs();
            nodesGenerated.add(sm.nodesGenerated);
            nodesExpanded.add(sm.nodesExpanded);
            averageDepth.add(sm.solutionDepth);
            path =null;
            sm = null;
            if (i%50==0) System.gc();
        }
        System.out.println("Average for nodes generated " +calculateAverage(nodesGenerated));
        System.out.println("Average nodes expanded " +calculateAverage(nodesExpanded));
        System.out.println("Average depth " +calculateAverage(averageDepth));
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
