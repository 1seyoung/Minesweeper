package com.practice;

public class GameView {
    public void printBoard(GameBoard gameBoard) {
        System.out.println("Current Board:");
        Tile[][] board = gameBoard.getBoard();
        int rows = gameBoard.getRows();
        int cols = gameBoard.getCols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Tile tile = board[i][j];

                if (tile.isFlag()) {
                    System.out.print("F ");
                } else if (!tile.isOpened()) {
                    System.out.print("# ");
                } else if (tile.isMine()) {
                    System.out.print("* ");
                } else {
                    int neighborMineCount = tile.getMinesCount();
                    if (neighborMineCount > 0) {
                        System.out.print(neighborMineCount + " ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }
}