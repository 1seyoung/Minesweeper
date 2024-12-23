package com.practice;

import java.beans.EventHandler;

public class GameController {
    // 게임 상태, 이벤트 , 게임 보드 관리하는 컨트롤러
    private GameStateModel gameState;
    private GameBoard gameBoard;  // 여기가 public 이고 아니고가 영향을 주는감...

    public GameController (int rows, int cols, String level, GameStateModel gameState) {
        this.gameState = gameState;
        this.gameBoard = new GameBoard(rows, cols, level);
    }

    public void handleTileClick(int x, int y) {
        Tile tile = gameBoard.getTile(x,y);
        gameState.handleEvent("click_tile");

        // 타일이 지뢰면
        if (tile.isMine()) {
            gameState.handleEvent("click_mine");
        } else {
            // 아니면 열어야지
            gameBoard.openTile(x, y, gameState);
        }
    }


    public void handleTileFlag(int x, int y) {
        gameBoard.checkedFlag(x,y);
    }

    public void printBoard() {
        gameBoard.printBoard();
    }


    public void startGame() {
        gameState.handleEvent("game_start"); // 게임 시작 이벤트
    }
}
