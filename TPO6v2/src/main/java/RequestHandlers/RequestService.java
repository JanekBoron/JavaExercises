package RequestHandlers;

import queues.QueueReceive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestService {
    public void start() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);//creating a pool of 2 threads
        for (int i = 0; i < 5; i++) {
            Runnable receiver = new QueueReceive("Receiver " + i);
            executor.execute(receiver);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }
    }
}
