package com.company.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static final Logger logger = LogManager.getLogger();
    private static final Tunnel instance = new Tunnel();
    private static final int maximumShipsInTunnel = 10;
    private static final int minimumShipsInTunnel = 0;
    private int shipsCounter = 0;
    private List<Ship> store;

    public static Tunnel getInstance() {
        return instance;
    }

    private Tunnel() {
        store = new ArrayList<>();
    }

    //Ship is Calling this method
    public synchronized void addShip(Ship element) {
        try {
            if (shipsCounter < maximumShipsInTunnel) {
                notifyAll();
                store.add(element);
                String info = String.format("%s + Ship(id: %s action: %s size: %s)", store.size(), element.getShipId(), element.getType(), element.getSize());
                System.out.println(info);
                TimeUnit.MILLISECONDS.sleep(1000);
                shipsCounter++;
            } else {
                System.out.println(store.size() + "> There is no place for a ship in the tunnel: " + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(500);
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Pier is Calling this method
    public synchronized Ship getShip() {
        try {
            if (shipsCounter > minimumShipsInTunnel) {
                notifyAll();
                for (Ship ship : store) {
                    shipsCounter--;
                    logger.info("The ship is taken from the tunnel: " + Thread.currentThread().getName());
                    System.out.println("The ship " + ship.getShipId() + " is taken from the tunnel");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    store.remove(ship);
                    return ship;
                }
            } else {
                logger.info("There are no ships in the tunnel");
                System.out.println("There are no ships in the tunnel");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ship> getStore() {
        return store;
    }

    public int getShipsCounter() {
        return shipsCounter;
    }

    public void setShipsCounter(int shipsCounter) {
        this.shipsCounter = shipsCounter;
    }

    public void setStore(List<Ship> store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tunnel tunnel = (Tunnel) o;

        if (shipsCounter != tunnel.shipsCounter) return false;
        return store != null ? store.equals(tunnel.store) : tunnel.store == null;
    }

    @Override
    public int hashCode() {
        int result = shipsCounter;
        result = 31 * result + (store != null ? store.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tunnel.class.getSimpleName() + "[", "]")
                .add("shipsCounter=" + shipsCounter)
                .add("store=" + store)
                .toString();
    }
}