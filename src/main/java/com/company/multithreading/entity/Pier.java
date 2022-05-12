package com.company.multithreading.entity;

import java.util.ArrayList;
import java.util.List;

public class Pier {
    private List<Ship> store;
    private static final int maxShipsInPier = 1;
    private static final int minShipsInPier = 0;
    private int shipsCounter = 0;

    public Pier() {
        store = new ArrayList<>();
    }

    public synchronized boolean add(Ship ship) {
        try {
            if (shipsCounter < maxShipsInPier) {
                notifyAll();
                store.add(ship);
                shipsCounter++;
            } else {
                wait();
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized Ship get() {
        try {
            if (shipsCounter > minShipsInPier) {
                notifyAll();
                for (Ship ship : store) {
                    shipsCounter--;
                    store.remove(ship);
                    return ship;
                }
            }
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isBusy() {
        return shipsCounter >= maxShipsInPier;
    }
}