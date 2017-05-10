package com.paradice.suprabhat.paradice;

import android.util.Log;

import java.util.Random;

public enum APIClient {
    instance;
    Random rand;

    private APIClient() {
        rand = new Random();
    }

    public static int getRandomNumber(int maxVal) {
        return instance.rand.nextInt(maxVal);
    }

    public static String getDiceRoll() {
        int num = getRandomNumber(6);
        String roll = "";
        switch (num) {
            case 0:
                roll = "\u2680";
                break;
            case 1:
                roll = "\u2681";
                break;
            case 2:
                roll = "\u2682";
                break;
            case 3:
                roll = "\u2683";
                break;
            case 4:
                roll = "\u2684";
                break;
            case 5:
                roll = "\u2685";
                break;
            default:
                roll = "Tap to roll dice.";
                break;
        }
        return roll;
    }

    public static String getMultipleDiceRolls(int num) {
        String rolls = "";
        for (int i = 0; i < num; i++) {
            rolls += getDiceRoll();
        }
        return rolls;
    }
}
