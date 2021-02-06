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
            if (airport.getLock1().tryLock()) {
                executeTakeoff(airport.getLock1(), airport.getRunwayStripName(0));
            }
            else if (airport.getLock2().tryLock()) {
                executeTakeoff(airport.getLock2(), airport.getRunwayStripName(1));
            }
            else if (airport.getLock3().tryLock()) {
                executeTakeoff(airport.getLock3(), airport.getRunwayStripName(2));
            }
            else if (airport.getLock4().tryLock()) {
                executeTakeoff(airport.getLock4(), airport.getRunwayStripName(3));
            }
            else if (airport.getLock5().tryLock()) {
                executeTakeoff(airport.getLock5(), airport.getRunwayStripName(4));
            } else {
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
