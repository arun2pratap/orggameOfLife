package org.idea.gameOfLife;

public class GameOfLifeUnderPopulationRule implements GameRule {

    public State applyRule(State currentState, Integer liveCount) {
        if(State.LIVE.equals(currentState) && liveCount > 3){
            return State.DEAD;
        }
        return currentState;
    }
}
