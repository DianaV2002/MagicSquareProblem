package es.udc.intelligentsystems;
import java.util.*;
import java.lang.Object;
import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.example.VacuumCleanerProblem;

public  class Node { // explored states

    public State state;
    public Node parent;
    public Action action;

    public Node(State s, Node p, Action a) {
        state = s;
        parent = p;
        action = a;

    }

//    public ArrayList<Node> Succesors(Action[] actionList) {
//        ArrayList<Node> succesors = new ArrayList<Node>();
//        for (Action action:actionList)
//            if(action!=null)
//            {
//                if (action.isApplicable(state)) {
//                    State newState=action.applyTo(state);
//                    Node child = new Node(newState, this, action,cost,fEvalutaion);
//                    succesors.add(child);
//                }
//            }
//
//        return succesors;
//    }

    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node that=(Node)o;
        return Arrays.deepEquals((((MagicSquareProblem.MagicSquareState) state).getSquare()), ((MagicSquareProblem.MagicSquareState) that.state).getSquare());
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    public State getState() {
        return state;
    }
}

