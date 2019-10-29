public class Main {
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
        SearchController sc = new SearchController();
        sc.doAStar();
    }
}
