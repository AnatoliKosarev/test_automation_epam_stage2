package java_threads.optional_tasks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {

    private static Airport airport;
    private final String [] runwayStripArray = {"Полоса 1", "Полоса 2", "Полоса 3", "Полоса 4",
            "Полоса 5"};
    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);
    private final Lock lock3 = new ReentrantLock(true);
    private final Lock lock4 = new ReentrantLock(true);
    private final Lock lock5 = new ReentrantLock(true);

    private Airport() {}

    public static Airport getAirport() {
        if (airport == null) {
            airport = new Airport();
        }
        return airport;
    }

    public Lock getLock1() {
        return lock1;
    }

    public Lock getLock2() {
        return lock2;
    }

    public Lock getLock3() {
        return lock3;
    }

    public Lock getLock4() {
        return lock4;
    }

    public Lock getLock5() {
        return lock5;
    }

    public String getRunwayStripName(int id) {
        return runwayStripArray[id];
    }
}
