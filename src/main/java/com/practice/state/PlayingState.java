package com.practice.state;

import com.practice.GameEvent;
import com.practice.GameStateModel;

public class PlayingState implements GameState{
    private static final  PlayingState instance = new PlayingState();

    private PlayingState() {}

    public static PlayingState getInstance() {
        return instance;
    }

    @Override
    public void handleEvent(GameEvent event, GameStateModel model) {
        if (event == GameEvent.CLICK_MINE) {
            model.setCurrentState(GameOverState.getInstance());
            System.out.println("You clicked on a mine! Game Over.");
        } else if (event == GameEvent.ALL_SAFE) {
            model.setCurrentState((VictoryState.getInstance()));
        } else {
            System.out.println("Invalid event for this state.");
        }
    }

    @Override
    public boolean isState(GameState state) {
        return state == this;
    }
}
