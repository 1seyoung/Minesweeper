package com.practice;

import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;

public class Game {


    public void startGame() {
        boolean game = true;
        String result = "";
        String command = "";

        Instant start = null; // 이거를  여기에 정의..? 왜
        Instant end = null; // 반복문 안에서 정의 x...
        Scanner input = new Scanner(System.in);
        while (game) {
            start = Instant.now();
            System.out.println("Test Game");

            command = input.nextLine(); // 선택 입력
            if (command.equals("end")) {
                game = false;
                endGame();
            }

            if (command.equals("fail")) {
                game = false;
                result = "fail";

            }

            if (command.equals("win")) {
                game = false;
                result = "win";
            }

            end = Instant.now();

        }

        System.out.println("End Game : " + result);
        System.out.println("time : " + +Duration.between(start, end).toMillis() + " ms");
        System.out.println("Click Enter");
        input.nextLine(); // 입력 버퍼 정리



    }


    public void endGame(){
        System.out.println("- 진행 중인 게임 종료 - ");

    }

}
