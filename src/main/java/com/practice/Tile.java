package com.practice;

public class Tile {
    private boolean isMine;
    private boolean isFlag;
    private boolean isOpen;
    private int surroundMineCount;

    public Tile(boolean isMine) {
        this.isMine = isMine;
        this.isOpen = false;
        this.isFlag = false;
        this.surroundMineCount = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isOpened() {
        return isOpen;
    }

    public void openTile() {
        this.isOpen = true;
    }

    public int getMinesCount() {
        return surroundMineCount;
    }

    public void setMinesCount(int countMines) {
        this.surroundMineCount = countMines;
    }

    public void toggleFlag() {
        this.isFlag = !this.isFlag;

    }

    public boolean isFlag() {
        return isFlag;
    }
}
