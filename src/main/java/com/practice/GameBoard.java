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

        GameLevel gameLevel = GameLevel.fromInput(level);

        numMines = (int) (rows * cols * gameLevel.getMineRatio());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Tile(false);
            }
        }
        placeMines(numMines);
        countSurroundMine();
    }

    private void placeMines(int mines) {
        while (mines > 0) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);

            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                mines--;
            }
        }
    }

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
        if (!isInBounds(x, y))
            return;

        Tile tile = board[x][y];

        if (!tile.isOpened()) {
            tile.toggleFlag();
        }
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public void openTile(int x, int y, GameStateModel gameState) {
        if (!isInBounds(x, y))
            return;

        Tile tile = board[x][y];

        if (tile.isOpened()) {
            return;
        }

        tile.openTile();
        int mineCount = tile.getMinesCount();

        if (mineCount > 0) {
            return;
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (isInBounds(nx, ny)) {
                Tile neighborTile = board[nx][ny];

                if (!neighborTile.isOpened() && !neighborTile.isMine()) {
                    openTile(nx, ny, gameState);
                }
            }
        }
        if (allSafeOpen()) {
            //gameState.handleEvent("all_safe");
            gameState.handleEvent(GameEvent.VICTORY);
        }
    }

    public boolean allSafeOpen() {
        int totalCount = rows * cols ;
        int openCount = 0;

        for (int i =0 ; i < rows; i++) {
            for (int j = 0 ; j < cols; j ++) {
                Tile tile = board[i][j];

                if (tile.isOpened()) {
                    openCount++;
                }
            }
        }
        return (totalCount - numMines) == openCount;
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Tile[][] getBoard() {
        return board;
    }
}
