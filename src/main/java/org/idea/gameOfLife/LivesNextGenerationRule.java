package org.idea.gameOfLife;

public class LivesNextGenerationRule implements GameRule {
    private final GameRule rule;
    public LivesNextGenerationRule(GameRule rule) {
        this.rule = rule;
    }

    public State applyRule(State currentState, Integer liveCount) {
        if(State.LIVE.equals(currentState) && liveCount == 2 || liveCount == 3){
            return State.LIVE;
        }
        return rule.applyRule(currentState, liveCount);
    }
}
