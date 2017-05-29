package com.paradice.suprabhat.paradice;

import java.util.Random;

import static org.random.util.RandomOrgAPIKey.RANDOM_API_KEY;

public enum Roller {
    instance;
    Random rand;

    private Roller() {
        rand = new org.random.util.RandomOrgRandom(RANDOM_API_KEY);
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
