package es.udc.intelligentsystems;

import java.util.*;

public class AStarStrategy implements InformedSearchStrategy {
    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {
        PriorityQueue<InformedSearchNode> frontier = new PriorityQueue<>();
        ArrayList<InformedSearchNode> explored = new ArrayList<InformedSearchNode>();

        State currentState = p.getInitialState();
        MagicSquareProblem p2 = (MagicSquareProblem) p;
        //MagicSquareHeuristic heuristic = new MagicSquareHeuristic();
        float fEvaluation = h.evaluate(currentState);
        int pathCost = 0;
        InformedSearchNode current = new InformedSearchNode(currentState, null, null, pathCost, fEvaluation);
        frontier.add(current);
        int i = 1;
        System.out.println((i++) + " - Starting search at " + Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
        do {
            if (frontier.isEmpty()) throw new Exception("Frontier empty");
            current = frontier.poll();
            //System.out.println(Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
            currentState = current.state;
            if (p2.isGoal(currentState)) {
                System.out.println("Solved the problem: " + Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
                return (MagicSquareProblem.MagicSquareState) currentState;
//                System.out.println("Solved the problem: "+ Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
//                System.out.println("Number of expanded and created nodes to reach solution: "+expandedNodes+" "+createdNodes);
//                return reconstructSolution(current);
            }
            explored.add(current);
            Action[] availableActions = p2.actions(currentState);
          //  System.out.println(Arrays.toString(p2.actions(currentState)));
            for (int j = 0; j < p2.actions(currentState).length && availableActions[j] != null; j++) {
                InformedSearchNode child = ChildNode(p2, current, availableActions[j]);
                if (!frontier.contains(child) && !explored.contains(child))
                    frontier.offer(child);
               else
                {

                    InformedSearchNode duplicate=FindNode(frontier,child);
                    //duplicate is always null
                    if(duplicate!=null&&duplicate.fEvalutaion>child.fEvalutaion)
                    {

                        //System.out.println("Found a duplicate");
                        System.out.println("Duplicate:"+duplicate);
                        System.out.println("Child:"+child);
                        System.out.println("Before remove");
                        displayFrontier(frontier);
                        frontier.remove(duplicate);
                        System.out.println("After remove");
                        displayFrontier(frontier);
                        frontier.offer(child);
                        System.out.println("After offer");
                        displayFrontier(frontier);
                        break;
                    }

                }

               }

        } while (true);
    }

    private InformedSearchNode ChildNode(SearchProblem p2, InformedSearchNode parent, Action action) {
        int[][] matrix = Arrays.stream(((MagicSquareProblem.MagicSquareState) parent.state).getSquare()).map(int[]::clone).toArray(int[][]::new);
        State st = new MagicSquareProblem.MagicSquareState(matrix);
        State state = p2.result(st, action);
        MagicSquareHeuristic heuristic=new MagicSquareHeuristic();
        float fEvaluation = parent.cost +1+ heuristic.evaluate(state);
        InformedSearchNode child = new InformedSearchNode(state, parent, action, parent.cost+1, fEvaluation);
        return child;
    }
    private InformedSearchNode FindNode(PriorityQueue<InformedSearchNode> frontier,InformedSearchNode child)
    {
        InformedSearchNode retrievedElement =null;
        PriorityQueue<InformedSearchNode> tempPQ = new PriorityQueue<InformedSearchNode>(frontier);// Initialize with a default value in case the element is not found
        while (!tempPQ.isEmpty()) {
            InformedSearchNode element = tempPQ.poll(); // Remove the element at the front of the temporary priority queue
            if (element.equals(child)) { // Check if the element is the one we want
                retrievedElement = element;
                //System.out.println("RetrievedElem: "+retrievedElement.state.toString());
                return retrievedElement;
            }
        }
        return retrievedElement;
    }
    public static void displayFrontier(PriorityQueue<InformedSearchNode> pq) {
        Iterator<InformedSearchNode> iterator = pq.iterator();
        System.out.print("Priority Queue: ");
        while (iterator.hasNext()) {
            System.out.print(Arrays.deepToString(((MagicSquareProblem.MagicSquareState) iterator.next().state).getSquare()) + " ");
        }
        System.out.println();
    }

}





