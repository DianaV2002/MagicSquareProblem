package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class GraphSearchStrategy implements SearchStrategy {

    public GraphSearchStrategy() {
    }


    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Queue<Node> frontier = new LinkedList<Node>();
        ArrayList<State> explored = new ArrayList<State>();
        State currentState = p.getInitialState();
        Node current = new Node(currentState, null, null);
        frontier.add(current);
        explored.add(current.state);
        int i = 1;
        System.out.println((i++) + " - Starting search at " + currentState);
        while (!p.isGoal(currentState)){
            if (frontier.isEmpty()) throw new Exception("Frontier empty");
            System.out.println((i++) + " - " + currentState + " is not a goal");
            current = frontier.poll();
            currentState=current.state;
            explored.add(currentState);
            Action[] availableActions = p.actions(currentState);
            boolean modified = false;
            for (Action action : availableActions) {
                Node child = ChildNode(p, current, action);
                System.out.println("Child state aka result:"+ child.state);
                if (!explored.contains(child.state)) {
                    System.out.println((i++) + " - " + child.state + " not explored yet");
                    if (p.isGoal(child.state))
                        return reconstructSolution(child);
                    frontier.add(child);
                    modified=true;
                    break;
                } else {
                    System.out.println((i++) + " - " + child.state + " already explored");
                }
            }
            if (!modified) {
                throw new Exception("No solution could be found");
            }

        }
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
        State state=p.result(parent.state,action);
        Node child=new Node(state,parent,action);
        return child;
    }
}