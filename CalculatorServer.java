// server class
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class CalculatorServer extends CalculatorFactoryImplementation {

    // explicit constructor
    public CalculatorServer() throws RemoteException {}
    
    // stores registry after creation to prevent being garbage collected while server is running
    private static Registry registry;
    
    public static void main(String args[]) {
        try {

            // Instantiate and export remote object
            CalculatorFactory obj = new CalculatorFactoryImplementation();
            CalculatorFactory stub = (CalculatorFactory) UnicastRemoteObject.exportObject(obj, 0);
    
            // Bind the remote object's stub in the registry
            registry = LocateRegistry.createRegistry(9090);
            registry.bind("calculatorfactory", stub);
    
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
