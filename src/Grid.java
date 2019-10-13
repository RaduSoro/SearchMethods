import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    public Grid parent;
    public ArrayList<Grid> children;
    public String[][] grid;
    public String actor;
    public Grid(String[][] grid, String actor){
        this.parent = null;
        this.children = new ArrayList<>();
        this.grid = grid;
        this.actor = actor;
    }

    public String[][] getNewGrid(){
        String [][] newGrid = new String[4][4];
        for (int i = 0; i<4; i++){
            for (int j = 0; j<4;j++){
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }

    public void swapPositions(int[] newPosition){
        int[] actorPosition = this.getElementPosition(actor);
        String element = this.grid[newPosition[0]][newPosition[1]];
        this.grid[actorPosition[0]][actorPosition[1]] = element;
        this.grid[newPosition[0]][newPosition[1]] = actor;
    }

    public ArrayList<String> getActorAllowedMovement(){
        int[] position = getElementPosition(actor);
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

    public int[] getActorPosition(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<4;j++){
                if (grid[i][j].equals(actor)) return new int[]{i, j};
            }
        }
        return null;
    }

    private int[] getElementPosition(String searchElement){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<4;j++){
                if (grid[i][j].equals(searchElement)) return new int[]{i, j};
            }
        }
        return null;
    }

    public void printGrid() {
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
    }
}
