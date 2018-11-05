package org.idea.gameOfLife;

public class ReProductionRule implements GameRule {
    private final GameRule rule;
    public ReProductionRule(GameRule rule) {
        this.rule = rule;
    }

    public State applyRule(State currentState, Integer liveCount) {
        if(State.DEAD.equals(currentState) && liveCount == 3){
            return State.LIVE;
        }
        return rule.applyRule(currentState, liveCount);
    }
}
