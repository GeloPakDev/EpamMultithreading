package com.company.multithreading.entity;

import com.company.multithreading.service.PortService;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Ship implements Callable<Integer> {
    private AtomicInteger cargo = new AtomicInteger();
    private AtomicBoolean isFull = new AtomicBoolean();
    private AtomicInteger dockNumber = new AtomicInteger();
    private PortService portManager;

    public Ship(int cargo, boolean isFull, PortService portManager) {
        this.cargo.set(cargo);
        this.isFull.set(isFull);
        this.portManager = portManager;
    }

    @Override
    public Integer call() {
        return 0;
    }

    public AtomicInteger getCargo() {
        return cargo;
    }

    public AtomicBoolean getIsFull() {
        return isFull;
    }

    public AtomicInteger getDockNumber() {
        return dockNumber;
    }

    public void setDockNumber(int dockNumber) {
        this.dockNumber.set(dockNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (cargo != null ? !cargo.equals(ship.cargo) : ship.cargo != null) return false;
        if (isFull != null ? !isFull.equals(ship.isFull) : ship.isFull != null) return false;
        if (dockNumber != null ? !dockNumber.equals(ship.dockNumber) : ship.dockNumber != null) return false;
        return portManager != null ? portManager.equals(ship.portManager) : ship.portManager == null;
    }

    @Override
    public int hashCode() {
        int result = cargo != null ? cargo.hashCode() : 0;
        result = 31 * result + (isFull != null ? isFull.hashCode() : 0);
        result = 31 * result + (dockNumber != null ? dockNumber.hashCode() : 0);
        result = 31 * result + (portManager != null ? portManager.hashCode() : 0);
        return result;
    }
}
