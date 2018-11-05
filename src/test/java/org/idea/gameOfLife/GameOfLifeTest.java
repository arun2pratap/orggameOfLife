package org.idea.gameOfLife;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.idea.gameOfLife.State.DEAD;
import static org.idea.gameOfLife.State.LIVE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameOfLifeTest {


    @Test
    public void getGameCount(){
        State[][] initialState = {
                {LIVE, DEAD, DEAD, DEAD, DEAD},
                {DEAD, LIVE, LIVE, DEAD, DEAD},
                {DEAD, DEAD, LIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, LIVE},
        };
        GameOfLife gameOfLife = new GameOfLife();
        int liveCount = gameOfLife.liveCount(initialState, 3, 3);
        assertEquals(2, liveCount);

        liveCount = gameOfLife.liveCount(initialState, 0, 0);
        assertEquals(1, liveCount);
    }

    @Test
    public void gameUnderPopulation() throws IOException, InterruptedException {
        GameOfLife gameOfLife = new GameOfLife();
        State[][] initialState = {
                {LIVE, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, LIVE, DEAD, DEAD},
                {DEAD, DEAD, LIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
        };
        int noOfIteration = 1;

        State[][] expectedState = {
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, LIVE, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
        };
        GameRule rule = getRule();
        State[][] matrix = gameOfLife.start(initialState, noOfIteration, rule);
        printMatrix(matrix);
        assertEquals(expectedState, matrix);
    }



    @Test
    public void gameReProduction() throws IOException, InterruptedException {
        GameOfLife gameOfLife = new GameOfLife();
        State[][] initialState = {
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, LIVE, LIVE, DEAD, DEAD},
                {DEAD, DEAD, LIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
        };
        int noOfIteration = 1;

        State[][] expectedState = {
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, LIVE, LIVE, DEAD, DEAD},
                {DEAD, LIVE, LIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
        };
        GameRule rule = getRule();
        State[][] matrix = gameOfLife.start(initialState, noOfIteration, rule);
        printMatrix(matrix);
        assertEquals(expectedState, matrix);
    }



    @Test
    public void gameOverPopulation() throws IOException, InterruptedException {
        GameOfLife gameOfLife = new GameOfLife();
        State[][] initialState = {
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, LIVE, LIVE, LIVE, DEAD},
                {DEAD, DEAD, LIVE, LIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
        };
        int noOfIteration = 1;

        State[][] expectedState = {
                {DEAD, DEAD, LIVE, DEAD, DEAD },
                {DEAD, LIVE, DEAD, LIVE, DEAD },
                {DEAD, LIVE, DEAD, LIVE, DEAD },
                {DEAD, DEAD, DEAD, DEAD, DEAD },
                {DEAD, DEAD, DEAD, DEAD, DEAD }
        };
        GameRule rule = getRule();
        State[][] matrix = gameOfLife.start(initialState, noOfIteration, rule);
        printMatrix(matrix);
        assertEquals(expectedState, matrix);
    }


    @Test
    public void gameTestMultipleItteration() throws IOException, InterruptedException {
        GameOfLife gameOfLife = new GameOfLife();
        State[][] initialState = {
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, LIVE, LIVE, LIVE, DEAD},
                {DEAD, DEAD, LIVE, LIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
        };
        int noOfIteration = 3;

        State[][] expectedState = {
                {DEAD, DEAD, LIVE, DEAD, DEAD },
                {DEAD, DEAD, LIVE, DEAD, DEAD },
                {DEAD, DEAD, DEAD, DEAD, DEAD },
                {DEAD, DEAD, DEAD, DEAD, DEAD },
                {DEAD, DEAD, DEAD, DEAD, DEAD }
        };
        GameRule rule = getRule();
        State[][] matrix = gameOfLife.start(initialState, noOfIteration, rule);
//        printMatrix(matrix);
        assertEquals(expectedState, matrix);
    }

    private GameRule getRule() {
        GameRule rule = new GameOfLifeUnderPopulationRule();
        rule = new LivesNextGenerationRule(rule);
        rule = new ReProductionRule(rule);
        rule = new UnderPopulationRule(rule);
        return rule;
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
}
