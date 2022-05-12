package com.company.multithreading.entity;

import java.util.ArrayList;
import java.util.List;

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

    public void setPiers(List<Pier> piers) {
        this.piers = piers;
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
}