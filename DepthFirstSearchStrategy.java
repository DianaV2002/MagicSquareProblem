package es.udc.intelligentsystems;

import java.util.*;

public class DepthFirstSearchStrategy implements SearchStrategy{

    public DepthFirstSearchStrategy()
    {

    }
    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Stack<Node> frontier = new Stack<Node>();
        ArrayList<Node> explored = new ArrayList<Node>();
        State currentState = p.getInitialState();
        MagicSquareProblem p2=(MagicSquareProblem) p;
        Node current = new Node(currentState, null, null);
        frontier.add(current);
        int createdNodes=1;
        int expandedNodes=0;
        int i = 1;
        System.out.println((i++) + " - Starting search at " + Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
        while (!p2.isGoal(currentState)){
            if (frontier.isEmpty()) throw new Exception("Frontier empty");
            current = frontier.pop();
            //System.out.println((i++) + " - " +  Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()) + " is not a goal");
            currentState=  current.state;
            if (!explored.contains(current)) {
                System.out.println((i++) + " - " + Arrays.deepToString(((MagicSquareProblem.MagicSquareState) current.state).getSquare()) + " not explored yet");
                {
                    if (p2.isGoal(currentState)) {
                        System.out.println("Solved the problem: "+ Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
                        System.out.println("Number of expanded and created nodes to reach solution: " + expandedNodes + " " + createdNodes);
                        return reconstructSolution(current);
                    } else {
                        explored.add(current);
                        expandedNodes++;
                        ArrayList<Node> successors=new ArrayList<>();
                        Action[] availableActions = p2.actions(currentState);
                        for(int j=0;j<p2.actions(currentState).length&&availableActions[j]!=null;j++) {
                            Node child=ChildNode(p2,current,availableActions[j]);
                            if(!frontier.contains(child)&&!explored.contains(child))
                            {
                                createdNodes++;
                                frontier.add(child);
                            }
                        }

                    }
                }
            }
            else {
                System.out.println((i++) + " - " + Arrays.deepToString(((MagicSquareProblem.MagicSquareState) current.state).getSquare()) + " already explored");
            }
        }
        System.out.println("Solved the problem: "+ Arrays.deepToString(((MagicSquareProblem.MagicSquareState) currentState).getSquare()));
        System.out.println("Number of expanded and created nodes to reach solution: "+expandedNodes+" "+createdNodes);
        return reconstructSolution(current);
    }
    private Node[] reconstructSolution(Node n) {

        ArrayList<Node> path = new ArrayList<>();
        Node current = n;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }

        Node[] solution = new Node[path.size()];
        for (int i = 0; i < path.size(); i++) {
            solution[i] = path.get(path.size() - i - 1);
        }

        return solution;
    }
    private Node ChildNode(SearchProblem p, Node parent, Action action) {
        int[][] matrix= Arrays.stream(((MagicSquareProblem.MagicSquareState)parent.state).getSquare()).map(int[]::clone).toArray(int[][]::new);
        State st=new MagicSquareProblem.MagicSquareState(matrix);
        State state=p.result(st,action);
        Node child=new Node(state,parent,action);
        return child;
    }
}

