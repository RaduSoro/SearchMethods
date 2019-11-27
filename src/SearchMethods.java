import java.util.*;

public class SearchMethods {
    private Grid root;
    private Grid currentNode;
    public int nodesExpanded = 0;
    public int nodesGenerated = 0;
    public int solutionDepth = 0;
    public int currentDepth = 0;

    public SearchMethods(Grid root){
        this.root = root;
        this.currentNode = root;
    }

    public ArrayList<Grid> dfs(){
//        ArrayList<Grid> exploredNodes = new ArrayList<>();
        Stack<Grid> fringe = new Stack<>();
        fringe.add(root);
        while (!fringe.isEmpty()){
            currentNode = fringe.pop();
            if (isGoalState()){
                System.out.println(nodesExpanded + " total number of nodes expanded");
                System.out.println(nodesGenerated + " total number of nodes generated");
                solutionDepth = currentNode.depth;
                return getPathToRoot(currentNode);
            }else {
                ArrayList<Grid> children = expandNode(currentNode,true);
                fringe.addAll(children);
            }
        }
        return null;
    }

    public ArrayList<Grid> dls(int depth){
//        ArrayList<Grid> exploredNodes = new ArrayList<>();
        Stack<Grid> fringe = new Stack<>();
        fringe.add(root);
        while (!fringe.isEmpty() && currentNode.depth<=depth){
            currentNode = fringe.pop();
            if (isGoalState()){
                System.out.println(nodesExpanded + " total number of nodes expanded");
                System.out.println(nodesGenerated + " total number of nodes generated");
                return getPathToRoot(currentNode);
            }else if (currentNode.depth<=depth-1){
                nodesGenerated++;
//                System.out.println(currentNode.depth);
//                System.out.println(nodesGenerated);
                ArrayList<Grid> children = expandNode(currentNode,false);
                fringe.addAll(children);
            }
        }
        return null;
    }

    public ArrayList<Grid> IDS(int MaxDepth){
        int depth = 0;
        ArrayList<Grid> result;
        while (depth<=MaxDepth){
            System.out.println("Current depth LIMIT : "+depth );
            result = dls(depth);
            if (result == null) depth ++;
            else return result;
        }
        return null;
    }

    public ArrayList<Grid> aStarSeach(){
        PriorityQueue<Grid> fringe = new PriorityQueue<Grid>(new GridComparator());
        ArrayList<String> exploredNodes = new ArrayList<>();
        root.getManhattanScore();
        fringe.add(root);
        while (!fringe.isEmpty()) {
            currentNode = fringe.remove();
            if (isGoalState()){
                System.out.println(nodesExpanded + " total number of nodes expanded");
                System.out.println(nodesGenerated + " total number of nodes generated");
                return getPathToRoot(currentNode);
            }
            else {
                exploredNodes.add(currentNode.getGridUniqueID());
                ArrayList<Grid> children = expandNode(currentNode,false);
                children.forEach(child->{
                    if (!exploredNodes.contains(child.getGridUniqueID())){
                        fringe.add(child);
                    }
                });
//                fringe.addAll(children);
            }
        }
        return null;
    }

    public ArrayList<Grid> bfs(){
        Queue<Grid> fringe = new ArrayDeque<>();
        ArrayList<String> exploredNodes = new ArrayList<>();
        fringe.add(root);
        while (!fringe.isEmpty()){
            currentNode = fringe.remove();
            if (isGoalState()){
                System.out.println(nodesExpanded + " total number of nodes expanded");
                System.out.println(nodesGenerated + " total number of nodes generated");
                return getPathToRoot(currentNode);
            }
            else {
                exploredNodes.add(currentNode.getGridUniqueID());
                ArrayList<Grid> children = expandNode(currentNode,false);
                children.forEach(child -> {
                    if (!exploredNodes.contains(child.getGridUniqueID())){
                        fringe.add(child);
                    }
                });
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

    public boolean isGoalState(Grid state){
        String[][] grid = state.grid;
        if (grid[1][1].equals("A")&&grid[2][1].equals("B")&&grid[3][1].equals("C")) return true;
        return false;
    }

    public ArrayList<Grid> expandNode(Grid node, boolean randomMovementOrder){
        ArrayList<String> allowedMovements = node.getActorAllowedMovement();
        ArrayList<Grid> children = new ArrayList<>();
        Grid generatedNode;
        ArrayList<String> allowedMovementsBeforeShuffle = new ArrayList<>(allowedMovements);
        if (randomMovementOrder) Collections.shuffle(allowedMovements);
        nodesExpanded++;
        for (String movement: allowedMovements) {
            nodesGenerated++;
            //generates a new node similar to parent, adds parent to parent property
            generatedNode = new Grid(node.getNewGrid(),node.actor);
            generatedNode.generatedByMovement =movement;
            int[] actorPosition = generatedNode.getActorPosition();
            generatedNode.parent = node;
            generatedNode.depth = node.depth+1;
            generatedNode.parent.children.add(generatedNode);

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
            generatedNode.getManhattanScore();
            children.add(generatedNode);
        }
        return children;
    }
}

class GridComparator implements Comparator<Grid>{
    public int compare(Grid g1, Grid g2){
        if (g1.heuristicScore >g2.heuristicScore) return 1;
        else if (g1.heuristicScore <g2.heuristicScore) return -1;
        else return 0;
    }
}
