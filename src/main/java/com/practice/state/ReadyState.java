package com.practice.state;

import com.practice.GameEvent;
import com.practice.GameStateModel;

public class ReadyState implements GameState{
    private static final ReadyState instance = new ReadyState();

    private ReadyState() {}

    public static ReadyState getInstance() {
        return instance;
    }

    @Override
    public void handleEvent(GameEvent event, GameStateModel model) {
        if (event == GameEvent.CLICK_TILE) {
            model.setCurrentState(PlayingState.getInstance());
            System.out.println("Game is now in PLAYING state.");
        } else {
            System.out.println("Invalid event for this state.");
        }
    }

    @Override
    public boolean isState(GameState state) {
        return state == this;
    }
}
