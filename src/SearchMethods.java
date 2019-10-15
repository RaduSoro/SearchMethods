import java.util.*;

public class SearchMethods {
    private Grid root;
    private Grid currentNode;
    int nodesExpanded = 0;

    public SearchMethods(Grid root){
        this.root = root;
        this.currentNode = root;
    }


    public ArrayList<Grid> bfs(){

        Queue<Grid> fringe = new ArrayDeque<>();
        fringe.add(root);
        while (!fringe.isEmpty()){
            currentNode = fringe.remove();
            if (isGoalState()){
                System.out.println(nodesExpanded + " total number of nodes expanded");
                return getPathToRoot(currentNode);
            }
            else {
                ArrayList<Grid> children = expandNode(currentNode);
                children.forEach(node-> fringe.add(node));
            }
        }
        return null;
    }

    public ArrayList<Grid> getPathToRoot(Grid node){
        ArrayList<Grid> path = new ArrayList<>();
        path.add(currentNode);
        while (node.parent!=null){
            path.add(node.parent);
            node = node.parent;
        }
        return path;
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
        nodesExpanded++;
        for (String movement: allowedMovements) {
            //generates a new node similar to parent, adds parent to parent property
            generatedNode = new Grid(node.getNewGrid(),node.actor);
            generatedNode.move=movement;
            int[] actorPosition = generatedNode.getActorPosition();
            generatedNode.parent = node;
            generatedNode.depth = node.depth+1;
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
