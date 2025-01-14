package com.practice.state;

import com.practice.GameEvent;
import com.practice.GameStateModel;

public class VictoryState implements GameState{
    private static final VictoryState instance = new VictoryState();

    private VictoryState() {}

    public static VictoryState getInstance() {
        return instance;
    }

    @Override
    public void handleEvent(GameEvent event, GameStateModel model) {
        System.out.println("You've already won! Start a new game to play again.");

    }

    @Override
    public boolean isState(GameState state) {
        return state == this;
    }
}
