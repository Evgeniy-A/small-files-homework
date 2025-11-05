import java.io.IOException;
import service.BillingService;

public class Main {
    public static void main(String[] args) throws IOException {
        BillingService service = new BillingService();
        service.processBilling();
    }
}