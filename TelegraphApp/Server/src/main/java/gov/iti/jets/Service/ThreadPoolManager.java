package gov.iti.jets.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
    private static final int THREAD_COUNT = 10;
    private final ExecutorService executorService;

    public ThreadPoolManager() {
        this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }
    public void submitOperation(Runnable operation){
        executorService.execute(operation);
    }
    public void shutdown(){
        executorService.shutdown();
    }
}
