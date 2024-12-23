package com.practice;

public class GameStateModel {
    private GameState currentState;

    public GameStateModel() {
        this.currentState = GameState.INITIAL; // 초기 상태
    }

    // 현재 상태 반환
    public GameState getCurrentState() {
        return currentState;
    }

    // 상태 전환 이벤트 처리
    public void handleEvent(String event) {
        switch (currentState) {
            case INITIAL:
                if (event.equals("game_start")) {
                    currentState = GameState.READY;
                    System.out.println("Game started! State is now READY.");
                }
                break;

            case READY:
                if (event.equals("click_tile")) {
                    currentState = GameState.PLAYING;
                    System.out.println("Game is now in PLAYING state.");
                }
                break;

            case PLAYING:
                if (event.equals("click_mine")) {
                    currentState = GameState.GAME_OVER;
                    System.out.println("You clicked on a mine! Game Over.");
                } else if (event.equals("all_safe")) {
                    currentState = GameState.VICTORY;
                    System.out.println("Congratulations! You win!");
                }
                break;

            case GAME_OVER:
                System.out.println("Game is already over. Restart to play again.");
                break;

            case VICTORY:
                System.out.println("You've already won! Start a new game to play again.");
                break;

            default:
                System.out.println("Unknown event or invalid state.");
        }
    }

}