package com.practice;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean run = true;
        User user = new User();


        System.out.println("--------------------------------------");
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Write Your Name : ");
        user.setName(input.nextLine());
        String _name = user.getName();
        System.out.println();


        while (run) {
            System.out.println("--------------------------------------");
            System.out.println("   __  __ _                          ");
            System.out.println("  |  \\/  (_)                         ");
            System.out.println("  | \\  / |_ _ __ ___   ___  ___ ___  ");
            System.out.println("  | |\\/| | | '_ ` _ \\ / _ \\/ __/ __|");
            System.out.println("  | |  | | | | | | | |  __/\\__ \\__ \\");
            System.out.println("  |_|  |_|_|_| |_| |_|\\___||___/___/ ");
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("User : " + _name);
            System.out.println("▶︎ Game Start");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("3. Setting");
            System.out.println("--------------------------------------");
            System.out.print("Me : ");

            int choice = input.nextInt(); // 선택 입력
            input.nextLine(); // 입력 버퍼 정리

            switch (choice){
                case 1 :
                    Game game = new Game();
                    game.startGame();
                    break; // break 안했더니 그냥 종료됨 ,,, 잊지 말기...
                case 2 :
                    run = false;
                    input.close();
                    System.exit(0); // 시스템 종료
                    break;
                case  3 :
                    // Setting 로직
                    System.out.print("Write New Name : ");
                    user.setName(input.nextLine());
                    _name = user.getName();
                    break;

            }
        }
    }
}