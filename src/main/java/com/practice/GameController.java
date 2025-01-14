package com.practice;

public class GameController {
    private GameStateModel gameState;
    private GameBoard gameBoard;
    private GameView gameView;

    public GameController (int rows, int cols, String level, GameStateModel gameState) {
        this.gameState = gameState;
        this.gameBoard = new GameBoard(rows, cols, level);
        this.gameView = new GameView();
    }

    public void handleTileClick(int x, int y) {
        Tile tile = gameBoard.getTile(x,y);
        //gameState.handleEvent("click_tile");
        gameState.handleEvent(GameEvent.CLICK_TILE);

        if (tile.isMine()) {
            //gameState.handleEvent("click_mine");
            gameState.handleEvent(GameEvent.CLICK_MINE);
        } else {
            gameBoard.openTile(x, y, gameState);
        }
    }

    public void handleTileFlag(int x, int y) {
        gameBoard.checkedFlag(x,y);
    }

    public void printBoard() {
        gameView.printBoard(gameBoard);
    }

    public void startGame() {
        //gameState.handleEvent("game_start");
        gameState.handleEvent(GameEvent.GAME_START);
    }
}
