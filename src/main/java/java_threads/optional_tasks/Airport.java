package java_threads.optional_tasks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {

    private static Airport airport;
    private final String [] runwayStripArray = {"Полоса 1", "Полоса 2", "Полоса 3", "Полоса 4",
            "Полоса 5"};
    private final Lock [] lockArray = {new ReentrantLock(true), new ReentrantLock(true),
            new ReentrantLock(true), new ReentrantLock(true), new ReentrantLock(true)};

    private Airport() {}

    public static Airport getAirport() {
        if (airport == null) {
            airport = new Airport();
        }
        return airport;
    }

    public String getRunwayStripName(int id) {
        return runwayStripArray[id];
    }

    public Lock getLockArray(int id) {
        return lockArray[id];
    }
}
