import java.rmi.Naming;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) {
        try {
            // Lookup the remote CalculatorService
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nEnter operation: +, -, *, / (or 'exit' to quit)");
                String operation = scanner.next();

                if (operation.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                System.out.print("Enter first number: ");
                int a = scanner.nextInt();
                System.out.print("Enter second number: ");
                int b = scanner.nextInt();

                int result = 0;
                double resultDiv = 0;

                switch (operation) {
                    case "+":
                        result = calculator.add(a, b);
                        System.out.println("Result: " + result);
                        break;
                    case "-":
                        result = calculator.subtract(a, b);
                        System.out.println("Result: " + result);
                        break;
                    case "*":
                        result = calculator.multiply(a, b);
                        System.out.println("Result: " + result);
                        break;
                    case "/":
                        if (b != 0) {
                            resultDiv = calculator.divide(a, b);
                            System.out.println("Result: " + resultDiv);
                        } else {
                            System.out.println("Error: Cannot divide by zero.");
                        }
                        break;
                    default:
                        System.out.println("Invalid operation!");
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
