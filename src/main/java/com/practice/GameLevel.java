package com.practice;

import java.util.Locale;

public enum GameLevel {
    EASY(0.15),
    MEDIUM(0.20),
    HARD(0.25);

    private final double mineRatio;
    // 생성자
    GameLevel(double mineRatio) {
        this.mineRatio = mineRatio;
    }

    public double getMineRatio() {
        return mineRatio;
    }
    // 입력 값에 따라 난이도 반환 (유효하지 않은 경우 EASY 반환)
    public static GameLevel fromInput(String input) {
        switch (input.toUpperCase()) {
            case "EASY":
                return EASY;
            case "MEDIUM":
                return MEDIUM;
            case "HARD":
                return HARD;
            default:
                // 잘못된 난이도 들어오면 난이도 쉬움으로 세팅
                return EASY;
        }
    }

}
