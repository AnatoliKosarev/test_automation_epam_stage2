package java_threads.main_task;

public class ParkingLot {
    private volatile static ParkingLot parkingLot;
    public int availableParkingSpaceQuantity = 3;

    private ParkingLot() {}

    public static ParkingLot getParkingLot() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public synchronized boolean emptyParkingSpaceAvailable() {
        if (availableParkingSpaceQuantity > 0) {
            decrementAvailableParkingSpaceQuantity();
            return true;
        } else {
            return false;
        }
    }

    public void incrementAvailableParkingSpaceQuantity() {
            availableParkingSpaceQuantity++;
    }

    public void decrementAvailableParkingSpaceQuantity() {
            availableParkingSpaceQuantity--;
    }


}
