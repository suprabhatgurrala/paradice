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
}
