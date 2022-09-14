package RequestHandlers;

import queues.QueueSend;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestHandler {

    public void start() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);//creating a pool of 5 threads
        for (int i = 0; i < 10; i++) {
            Runnable sender = new QueueSend("Sender " + i);
            executor.execute(sender);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }
    }
}
