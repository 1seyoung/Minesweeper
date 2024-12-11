package com.practice;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("--------------------------------------");
        System.out.println("Minesweeper");
        System.out.println("--------------------------------------");
        System.out.println("▶︎ Game Start");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("--------------------------------------");
        System.out.print("Me : ");
        Scanner input = new Scanner(System.in);
        if(input.nextInt() == 1) {
            Game game = new Game();
            game.startGame();
        }
        else if (input.nextInt() == 2){
            input.close(); // Scanner 닫기
            System.exit(0); // 시스템 종료
        }

    }
}