package org.idea.gameOfLife;

public class GameOfLife {

    public State[][] start(State[][] intialState, int noOfIteration, GameRule rule) {
        State[][] nextState = intialState;
        for (int i = 0; i < noOfIteration; i++) {
            nextState = getNextState(nextState, rule);
            printMatrix(nextState);
        }
        return nextState;
    }

    private void printMatrix(State[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("{");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.print("},");
            System.out.println();
        }
    }

    private State[][] getNextState(State[][] intialState, GameRule rule) {
        State[][] finalState = new State[intialState.length][intialState[0].length];
        for (int row = 0; row < intialState.length; row++) {
            for (int column = 0; column < intialState[row].length; column++) {
                Integer liveCount = liveCount(intialState, row, column);
                finalState[row][column] = rule.applyRule(intialState[row][column], liveCount);
            }
        }
        return finalState;
    }

    protected Integer liveCount(State[][] intialState, int i, int j) {
        int rowLimit = intialState.length-1;
        int columnLimit = intialState[0].length-1;
        int liveCount = 0;
        for(int x = Math.max(0, i-1); x <= Math.min(i+1, rowLimit); x++) {
            for(int y = Math.max(0, j-1); y <= Math.min(j+1, columnLimit); y++) {
                if(x != i || y != j) {
                    if (intialState[x][y].equals(State.LIVE)) {
                        liveCount++;
                    }
                }
            }
        }
        return  liveCount;
    }


}
