package java_threads.optional_tasks;

import java.util.concurrent.locks.Lock;

public class Plane implements Runnable {
    private final int id;
    private boolean isTakenOff = false;
    private final Airport airport = Airport.getAirport();

    public Plane(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Самолет " + id + " готов к взлету.");
        while (!isTakenOff) {
            for (int i = 0; i < 5; i++) {
                Lock lock = airport.getLockArray(i);
                if (lock.tryLock()) {
                    executeTakeoff(lock, airport.getRunwayStripName(i));
                    return;
                }

            }
            if (!isTakenOff) {
                waiting();
            }
        }
    }

    public void executeTakeoff(Lock lock, String runwayName) {
        try {
            System.out.println(runwayName + " приняла самолет " + id + ".");
            System.out.println(runwayName + ": самолет " + id + " начал выход на полосу.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=v= " + runwayName + ": самолет " + id + " взлетел. =v=");
            System.out.println(runwayName + " освободилась.");
            isTakenOff = true;
        } finally {
            lock.unlock();
        }
    }

    public void waiting() {
        System.out.println("Самолет " + id + " ждет разрешения для выхода на свободную полосу.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
