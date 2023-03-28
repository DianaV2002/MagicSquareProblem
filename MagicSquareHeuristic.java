package es.udc.intelligentsystems;

public class MagicSquareHeuristic extends Heuristic{
    //compute magic sum
    //calculate the differences between all columns,rows and main diagonals and magic Sum
    //heuristic=sum of all the diffrences between all columns,rows and main diagonals and magic Sum
    //it is admissible because it never overestimates the cost to the goal state
    //it is consistent because  the estimated cost of reaching any successor state is always less than or equal to the estimated cost of reaching the goal state from the current state
    public MagicSquareHeuristic()
    {

    }
    @Override
    public float evaluate(State e) {
        MagicSquareProblem.MagicSquareState state=(MagicSquareProblem.MagicSquareState)e;
        int h=0;//heuritic cost
        //compute all sums
       int numberofValues=0;
        for(int i=0;i<state.getSize();i++)
        {
            for(int j=0;j<state.getSize();j++)
            {
                if(state.getSquare()[i][j]!=0)
                   numberofValues++;
            }
        }
        h= state.size*state.size-numberofValues;
        return h;
    }
}
