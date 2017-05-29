package com.paradice.suprabhat.paradice;

import org.random.api.RandomOrgCache;
import org.random.api.RandomOrgClient;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.random.util.RandomOrgAPIKey.RANDOM_API_KEY;

/**
 * Class to generate random rolls. Defined as enum for Singleton design pattern.
 */
public enum Roller {
    instance;
    int[] randomCache;
    int index = 0;
    Random rand = new Random();

    /**
     * Private constructor to initially populate cache.
     */
    Roller() {
        populateCache();
    }

    /**
     * Gets the next value available in the cache or repopulates cache if it has been used.
     * @return next available value in cache.
     */
    public static int getRandomNumber() {
        if (instance.index >= instance.randomCache.length) {
            populateCache();
            instance.index = 0;
        }
        return instance.randomCache[instance.index++];
    }

    /**
     * Populates cache using Random.org data. If data is not available, populates a temporary cache
     * of pseudorandom numbers.
     */
    public static void populateCache() {
        RandomOrgClient roc = RandomOrgClient.getRandomOrgClient(RANDOM_API_KEY);
        RandomOrgCache<int[]> c = roc.createIntegerCache(1000, 0, 5);

        int[] randoms;

        try {
             randoms = c.get();

        } catch (NoSuchElementException e) {
            randoms = new int[10];
            Random rand = new Random();
            randoms[0] = rand.nextInt(6);
        }
        instance.randomCache = randoms;
    }

    /**
     * Converts integers to Unicode dice characters
     * @return Unicode dice String representing the roll
     */
    public static String getDiceRoll() {
        int num = getRandomNumber();
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

    /**
     * Gets multiple dice rolls at once.
     * @param num number of dice rolls to retrieve
     * @return a String of Unicode dice chars
     */
    public static String getMultipleDiceRolls(int num) {
        String rolls = "";
        for (int i = 0; i < num; i++) {
            rolls += getDiceRoll();
        }
        return rolls;
    }
}
