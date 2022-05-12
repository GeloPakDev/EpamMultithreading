package com.company.multithreading;

import com.company.multithreading.entity.Dock;
import com.company.multithreading.service.PierService;
import com.company.multithreading.service.ShipGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Dock dock = new Dock();
        ShipGenerator shipGenerator = new ShipGenerator(dock, 20);

        PierService pierService = new PierService(dock);

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(shipGenerator);
        service.execute(pierService);

        service.shutdown();
    }
}