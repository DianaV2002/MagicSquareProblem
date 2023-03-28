package es.udc.intelligentsystems;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] initBoard = {{4, 9, 2}, {3,5,0},{0, 1,0}};
        int n = 3;
        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(initBoard);
        MagicSquareProblem magicSquare = new MagicSquareProblem(initialState);

//        SearchStrategy buscadorGraph = new DepthFirstSearchStrategy();
//        Node[] solutionGraph = buscadorGraph.solve(magicSquare);
//        for (int i = 0; i < solutionGraph.length; i++)
//         System.out.println(Arrays.deepToString(((MagicSquareProblem.MagicSquareState) solutionGraph[i].state).getSquare()));
        MagicSquareHeuristic heuristic=new MagicSquareHeuristic();
        System.out.println("Cost calculated with heuristic: " + heuristic.evaluate(initialState));

          InformedSearchStrategy buscadorAStar=new AStarStrategy();
          State solutionGraph = buscadorAStar.solve(magicSquare,heuristic);
          System.out.println("Solution:"+Arrays.deepToString(((MagicSquareProblem.MagicSquareState) solutionGraph).getSquare()));
//////
//        DepthFirstBacktracking strategy=new DepthFirstBacktracking();
//        State solution=strategy.solve(magicSquare);
//        MagicSquareProblem.MagicSquareState state=(MagicSquareProblem.MagicSquareState) solution;
//        if(state!=null)
//            System.out.println("Solution: "+ Arrays.deepToString(state.getSquare()));


    }

}