package com.paradice.suprabhat.paradice;

import android.util.Log;

import org.random.api.RandomOrgCache;
import org.random.api.RandomOrgClient;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.random.util.RandomOrgAPIKey.RANDOM_API_KEY;

/**
 * Class to generate random rolls. Defined as enum for Singleton design pattern.
 */
public enum Roller {
    INSTANCE;
    private int[] randomCache = null;
    private int index = 0;
    private Random rand = new Random();

    /**
     * Gets the next value available in the cache or repopulates cache if it has been used.
     * @return next available value in cache.
     */
    public static int getRandomNumber() {
        if (INSTANCE.randomCache == null || INSTANCE.index >= INSTANCE.randomCache.length) {
            populateCache();
            INSTANCE.index = 0;
        }
        Log.d("Roller.java", "Accessing cache at index " + INSTANCE.index + ": " + Arrays.toString(INSTANCE.randomCache));
        return INSTANCE.randomCache[INSTANCE.index++];
    }

    /**
     * Populates cache using Random.org data. If data is not available, populates a temporary cache
     * of pseudorandom numbers.
     */
    public static void populateCache() {
        Log.d("Roller.java", "Attempting to populate cache.");
        RandomOrgClient roc = RandomOrgClient.getRandomOrgClient(RANDOM_API_KEY);
        RandomOrgCache<int[]> c = roc.createIntegerCache(1000, 0, 5);

        int[] randoms;

        if (INSTANCE == null) {
            Log.d("Roller.java", "INSTANCE is null");
        }
        if (INSTANCE.randomCache == null) {
            Log.d("Roller.java", "randomCache is null");
        }

        try {
            randoms = c.get();
            Log.d("Roller.java", "Got data from API: " + Arrays.toString(randoms));
            if (randoms == null) {
                throw new NoSuchElementException("Got a null response.");
            }
        } catch (NoSuchElementException e) {
            Log.d("Roller.java", "API Data unavailable, populating short term cache with psuedorandom data");
            randoms = new int[10];
            for (int i = 0; i < randoms.length; i++) {
                randoms[i] = INSTANCE.rand.nextInt(6);
            }
        }
        Log.d("Roller.java", "New cache: " + Arrays.toString(randoms));
        INSTANCE.randomCache = randoms;
        Log.d("Roller.java", "Populated cache.");
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
