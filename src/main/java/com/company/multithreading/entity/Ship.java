package com.company.multithreading.entity;

import com.company.multithreading.types.LoadType;

import java.util.StringJoiner;

public class Ship {
    private int capacity;
    private LoadType loadType;

    public Ship(int capacity, LoadType loadType) {
        this.capacity = capacity;
        this.loadType = loadType;
    }

    public void action(int count) {
        if (loadType == LoadType.UNLOAD) {
            capacity -= count;
        } else if (loadType == LoadType.UPLOAD) {
            capacity += count;
        }
    }

    public LoadType getType() {
        return loadType;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ship.class.getSimpleName() + "[", "]")
                .add("size=" + capacity)
                .add("loadType=" + loadType)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (capacity != ship.capacity) return false;
        return loadType == ship.loadType;
    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + (loadType != null ? loadType.hashCode() : 0);
        return result;
    }

}