package org.idea.gameOfLife;

public class UnderPopulationRule implements GameRule {
    private final GameRule rule;
    public UnderPopulationRule(GameRule rule) {
        this.rule = rule;
    }

    public State applyRule(State currentState, Integer liveCount) {
        if(liveCount < 2){
            return State.DEAD;
        }
        return rule.applyRule(currentState, liveCount);
    }
}
