package org.idea.gameOfLife;

public interface GameRule {
    State applyRule(State currentState, Integer liveCount);
}
