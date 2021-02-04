package java_threads.main_task;

import java.util.Random;

public class Vehicle implements Runnable {
    private final int id;
    private final ParkingLot parkingLot = ParkingLot.getParkingLot();
    private final Integer randomSleep = new Random().nextInt(10000);

    public Vehicle(int name) {
        this.id = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println("Vehicle " + id + " enters parking lot");
        if (parkingLot.emptyParkingSpaceAvailable()) {
            park();
            vehicleParkingTime();
            leaveParkingLot();
        } else {
            waitForAvailableParkSpace();
            if (parkingLot.emptyParkingSpaceAvailable()) {
                park();
                vehicleParkingTime();
                leaveParkingLot();
            } else {
                lookForAnotherParkingLot();
            }
        }
    }

    public void park() {
        synchronized (parkingLot) {
            System.out.println("Vehicle " + id + " is parked. Available spaces left " +
                    parkingLot.availableParkingSpaceQuantity);
        }
    }

    public void vehicleParkingTime() {
        try {
            Thread.sleep(randomSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void leaveParkingLot() {
        synchronized (parkingLot) {
            parkingLot.incrementAvailableParkingSpaceQuantity();
            System.out.println("Vehicle " + id + " is leaving parking lot. Available " +
                    "spaces left " + parkingLot.availableParkingSpaceQuantity);
            parkingLot.notify();
        }
    }

    public void waitForAvailableParkSpace() {
        synchronized (parkingLot) {
            try {
                System.out.println("Vehicle " + id + " is waiting");
                parkingLot.wait(5000);
                System.out.println("Vehicle " + id + " stops waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void lookForAnotherParkingLot() {
        System.out.println("Vehicle " + id + " is leaving to find another " +
                "parking " + "lot" + ".");
    }
}
