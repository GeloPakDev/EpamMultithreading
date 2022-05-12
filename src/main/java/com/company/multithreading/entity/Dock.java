package com.company.multithreading.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Dock {
    //TODO:Add Singleton
    private List<Pier> piers;
    private int capacity = 1000;
    private int availablePierCounter = 5;

    public Dock() {
        piers = new ArrayList<>();
        for (int i = 0; i < availablePierCounter; i++) {
            piers.add(new Pier());
        }
    }
    //
    public synchronized Pier getPier() {
        try {
            notifyAll();
            for (Pier pier : piers) {
                //If pier is not empty move to the another
                if (pier.isBusy()) {
                    availablePierCounter--;
                }
                return pier;
            }
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pier> getPiers() {
        return piers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailablePierCounter() {
        return availablePierCounter;
    }

    public void setAvailablePierCounter(int availablePierCounter) {
        this.availablePierCounter = availablePierCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dock dock = (Dock) o;

        if (capacity != dock.capacity) return false;
        if (availablePierCounter != dock.availablePierCounter) return false;
        return piers != null ? piers.equals(dock.piers) : dock.piers == null;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dock.class.getSimpleName() + "[", "]")
                .add("piers=" + piers)
                .add("capacity=" + capacity)
                .add("availablePierCounter=" + availablePierCounter)
                .toString();
    }

    @Override
    public int hashCode() {
        int result = piers != null ? piers.hashCode() : 0;
        result = 31 * result + capacity;
        result = 31 * result + availablePierCounter;
        return result;
    }
}