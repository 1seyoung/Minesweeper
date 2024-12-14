package com.practice;

import java.time.Instant;
import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

//TODO : 지뢰 플래그 /언플래그 기능 , 다 잘 선택하면 게임 이긴걸로 결과 출력하는걸로

public class Game {

    private final String _name;
    public Object[][] map ;
    public Object[][] userMap;

    public Game(String name){
        this._name = name;
    }


    public void startGame() {
        boolean game = true;
        String message = "";
        String command;
        int mapSize = 9;

        Instant start = null; // 이거를  여기에 정의..? 왜
        Instant end = null; // 반복문 안에서 정의 x...
        Scanner input = new Scanner(System.in);

        System.out.println("Choice Map Size (9 / 25/ 36)" );


        // 맵 크기 입력 처리
        while (true) {
            try {
                mapSize = Integer.parseInt(input.nextLine().trim()); // 개행 문자 문제 해결
                if (mapSize == 9 || mapSize == 25 || mapSize == 36) {
                    break;
                } else {
                    System.out.println("Invalid size. Please choose 9, 25, or 36.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (9, 25, 36).");
            }
        }

        map = mapMaker(mapSize); // 정답 map
        userMap = new Object[mapSize][mapSize];

        for(int i = 0; i < mapSize ; i++){
            for (int j = 0; j < mapSize ; j++){
                userMap[i][j] = "☐";
            }
        }
        printMap(map);
        System.out.println("Game Start");
        while (game) {

            start = Instant.now();
            printMap(userMap);

            command = input.nextLine().trim(); // 선택 입력
            CommandResult result = cmdInterpreter(command); // 어떤 문법이냐 new는 없는데

            assert result != null; // TODO :  왜 이런걸 추천을?
            game = result.isGameContinues();
            message = result.getMessage();



        }
        end = Instant.now();

        printResult(message,start,end);
        input.nextLine(); // 입력 버퍼 정리

    }


    private Object[][] mapMaker(int size){
        //맵 만들고
        Object [][] _map = new Object[size][size];

        // 지뢰 설정
        int numMines = (int) (size * size * 0.15); // 추후에 난이도 조절할 때는 여기 선택 가능하게 (int) 이건 왜 이렇게 쓰는감

        for(int i = 0; i < size;i++){
            for (int j = 0 ; j < size; j++){
                _map[i][j] = "◼";
            }
        }

        // 지뢰를 맵에 배치하 -> 지뢰 표시 x 주변에 지뢰 없으면 ◼︎ 있으면 숫자 숫자 -> 섞어야함 ㄱ그러면 int  [][] x Object [][]
        placeMine(_map, numMines);
        // 지뢰 배치하면 숫자 배치하고 없는 공간에는 ︎︎◼︎
        placeNum(_map);


        return _map;
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
    private void placeNum(Object [][] _map) {
        int size = _map.length;
        //해당 위치의 상하좌우대각선을 체크
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int i = 0; i<size; i++ ){
            for(int j= 0; j <size ; j ++){
                if (!"x".equals(_map[i][j])) {
                    int count = 0;

                    for (int k = 0; k < 8 ; k++){
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (isInBounds(ni,nj,size) && "x".equals(_map[ni][nj])){
                            count ++;
                        }
                    }

                    if (count !=0){
                        _map[i][j] = count;
                    }
                }
            }
        }
    }
    // 경계 확인
    private static boolean isInBounds(int row, int col, int size) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
    private void placeMine(Object[][] _map, int numMines) {
        // 지뢰 배치는 랜덤
        Random random = new Random();
        int size = _map.length;
        while (numMines > 0 ) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (!"x".equals(_map[row][col])) {
                // Map의 값이 x 가 아니면 X면 이미 지뢰라 패스
                _map[row][col] = "x";
                numMines--;
            }
        }
    }

    private CommandResult cmdInterpreter(String cmd) {
        String result = "";
        if (cmd.equals("end")) {
            endGame();
            return new CommandResult(false, "게임 종료"); // 게임 종료
        } else if (cmd.matches("\\d+,\\d+")) { // 좌표 형식인지 확인
            result = handleCoordinates(cmd); // 좌표 처리
            if (result.equals("bomb")){
                return new CommandResult(false, "지뢰 클릭");
            }
            // TODO : 게임 종료 여부 체크 / 실패 여부 체크
            if (result.equals("empty")){
                return new CommandResult(true);
            }
            if (result.equals("number")){
                return new CommandResult(true);
            }

        } else {
            System.out.println("Invalid command");
            return new CommandResult(true );
        }
        return null; // 이게 없으면 왜 큰일인가
    }

    private String handleCoordinates(String coordinates) {
        // 좌표 값 체크
        String[] parts = coordinates.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());

        Object target = map[x][y];

        // TODO : 예외 처리 배열 밖을 입력받은 상황에 대한

        if (target.equals("x") ){
            System.out.println("!!!!! 지뢰다 !!!!!!");
            return "bomb";
            //게임 끝나야함
        } else if (target.equals("◼")) {
            System.out.println("//주변에 아무것도 없음");
            userMapChange(x,y);
            return "empty";
        } else {
            System.out.println(" 주변에 있다. ");
            userMapChange(x,y);
            return "number";

        }

    }

    private void userMapChange(int x, int y) {
        //userMap[x][y] = map[x][y]; -> 참조가 옮겨가서 문제인가보다

        int size = map.length;

        Object value = map[x][y];
        userMap[x][y] = value;

        //해당 위치의 상하좌우대각선을 체크
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        /// 바보다... 여기서는 전체 탐색이 아니고 들어온 x,y 값 기준으로

        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            // 경계 조건 체크
            if (isInBounds(nx, ny, size)) {
                value = map[nx][ny];
                // 빈 공간(◼)이면 계속 탐색
                if ("◼".equals(value) && !"◼".equals(userMap[nx][ny])) {
                    userMapChange(nx, ny); // 재귀 호출로 연결된 빈 공간 탐색
                }
                // 숫자를 발견하면 값 복사만 수행 (탐색 종료)
                else if (isNumeric(value)) {
                    userMap[nx][ny] = value;
                }
            }
        }
    }

    // 숫자 여부 확인 유틸리티 메서드
    private boolean isNumeric(Object obj) {
        if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return obj instanceof Number;
    }


    private void printResult(String result, Instant start, Instant end){
        System.out.println("User Name : " + _name);
        System.out.println("result : " + result);
        System.out.println("time : " + Duration.between(start, end).toMillis() + " ms");
        System.out.println("Click Enter");
    }

    private void endGame(){
        System.out.println("- 진행 중인 게임 종료 - ");
    }

}
