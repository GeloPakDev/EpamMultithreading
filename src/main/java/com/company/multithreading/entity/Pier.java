package com.company.multithreading.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Pier {
    private List<Ship> pier;
    private static final int maxShipsInPier = 1;
    private static final int minShipsInPier = 0;
    private int shipsCounter = 0;

    public Pier() {
        pier = new ArrayList<>();
    }

    public synchronized boolean add(Ship ship) {
        try {
            if (shipsCounter < maxShipsInPier) {
                notifyAll();
                pier.add(ship);
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
                for (Ship ship : pier) {
                    shipsCounter--;
                    pier.remove(ship);
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Pier.class.getSimpleName() + "[", "]")
                .add("pier=" + pier)
                .add("shipsCounter=" + shipsCounter)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pier pier1 = (Pier) o;

        if (shipsCounter != pier1.shipsCounter) return false;
        return pier != null ? pier.equals(pier1.pier) : pier1.pier == null;
    }

    @Override
    public int hashCode() {
        int result = pier != null ? pier.hashCode() : 0;
        result = 31 * result + shipsCounter;
        return result;
    }


}