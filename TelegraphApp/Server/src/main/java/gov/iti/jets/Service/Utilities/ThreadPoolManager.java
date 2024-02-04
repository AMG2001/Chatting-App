package gov.iti.jets.Service.Utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private static final int THREAD_COUNT = 10;
    private static ThreadPoolManager instance;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private ThreadPoolManager() {
        //this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public static void submitOperation(Runnable operation) {
        executorService.execute(operation);
    }

    public static void shutdown() {
        executorService.shutdown();
    }
}
