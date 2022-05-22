package com.company.multithreading.service;

import com.company.multithreading.entity.Ship;
import com.company.multithreading.entity.Tunnel;
import com.company.multithreading.type.LoadType;
import com.company.multithreading.type.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ShipGenerator {
    private int shipsCount;
    private Tunnel tunnel;
    private static final Logger logger = LogManager.getLogger();

    public ShipGenerator(int shipsCount) {
        tunnel = Tunnel.getInstance();
        this.shipsCount = shipsCount;
    }

    public void run() {
        int count = 0;
        try {
            while (count < shipsCount) {
                Thread.currentThread().setName(" Ship Generator");
                count++;
                //Adding it to the Tunnel
                tunnel.addShip(new Ship(generateShipId(), generateRandomSize(), generateRandomType()));
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            logger.info("All ships uploaded");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private LoadType generateRandomType() {
        Random random = new Random();
        return LoadType.values()[random.nextInt(LoadType.values().length)];
    }

    private Size generateRandomSize() {
        Random random = new Random();
        return Size.values()[random.nextInt(Size.values().length)];
    }

    private int generateShipId() {
        Random random = new Random();
        return random.nextInt(20) + 10;
    }
}