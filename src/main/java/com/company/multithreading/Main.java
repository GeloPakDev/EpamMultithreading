package com.company.multithreading;

import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Port;
import com.company.multithreading.service.ShipGenerator;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        Port port = Port.getInstance();
        for (Pier pier : port.getPort()) {
            executors.submit(pier);
        }
        ShipGenerator shipGenerator = new ShipGenerator(12);
        shipGenerator.run();
    }
}