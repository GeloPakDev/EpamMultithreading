package com.company.multithreading.entity;

import com.company.multithreading.type.LoadType;
import com.company.multithreading.type.Size;

import java.util.StringJoiner;

public class Ship extends Thread {
    private int shipId;
    private Size size;
    private LoadType type;

    public Ship(int shipId, Size size, LoadType type) {
        this.shipId = shipId;
        this.size = size;
        this.type = type;
    }

    @Override
    public void run() {
        Tunnel tunnel = Tunnel.getInstance();
        tunnel.addShip(this);
    }

    public LoadType getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setType(LoadType type) {
        this.type = type;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (shipId != ship.shipId) return false;
        if (size != ship.size) return false;
        return type == ship.type;
    }

    @Override
    public int hashCode() {
        int result = shipId;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ship.class.getSimpleName() + "[", "]")
                .add("shipId=" + shipId)
                .add("size=" + size)
                .add("type=" + type)
                .toString();
    }
}