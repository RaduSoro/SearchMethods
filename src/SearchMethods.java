import java.util.*;

public class SearchMethods {
    private Grid root;
    private Grid goal;
    private Grid currentNode;


    public SearchMethods(Grid root, Grid goal){
        this.root = root;
        this.goal = goal;
        this.currentNode = root;
    }


    public int bfs(){

        return 0;
    }

    public boolean isGoalState(){
        String[][] grid = currentNode.grid;
        if (grid[1][1].equals("A")&&grid[2][1].equals("B")&&grid[3][1].equals("C")) return true;
        return false;
    }

    public ArrayList<Grid> expandNode(Grid node){
        ArrayList<String> allowedMovements = node.getActorAllowedMovement();
        ArrayList<Grid> children = new ArrayList<>();
        Grid generatedNode;
        for (String movement: allowedMovements) {
            //generates a new node similar to parent, adds parent to parent property
            generatedNode = new Grid(node.getNewGrid(),node.actor);
            int[] actorPosition = generatedNode.getActorPosition();
            generatedNode.parent = node;
            node.children.add(generatedNode);
//            System.out.println("**************************");
//            generatedNode.printGrid();
//            System.out.println("PARENT:         ");
//            node.printGrid();
//            System.out.println("**************************");
            switch (movement){
                case "left":
                    actorPosition[1] = actorPosition[1]-1;
                    generatedNode.swapPositions(actorPosition);
                break;
                case "right":
                    actorPosition[1] = actorPosition[1]+1;
                    generatedNode.swapPositions(actorPosition);
                break;
                case "up":
                    actorPosition[0] = actorPosition[0]-1;
                    generatedNode.swapPositions(actorPosition);
                break;
                case "down":
                    actorPosition[0] = actorPosition[0]+1;
                    generatedNode.swapPositions(actorPosition);
                break;
            }
            children.add(generatedNode);
        }
        return children;
    }
}
