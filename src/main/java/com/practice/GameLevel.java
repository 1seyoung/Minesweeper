package com.practice;

public enum GameLevel {
    EASY(0.15),
    MEDIUM(0.20),
    HARD(0.25);

    private final double mineRatio;

    GameLevel(double mineRatio) {
        this.mineRatio = mineRatio;
    }

    public double getMineRatio() {
        return mineRatio;
    }

    public static GameLevel fromInput(String input) {
        try {
            return GameLevel.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return EASY;
        }
    }

}
