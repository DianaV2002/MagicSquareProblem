package es.udc.intelligentsystems;

import java.util.Arrays;

public class InformedSearchNode implements Comparable{


    public State state;
    public InformedSearchNode parent;
    public Action action;
    public float cost;
    public float fEvalutaion;

    public InformedSearchNode(State s, InformedSearchNode p, Action a,float cost,float fEvaluation) {
        state = s;
        parent = p;
        action = a;
        this.cost=cost;
        this.fEvalutaion=fEvaluation;
    }


    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformedSearchNode that = (InformedSearchNode) o;
        for(int i=0;i<((MagicSquareProblem.MagicSquareState)state).getSize();i++)
            for(int j=0;j<((MagicSquareProblem.MagicSquareState)state).getSize();j++)
                if(((MagicSquareProblem.MagicSquareState)state).getSquare()[i][j]!=((MagicSquareProblem.MagicSquareState)that.state).getSquare()[i][j])
                    return false;
        return true;

    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }



    @Override
    public int compareTo(Object o) {
        InformedSearchNode that=(InformedSearchNode)o;
        if(this.fEvalutaion<that.fEvalutaion)
            return 1;
        else return 0;
    }


}

