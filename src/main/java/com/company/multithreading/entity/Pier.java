package com.company.multithreading.entity;

import com.company.multithreading.service.PierIdGenerator;
import com.company.multithreading.type.LoadType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Pier extends Thread {
    private static final Logger logger = LogManager.getLogger();
    private final Port port = Port.getInstance();
    private int pierId;

    public Pier() {
        this.pierId = PierIdGenerator.generatePierId();
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Pier {} is ready for work", pierId);
        try {
            while (!isInterrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Tunnel tunnel = Tunnel.getInstance();
                Ship ship = tunnel.getShip();
                if (ship != null) {
                    if (ship.getType() == LoadType.UNLOAD) {
                        logger.log(Level.INFO, "Add " + ship.getSize().getValue() + " to the port from ship " + ship.getShipId());
                        unloadFromShip(ship);
                        logger.log(Level.INFO, "Capacity after unloading : " + port.getPortCapacity());
                    } else if (ship.getType() == LoadType.UPLOAD) {
                        logger.log(Level.INFO, "Decrease " + ship.getSize().getValue() + " from the port to ship " + ship.getShipId());
                        uploadToShip(ship);
                        logger.log(Level.INFO, "Capacity after uploading " + port.getPortCapacity());
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void uploadToShip(Ship ship) {
        int portCapacity = port.getPortCapacity();
        int shipCapacity = ship.getSize().getValue();
        boolean uploaded = false;
        //Lock next block of code
        while (!uploaded) {
            try {
                if (portCapacity - shipCapacity > 0) {
                    notifyAll();
                    TimeUnit.MILLISECONDS.sleep(1000);
                    port.decreaseCapacity(shipCapacity);
                    uploaded = true;
                }
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void unloadFromShip(Ship ship) {
        int portCapacity = port.getPortCapacity();
        int shipCapacity = ship.getSize().getValue();
        boolean uploaded = false;
        while (!uploaded) {
            try {
                if (portCapacity + shipCapacity < 1000) {
                    notifyAll();
                    TimeUnit.MILLISECONDS.sleep(1000);
                    port.increaseCapacity(shipCapacity);
                    uploaded = true;
                } else {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPierId() {
        return pierId;
    }

    public void setPierId(int pierId) {
        this.pierId = pierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pier pier = (Pier) o;

        if (pierId != pier.pierId) return false;
        return port != null ? port.equals(pier.port) : pier.port == null;
    }

    @Override
    public int hashCode() {
        int result = port != null ? port.hashCode() : 0;
        result = 31 * result + pierId;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pier.class.getSimpleName() + "[", "]")
                .add("port=" + port)
                .add("pierId=" + pierId)
                .toString();
    }
}
