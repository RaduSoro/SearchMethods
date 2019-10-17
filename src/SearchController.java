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
    public void doDFS(){
     Grid gridStart = new Grid(startState,actor);
        for (int i=0; i<= 300; i++){
            sm = new SearchMethods(gridStart);
            ArrayList<Grid> path = sm.dfs();
            nodesGenerated.add(sm.nodesGenerated);
            System.out.println(i);
            path =null;
            sm = null;
            if (i%10==0) System.gc();
        }
        System.out.println(calculateAverage(nodesGenerated));
        Collections.sort(nodesGenerated);
        System.out.println("Smallest value "+ nodesGenerated.get(0));
        System.out.println("Biggest value "+ nodesGenerated.get(300));
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
