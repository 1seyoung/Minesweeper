package com.practice.state;

import com.practice.GameEvent;
import com.practice.GameStateModel;

public class GameOverState implements GameState{
    private static final GameOverState instance = new GameOverState();

    private GameOverState() {}

    public static GameOverState getInstance() {
        return instance;
    }

    @Override
    public void handleEvent(GameEvent event, GameStateModel model) {
        System.out.println("Game is already over. Restart to play again.");
    }

    @Override
    public boolean isState(GameState state) {
        return state == this;
    }
}
