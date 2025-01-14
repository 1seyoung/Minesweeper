package com.practice.state;

import com.practice.GameEvent;
import com.practice.GameStateModel;

public class InitialState implements GameState{
    private static final InitialState instance = new InitialState();

    private InitialState() {}

    public static InitialState getInstance() {
        return instance;
    }

    @Override
    public void handleEvent(GameEvent event, GameStateModel model) {
        if (event == GameEvent.GAME_START) {
            model.setCurrentState(ReadyState.getInstance());
            System.out.println("Game started! State is now READY.");
        }
    }

    @Override
    public boolean isState(GameState state) {
        return state == this;
    }
}
