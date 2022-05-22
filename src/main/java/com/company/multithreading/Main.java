package com.company.multithreading;

import com.company.multithreading.entity.Pier;
import com.company.multithreading.service.ShipGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        Pier pier = new Pier();
        Pier pier1 = new Pier();
        Pier pier2 = new Pier();
        Pier pier3 = new Pier();
        executors.execute(pier);
        executors.execute(pier1);
        executors.execute(pier2);
        executors.execute(pier3);
        ShipGenerator shipGenerator = new ShipGenerator(12);
        shipGenerator.run();
    }
}