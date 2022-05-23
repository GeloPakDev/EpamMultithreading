package com.company.multithreading.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Port {
    public static final int PIER_COUNT = 5;
    private static final Port instance = new Port();
    private List<Pier> port;
    private int portCapacity;

    public static Port getInstance() {
        return instance;
    }

    private Port() {
        port = new ArrayList<>();
        portCapacity = 1000;
        for (int i = 0; i < PIER_COUNT; i++) {
            port.add(new Pier());
        }
    }

    public List<Pier> getPort() {
        return port;
    }

    public void setPort(List<Pier> port) {
        this.port = port;
    }

    public int getPortCapacity() {
        return portCapacity;
    }

    public void setPortCapacity(int portCapacity) {
        this.portCapacity = portCapacity;
    }

    public void increaseCapacity(int amount) {
        portCapacity += amount;
    }

    public void decreaseCapacity(int amount) {
        portCapacity -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Port port1 = (Port) o;

        if (portCapacity != port1.portCapacity) return false;
        return port != null ? port.equals(port1.port) : port1.port == null;
    }

    @Override
    public int hashCode() {
        int result = port != null ? port.hashCode() : 0;
        result = 31 * result + portCapacity;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Port.class.getSimpleName() + "[", "]")
                .add("port=" + port)
                .add("portCapacity=" + portCapacity)
                .toString();
    }
}