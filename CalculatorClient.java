// a test client that should connect to the server, and test its operation
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    // constructor and non-static field for testing purposes, to be able to operate multiple Clients in Testing main
    // automatically creates Calculator(Implementation) instance and stores it in non-static field
    public Calculator client_stack;
    public CalculatorClient() throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(9090); 
       
        CalculatorFactory stub = (CalculatorFactory) registry.lookup("calculatorfactory"); 
        client_stack = (Calculator) stub.create_new_calculator();
    }

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);

        try {  
            // Get registry and lookup remote object
            Registry registry = LocateRegistry.getRegistry(9090); 
            CalculatorFactory stub = (CalculatorFactory) registry.lookup("calculatorfactory"); 
            // Invoke CalculatorFactory method to create new Calculator(Implementation) object instance for client
            Calculator stack = (Calculator) stub.create_new_calculator();

            int action = 0;

            do {
                System.out.println("Menu");
                System.out.println("1. push value \n2. push operation \n3. pop value \n4. Check if stack is empty \n5. Pop value with a delay\n6. Exit Client");
                System.out.print("Select action by number: ");
                action = in.nextInt();

                // Call remote method on retrieved object depending on action chosen
                // Take input from user and print values as needed
                // Print to notify users of action completion
                switch (action) {
                    case 1 : {
                        System.out.print("Enter value: ");
                        int value = in.nextInt();
                        stack.pushValue(value);
                        System.out.println(value + "push complete!");
                        break;
                    }
                    case 2: {
                        System.out.print("Enter operation (min/max/lcm/gcd): ");
                        String operation = in.next();
                        stack.pushOperation(operation);
                        System.out.println(operation + "operation complete!");
                        break;
                    }
                    case 3: {
                        int popval = stack.pop();
                        System.out.println("Popped value: " + popval);
                        break;
                    }
                    case 4: {
                        boolean emptystack = stack.isEmpty();
                        System.out.println("Stack is empty: " + emptystack);
                        break;
                    }
                    case 5: {
                        System.out.print("Delay time in milliseconds: ");
                        int msdelay = in.nextInt();
                        System.out.println("Delaying " + msdelay + " milliseconds . . . ");
                        int delaypopval = stack.delayPop(msdelay);
                        System.out.println("Popped value: " + delaypopval);
                        break;
                    }
                }
                System.out.println();
            } while (action != 6);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); 
            e.printStackTrace(); 
        } 

        in.close();
    }
}
