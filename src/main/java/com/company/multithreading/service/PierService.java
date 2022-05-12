package com.company.multithreading.service;

import com.company.multithreading.entity.Dock;
import com.company.multithreading.entity.Pier;
import com.company.multithreading.entity.Ship;
import com.company.multithreading.types.LoadType;

import java.util.Random;

public class PierService implements Runnable {
    private Dock dock;

    public PierService(Dock dock) {
        this.dock = dock;
    }

    @Override
    public void run() {
        //continue fulfilling the dock
        //TODO think about the condition to stop the execution
        while (true) {
            try {
                Thread.sleep(500);
                //get the Pier from the Dock and check is it empty or not
                Pier pier = dock.getPier();
                if (pier != null) {
                    Ship ship = pier.get();
                    if (ship != null) {
                        int count = generateSize();
                        ship.action(count);
                        if (ship.getType() == LoadType.UNLOAD) {
                            dock.setCapacity(dock.getCapacity() - count);
                            break;
                        } else if (ship.getType() == LoadType.UPLOAD) {
                            dock.setCapacity(dock.getCapacity() + count);
                            break;
                        }
                        //while (ship.countCheck()) {
                        //    //check action a and countCeh ck
                        //    Thread.sleep(100);
                        //    dock.setCapacity(dock.getCapacity());
                        //    ship.action();
                        //    //System.out.println(ship.getCount() + " Loaded ship. " + Thread.currentThread().getName());
                        //}
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateSize() {
        Random random = new Random();
        return random.nextInt(5) + 20;
    }
}