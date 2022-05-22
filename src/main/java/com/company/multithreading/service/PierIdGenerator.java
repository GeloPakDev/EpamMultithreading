package com.company.multithreading.service;

import java.util.Random;

public class PierIdGenerator {
    public static int generatePierId() {
        Random random = new Random();
        return random.nextInt(20) + 10;
    }
}