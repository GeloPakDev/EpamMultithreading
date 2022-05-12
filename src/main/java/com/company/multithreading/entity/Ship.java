package com.company.multithreading.entity;

import com.company.multithreading.types.LoadType;

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
}