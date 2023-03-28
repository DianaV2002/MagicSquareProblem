package es.udc.intelligentsystems;

import es.udc.intelligentsystems.Action;

import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.SearchProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//number of empty spaces=n*n-initial state
public class MagicSquareProblem extends SearchProblem {

    public static class MagicSquareState extends State {
        public int[][] magicSquare;
        public int size;
        public int magicSum;


        public MagicSquareState(int[][] magicSquare) {
            this.magicSquare=magicSquare;
            this.size=magicSquare.length;
            this.magicSum=size*(size*size+1)/2;
        }

        @Override
        public String toString() {
            return magicSquare.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MagicSquareState that = (MagicSquareState) o;
            boolean ok=true;
            int row=0;
            int col=0;
            for(row=0;row<size;row++)
                for(col=0;col<size;col++)
                    if(magicSquare[row][col]!=that.magicSquare[row][col])
                        ok=false;
            return ok;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            for(int i=0; i<size; i++) {
                hash =+ Arrays.hashCode(magicSquare[i]);
            }
            return hash;
        }
        public int getSize() {
            return size;
        }
        public int[][] getSquare()
        {
            return magicSquare;

        }
        public int getMagicSum()
        {
            return magicSum;
        }
    }
//de implementat
    public static class MagicSquareAction extends Action {
    private int row;
    private int col;
    private int value;


    public MagicSquareAction(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
         return "("+row+","+col+","+value+")";
    }

    @Override
    public boolean isApplicable(State st) {
        MagicSquareState state = (MagicSquareState) st;
        int row = 0;
        int col = 0;
        int value = 0;


        for (row = 0; row < state.getSize(); row++)
            for (col = 0; col < state.getSize(); col++)
                if (state.getSquare()[row][col] == 0)
                    for (value = 0; value < state.getMagicSum(); value++)
                        if (state.getSquare()[row][col] + value < state.getMagicSum())
                            return true;
        return false;
    }

    @Override
    public State applyTo(State st) {
        MagicSquareState state = (MagicSquareState) st;
        int row = 0;
        int col = 0;
        for (row = 0; row < state.getSize(); row++)
            for (col = 0; col < state.getSize(); col++)
                if (this.row == row && this.col == col)
                    state.getSquare()[row][col] = value;
        return state;

    }
}


    private Action[] actionList;
    private int nrActions;

    public MagicSquareProblem(State initialState) {
        super(initialState);
        actionList = new Action[]{};
        this.nrActions=0;
    }

    public Action[] actions(State st) {
        MagicSquareState state = (MagicSquareState) st;
        ArrayList<Action> actionList = new ArrayList<>();
        ArrayList<Integer> usedValues=new ArrayList<Integer>();
        for(int j=0;j< state.getSize();j++)
            for(int k=0;k< state.getSize();k++)
                if(state.getSquare()[j][k]!=0)
                    usedValues.add(state.getSquare()[j][k]);
        int row=0;
        int column=0;
        //find the first empty cell
        for(int i = 0; i < state.getSize()* state.getSize(); i++)
            if(state.getSquare()[i/state.getSize()][i%state.getSize()] == 0)
            {
                row = i / state.getSize();
                column = i % state.getSize();
                break;
            }
        for(int value=0;value<state.getSize()*state.getSize();value++)
            if(!usedValues.contains(value))
            {
                state.getSquare()[row][column]=value;
                MagicSquareAction action=new MagicSquareAction(row,column,value);
                actionList.add(action);
            }

        Action[] returnAcc=new Action[actionList.size()];
        return actionList.toArray(returnAcc);
    }

    public State result(State st, Action act)
    {
            return act.applyTo(st);
    }

    @Override
    public boolean isGoal(State st) {
        //check if the magic square is filled
        MagicSquareState state=(MagicSquareState)st;
        int diag1=0;
        int diag2=0;
        for (int i = 0; i < state.getSize(); i++) {
            int line = 0;
            int col = 0;
            diag1+=state.getSquare()[i][i];
            diag2+=state.getSquare()[i][state.getSize()-i-1];
            for (int j = 0; j < state.getSize(); j++) {
                if (state.getSquare()[i][j] == 0)
                    return false;
                line += state.getSquare()[i][j];
                col += state.getSquare()[j][i];
            }
            if (line != state.getMagicSum() || col != state.getMagicSum())
                return false;
        }
        if(diag1!=state.getMagicSum()||diag2!= state.getMagicSum())
            return false;
        return true;
    }
    public int getNrActions()
    {
        return nrActions;
    }

}
