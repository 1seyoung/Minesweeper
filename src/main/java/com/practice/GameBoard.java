package com.practice;



public class GameBoard {
    private Tile[][] board;
    private int rows;
    private int cols;
    private int numMines;

    public GameBoard(int rows, int cols, String level) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Tile[rows][cols];
        this.numMines = 0;
        initializeBoard(rows, cols, level);


    }

    private void initializeBoard(int rows, int cols, String level) {

        GameLevel _level = GameLevel.fromInput(level);

        numMines = (int) (rows * cols * _level.getMineRatio());

        // 기존 Game.java 85 - 105 line
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Tile(false);
            }
        }

        //okay
        placeMines(numMines); // 여기서 실패(12.23.7시 02분) -> 해결
        countSurroundMine();



    }

    private void placeMines(int mines) {
        while (mines > 0) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);

            if (!board[row][col].isMine()) { // 여기 느낌표 빼먹어서 그런듯
                board[row][col].setMine(true);
                mines--;
            }
        }


    }

    // Game.java 133 - 159 line
    public void countSurroundMine() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].isMine()) {
                    int countMines = 0;

                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (isInBounds(nx, ny) && board[nx][ny].isMine()) {
                            countMines++;
                        }
                    }

                    if (countMines != 0) {
                        board[i][j].setMinesCount(countMines);
                    }
                }
            }
        }
    }

    public void checkedFlag(int x, int y) {
        // x,y 가 범위 밖이면 일단 그냥 종료
        if (!isInBounds(x, y))
            return;

        Tile tile = board[x][y];

        // 해당 타일이 열린게 아니면 깃발해주기 이미 열린건 x
        if (!tile.isOpened()) {
            // 만약에 이미 깃발이면 깃발 해제로 인식
            tile.toggleFlag();
        }
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Tile tile = board[i][j];

                if (tile.isFlag()) {
                    System.out.print("F "); // 깃발이 설정된 타일
                } else if (!tile.isOpened()) {
                    System.out.print("# "); // 닫힌 타일
                } else if (tile.isMine()) {
                    System.out.print("* "); // 지뢰 타일
                } else {
                    int neighborMineCount = tile.getMinesCount();
                    if (neighborMineCount > 0) {
                        System.out.print(neighborMineCount + " "); // 주변 지뢰 개수
                    } else {
                        System.out.print("  "); // 빈 타일
                    }
                }
            }
            System.out.println();
        }
    }

    public void openTile(int x, int y, GameStateModel gameState) {
        if (!isInBounds(x, y))
            return; // 범위체크

        Tile tile = board[x][y];

        if (tile.isOpened()) {
            return;
        }

        tile.openTile();
        int mineCount = tile.getMinesCount();

        // 주변 지뢰가 있는 경우: 숫자를 표시하고 탐색 중지
        if (mineCount > 0) {
            return;
        }

        // 주변에 지뢰없는 칸인 경우 : 숫자를 경계로 다 열어
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (isInBounds(nx, ny)) {
                Tile neighborTile = board[nx][ny];

                // 인접 타일이 열려 있지 않고, 지뢰가 아닌 경우만 탐색
                if (!neighborTile.isOpened() && !neighborTile.isMine()) {
                    openTile(nx, ny, gameState); // 재귀적으로 인접 타일 열기
                }
            }
        }
        // 승리 조건 확인
        if (allSafeOpen()) {
            gameState.handleEvent("all_safe");
        }

    }

    public boolean allSafeOpen() {
        int totalCount = rows * cols ; // 전체 타일 수
        int openCount = 0;// 열린 타일 수

        // 이중 포문으로 전체 탐색
        for (int i =0 ; i < rows; i++) {
            for (int j = 0 ; j < cols; j ++) {
                Tile tile = board[i][j];

                if (tile.isOpened()) {
                    openCount++; // 열린 안전 타일 수 계산
                }
            }
        }

        return (totalCount - numMines) == openCount;

    }



    }
