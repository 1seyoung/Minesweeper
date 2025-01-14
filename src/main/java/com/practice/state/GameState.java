package com.practice.state;

import com.practice.GameEvent;
import com.practice.GameStateModel;

public interface GameState {
    void handleEvent(GameEvent event, GameStateModel model);
    boolean isState(GameState state);
}
