import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servere {
    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);
            
            // Create and bind the remote object
            CalculatorImpl calculator = new CalculatorImpl();
            Naming.rebind("CalculatorService", calculator);

            System.out.println("Calculator Server is running...");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
