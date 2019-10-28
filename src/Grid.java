import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Grid {

    public Grid parent;
    public String[][] grid;
    public String actor;
    public int depth;
    public String move;
    public ArrayList<Grid> children;
    public Grid(String[][] grid, String actor){
        this.parent = null;
        this.grid = grid;
        this.actor = actor;
        depth = 0;
        move = "";
        children = new ArrayList<>();
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

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public ArrayList<String> getRandomAllowedMovement(){
        ArrayList<String> allowedMovements = getActorAllowedMovement();
        Collections.shuffle(allowedMovements);
        return getRandomAllowedMovement() ;
    }
    // returns an array with the position of the searched element

    public String getGridUniqueID(){
        String ID = "";
        ID = ID + getElementPosition("A")[0]+getElementPosition("A")[1]+"A";
        ID = ID + getElementPosition("B")[0]+getElementPosition("B")[1]+"B";
        ID = ID + getElementPosition("C")[0]+getElementPosition("C")[1]+"C";
        ID = ID + getActorPosition()[0]+getActorPosition()[1]+actor;
        return ID;
    }

    public int[] getActorPosition(){
        for (int i = 0; i<4; i++){
            for (int j = 0; j<4;j++){
                if (grid[i][j].equals(actor)) return new int[]{i, j};
            }
        }
        return null;
    }

    // |x1-x2|+|y1-y2|
    public Integer getManhattanDistance(String needle){
        //IDEAL POSITIONS
        int[] aIdealPosition = new int[]{1,1};
        int[] bIdealPosition = new int[]{2,1};
        int[] cIdealPosition = new int[]{3,1};
        int[] actualPosition = getElementPosition(needle);

        switch (needle){
            case "A":
                return Math.abs(actualPosition[0]-aIdealPosition[0])+ Math.abs(actualPosition[1]-aIdealPosition[1]);
            case "B":
                return Math.abs(actualPosition[0]-bIdealPosition[0])+ Math.abs(actualPosition[1]-bIdealPosition[1]);
            case "C":
                return Math.abs(actualPosition[0]-cIdealPosition[0])+ Math.abs(actualPosition[1]-cIdealPosition[1]);
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
