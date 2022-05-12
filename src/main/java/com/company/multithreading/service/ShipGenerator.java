package com.company.multithreading.service;

import com.company.multithreading.entity.Dock;
import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Ship;
import com.company.multithreading.types.LoadType;

import java.util.List;
import java.util.Random;

//Made for fulfilling the Piers by Ships
public class ShipGenerator implements Runnable {
    //As we need fulfill Piers with Ships , get access to the Dock as it contains all the Piers
    private Dock dock;
    private int quantity;

    public ShipGenerator(Dock dock, int quantity) {
        this.dock = dock;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        //Here we should generate the number of ships which are coming from the constructor
        int count = 0;
        while (count < quantity) {
            Thread.currentThread().setName(" Generator ship");
            count++;
            List<Pier> piers = dock.getPiers();
            for (Pier pier : piers) {
                pier.add(new Ship(generateSize(), generateType()));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateSize() {
        Random random = new Random();
        return random.nextInt(200) + 100;
    }

    private LoadType generateType() {
        Random random = new Random();
        return LoadType.values()[random.nextInt(LoadType.values().length)];
    }
}