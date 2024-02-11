import java.rmi.Remote;
import java.rmi.RemoteException;

// CalculatorFactory interface
public interface CalculatorFactory extends Remote {
    Calculator create_new_calculator() throws RemoteException;
}