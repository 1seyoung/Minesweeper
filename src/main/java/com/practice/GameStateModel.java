package com.practice;

import com.practice.state.GameState;
import com.practice.state.InitialState;

public class GameStateModel {
    private GameState currentState;

    public GameStateModel() {
        this.currentState = InitialState.getInstance();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void handleEvent(GameEvent event) {
        currentState.handleEvent(event, this);
    }

}