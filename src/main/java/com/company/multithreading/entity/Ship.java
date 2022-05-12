package com.company.multithreading.entity;

import com.company.multithreading.types.LoadType;

import java.util.StringJoiner;

public class Ship {
    private int size;
    private LoadType loadType;

    public Ship(int size, LoadType loadType) {
        this.size = size;
        this.loadType = loadType;
    }

    public void action(int count) {
        if (loadType == LoadType.UNLOAD) {
            size -= count;
        } else if (loadType == LoadType.UPLOAD) {
            size += count;
        }
    }

    public LoadType getType() {
        return loadType;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ship.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("loadType=" + loadType)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (size != ship.size) return false;
        return loadType == ship.loadType;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + (loadType != null ? loadType.hashCode() : 0);
        return result;
    }

}