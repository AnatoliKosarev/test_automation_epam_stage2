package java_threads.optional_tasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AirportRunner {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 11; i++) {
            executorService.submit(new Plane(i));
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
