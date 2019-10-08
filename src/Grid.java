import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    public Grid(){

    }
    private String actor = "ʕ•ᴥ•ʔ";
    private String[][] startState = {{"#","#","#","#"},{"#","#","#","#"},{"#","#","#","#"},{"A","B","C",actor}};
    private String[][] goalState = {{"#","#","#","#"},{"#","A","#","#"},{"#","B","#","#"},{"#","C","#",actor}};
    private String[][] testState =  {
                                    {"#","#","#","#"},
                                    {"#","A","#","#"},
                                    {"#","B","#",actor},
                                    {"#","C","#","#"}
                                    };

    public String[][] getStartState(){
        return startState;
    }
    public String[][] getGoalState(){
        return goalState;
    }

    public ArrayList<String> getAllowedMovement(int[] position){
        ArrayList<String> allowedMovements = new ArrayList<>(Arrays.asList("left","right","up","down"));
        int row = position[0];
        int col = position[1];
        if (row == 3) allowedMovements.remove("down");
        if (row == 0) allowedMovements.remove("up");
        if (col == 3) allowedMovements.remove("right");
        if (col == 0) allowedMovements.remove("left");
        return allowedMovements;
    }
    // returns an array with the position of the searched element
    private int[] getElementPosition(String [][] grid, String searchElement){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<4;j++){
                if (grid[i][j].equals(searchElement)) return new int[]{i, j};
            }
        }
        return new int[]{-1,-1};
    }

    public void printGrid(String[][] grid) {
        System.out.println(getElementPosition(testState,actor));
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
    }
}
