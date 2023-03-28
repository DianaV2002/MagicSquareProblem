package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.Arrays;

public class DepthFirstBacktracking {

    public State solve(SearchProblem p) throws Exception {
        int k=0;
        State state=p.getInitialState();
        if(bkt(p,state,k))
            return state;
        else return null;
    }
    public boolean bkt(SearchProblem p,State current,int k)
    {
        MagicSquareProblem.MagicSquareState currentState=(MagicSquareProblem.MagicSquareState) current;

        if(k==currentState.getSize()*currentState.getSize())
            return p.isGoal(currentState);

        int row=k/ currentState.getSize();
        int col=k% currentState.getSize();
        if(currentState.getSquare()[row][col]==0) {
            for(int value=1;value<=currentState.getSize()*currentState.getSize();value++)
            {
                currentState.getSquare()[row][col]=value;
                if(isValid(currentState))
                {
                    if(bkt(p,currentState,k+1))
                        return true;
                }
                currentState.getSquare()[row][col]=0;
            }
        }
        else return bkt(p,currentState,k+1);
        currentState.getSquare()[row][col]=0;
        return false;
    }
    public boolean isValid(State state)
    {
        MagicSquareProblem.MagicSquareState st=(MagicSquareProblem.MagicSquareState) state;
        int[] duplicates = new int[17];
        for(int i = 1; i < 17; i++)
            duplicates[i] = 0;

        for(int row = 0; row < st.getSize(); row++)
            for(int col = 0; col < st.getSize(); col++)
                duplicates[st.getSquare()[row][col]] += 1;

        for(int i = 1; i < 17; i++)
            if(duplicates[i] > 1)
                return false;

        int diag1 = 0;
        int diag2 = 0;
        for(int row=0;row<st.getSize();row++)
        {
            int line=0;
            int cols=0;
            diag1 += st.getSquare()[row][row];
            diag2 += st.getSquare()[row][st.getSize() - row - 1];
            for(int col=0;col<st.getSize();col++)
                if(st.getSquare()[row][col]!=0)
                {
                    line+=st.getSquare()[row][col];
                    cols+=st.getSquare()[col][row];
                }

            if(line>st.getMagicSum()||cols> st.getMagicSum())
            {
                return false;
            }
        }

        if(diag1 > st.getMagicSum() || diag2 > st.getMagicSum())
            return false;

        return true;
    }
}
