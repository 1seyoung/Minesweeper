package com.practice;

import java.time.Instant;
import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final String _name;
    public Object[][] map ;
    public Object[][] userMap;


    public Game(String name){
        this._name = name;
    }


    public void startGame() {
        boolean game = true;
        String result = "";
        String command = "";
        int mapSize = 9;

        Instant start = null; // 이거를  여기에 정의..? 왜
        Instant end = null; // 반복문 안에서 정의 x...
        Scanner input = new Scanner(System.in);

        System.out.println("Choice Map Size (9 / 25/ 36)" );
        mapSize = input.nextInt();

        map = mapMaker(mapSize); // 정답 map
        userMap = new Object[mapSize][mapSize];

        for(int i = 0; i < mapSize ; i++){
            for (int j = 0; j < mapSize ; j++){
                userMap[i][j] = "☐";
            }
        }

        printMap(userMap);
        input.nextLine(); // 입력 버퍼 정리


        System.out.println("Game Start");
        while (game) {

            start = Instant.now();


            command = input.nextLine(); // 선택 입력
            game = cmdInterpreter(command);

            end = Instant.now();


        }

        printResult(result,start,end);
        input.nextLine(); // 입력 버퍼 정리

    }


    private Object[][] mapMaker(int size){

        //맵 만들고
        Object [][] map = new Object[size][size];

        // 지뢰 설정
        int numMines = (int) (size * size * 0.15); // 추후에 난이도 조절할 때는 여기 선택 가능하게 (int) 이건 왜 이렇게 쓰는감

        for(int i = 0; i < size;i++){
            for (int j = 0 ; j < size; j++){
                map[i][j] = "◼";
            }
        }

        // 지뢰를 맵에 배치하 -> 지뢰 표시 x 주변에 지뢰 없으면 ◼︎ 있으면 숫자 숫자 -> 섞어야함 ㄱ그러면 int  [][] x Object [][]
        placeMine(map, numMines);
        // 지뢰 배치하면 숫자 배치하고 없는 공간에는 ︎︎◼︎
        placeNum(map);


        return map;
    }

    private static void printMap(Object[][] map) {
        int size = map.length;

        // 열 번호 출력
        System.out.print("  "); // 왼쪽 상단 공백
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println(); // 줄 바꿈

        // 열 구분선 출력
        System.out.print("  "); // 왼쪽 상단 공백
        for (int i = 0; i < size; i++) {
            System.out.print("--");
        }
        System.out.println("-"); // 줄 바꿈

        // 행 번호와 맵 출력
        for (int i = 0; i < size; i++) {
            System.out.print(i + "|"); // 행 번호와 구분선
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(); // 줄 바꿈
        }
    }
    private void placeNum(Object[][] map) {
        System.out.println("printNum 이다");
        int size = map.length;
        //해당 위치의 상하좌우대각선을 체크
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int i = 0; i<size; i++ ){
            for(int j= 0; j <size ; j ++){
                if (!"x".equals(map[i][j])) {
                    int count = 0;

                    for (int k = 0; k < 8 ; k++){
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (isInBounds(ni,nj,size) && "x".equals(map[ni][nj])){
                            count ++;
                        }
                    }

                    if (count !=0){
                        map[i][j] = count;
                    }
                }
            }
        }
    }
    // 경계 확인
    private static boolean isInBounds(int row, int col, int size) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
    private void placeMine(Object[][] map, int numMines) {
        // 지뢰 배치는 랜덤
        Random random = new Random();
        int size = map.length;
        while (numMines > 0 ) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (!"x".equals(map[row][col])) {
                // Map의 값이 x 가 아니면 X면 이미 지뢰라 패스
                map[row][col] = "x";
                numMines--;
            }
        }
    }

    private boolean cmdInterpreter(String cmd) {
        if (cmd.equals("end")) {
            endGame();
            return false; // 게임 종료
        } else if (cmd.matches("\\d+,\\d+")) { // 좌표 형식인지 확인
            handleCoordinates(cmd); // 좌표 처리
            // TODO : 게임 종료 여부 체크 / 실패 여부 체크
            return true; // 게임 지속
        } else {
            System.out.println("Invalid command");
            return true; // 잘못된 명령어로도 게임 지속
        }
    }

    private void handleCoordinates(String coordinates) {
        // 좌표 값 체크
        String[] parts = coordinates.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());

        Object target = map[x][y];

        // TODO : 예외 처리 배열 밖을 입력받은 상황에 대한

        if (target.equals("x") ){
            System.out.println("!!!!! 지뢰다 !!!!!!");
        } else if (target.equals("◼")) {
            System.out.println("//주변에 아무것도 없음");
        } else {
            System.out.println(" 주변에 있다. ");
        }

    }


    private void printResult(String result, Instant start, Instant end){
        System.out.println("User Name : " + _name);
        System.out.println("time : " + +Duration.between(start, end).toMillis() + " ms");
        System.out.println("Click Enter");
    }

    private void endGame(){
        System.out.println("- 진행 중인 게임 종료 - ");
    }

}
