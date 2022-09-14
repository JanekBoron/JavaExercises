import java.util.concurrent.ThreadPoolExecutor;
import
import RequestHandlers.RequestHandler;
import RequestHandlers.RequestService;

public class Start {
    public static void main(String[]args) throws InterruptedException {
        RequestHandler threadPoolRequestor = new RequestHandler();
        RequestService threadPoolService = new RequestService();
        threadPoolRequestor.start();
        threadPoolService.start();
    }
}
