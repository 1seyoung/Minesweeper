package com.practice;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter rows: ");
        int rows = scanner.nextInt();

        System.out.println("Enter columns: ");
        int cols = scanner.nextInt();

        System.out.println("Enter Game level (EASY / MEDIUM / HARD): ");
        String level = scanner.next().toUpperCase(); // 난이도를 대문자로 처리

        GameStateModel gameState = new GameStateModel();

        //여기 까진 실행

        //rows, cols, level -> 여기서 문제 발생
        GameController gameController = new GameController(rows, cols, level, gameState);


        System.out.println("Enter command (start / exit): ");
        String command = scanner.next();

        if (command.equals("exit")) {
            System.out.println("-- 게임 종료 --");
        }

        if (command.equals("start")) {
            gameController.startGame();
            gameController.printBoard();
            while (true) {
                GameState currentState = gameState.getCurrentState();

                if (currentState == GameState.GAME_OVER) {
                    System.out.println("-- Game Over --");
                    break;
                }

                if (currentState == GameState.VICTORY) {
                    System.out.println("-- Win --");
                    break;
                }

                System.out.println("Enter command (open x y / flag x y) :");

                command = scanner.next();

                if (command.equals("open") || command.equals("flag")) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    switch (command) {
                        case "open":
                            gameController.handleTileClick(x, y);
                            break;
                        case "flag":
                            gameController.handleTileFlag(x, y);
                            break;
                        default:
                            System.out.println("다시 입력하세요");
                    }

                    gameController.printBoard();

                }
            }
            scanner.close();
        }
    }


}