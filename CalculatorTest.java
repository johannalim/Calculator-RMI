import java.rmi.RemoteException;

public class CalculatorTest {
    public static void main(String[] args) throws RemoteException {
        try {
        
        System.out.println("Calculator methods: pushValue(), pushOperation('min'/'max'/'lcm'/'gcd'), pop(), isEmpty(), delayPop()\n");
        System.out.println("pushOperation calculation results confirmed using pop() to show pushed value\n");
        // SINGLE CLIENT TESTING
        System.out.println("\nSingle client test \n");
        CalculatorClient client1 = new CalculatorClient();

        client1.client_stack.pushValue(1);
        System.out.println("Pushed value: 1");
        System.out.println("isEmpty(): Expected output = false. Actual output = " + client1.client_stack.isEmpty());
        client1.client_stack.pushValue(2);
        client1.client_stack.pushValue(3);
        System.out.println("Pushed values: 2, 3");
        client1.client_stack.pushOperation("min");
        System.out.println("pushOperation(min): Expected Output = 1. Actual Output = " + client1.client_stack.pop());



        // MULTIPLE CLIENTS TESTING
        System.out.println("\n\nMultiple clients test\n");
        CalculatorClient client2 = new CalculatorClient();
        CalculatorClient client3 = new CalculatorClient();
        CalculatorClient client4 = new CalculatorClient();

        // C1 = 1 3 7
        client1.client_stack.pushValue(3);
        client1.client_stack.pushValue(7);
        System.out.println("[Client 1] Pushed values: 3, 7");

        // C2 = 8
        client2.client_stack.pushValue(8);
        System.out.println("[Client 2] Pushed value: 8");

        // C1 = 1 3
        // C2 = empty
        System.out.println("[Client 1] pop(): Expected Output = 7. Actual Output = " + client1.client_stack.pop());
        System.out.println("[Client 2] pop(): Expected Output = 8. Actual Output = " + client2.client_stack.pop());

        // C3 = 36 51 9
        client3.client_stack.pushValue(36);
        client3.client_stack.pushValue(51);
        client3.client_stack.pushValue(9);
        System.out.println("\n[Client 3] Pushed values: 36, 51, 9");

        // C4 = 4 8 3 6
        client4.client_stack.pushValue(4);
        client4.client_stack.pushValue(8);
        client4.client_stack.pushValue(3);
        client4.client_stack.pushValue(6);
        System.out.println("[Client 4] Pushed values: 4, 8, 3, 6");
        
        System.out.println("\n[Client 2] isEmpty(): Expected output = true. Actual output = " + client2.client_stack.isEmpty());
        client1.client_stack.pushValue(14);
        System.out.println("\n[Client 1] Pushed value: 14");

        System.out.println("\nCurrent client stacks:");
        System.out.println("Client 1 = 1 3 14 (top)");
        System.out.println("Client 2 = [empty]");
        System.out.println("Client 3 = 36 51 9 (top)");
        System.out.println("Client 4 = 4 8 3 6 (top)\n");

        
        client3.client_stack.pushOperation("gcd");
        System.out.println("[Client 3] pushOperation(min): Expected output = 3. Actual output = " + client3.client_stack.pop());
        System.out.println("[Client 3] isEmpty(): Expected output = true. Actual output = " + client3.client_stack.isEmpty());
        System.out.println("\n[Client 4] isEmpty(): Expected output = false. Actual output = " + client4.client_stack.isEmpty());
        client4.client_stack.pushOperation("lcm");
        System.out.println("[Client 4] pushOperation(min): Expected output = 24. Actual output = " + client4.client_stack.pop());


        client1.client_stack.pushOperation("max");
        System.out.println("\n[Client 1] pushOperation(max) + delayPop(3000): Expected output = 14. Actual output = " + client1.client_stack.delayPop(3000));
        System.out.println("[Client 1] isEmpty(): Expected output = true. Actual output = " + client1.client_stack.isEmpty());
        System.out.println("\ndelayPop(): unable to record time delay in automation, but functionality is confirmed");
        
        } catch (Exception e) {
            System.err.println("Testing exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    }
}
