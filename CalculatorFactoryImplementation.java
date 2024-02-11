import java.rmi.*;

public class CalculatorFactoryImplementation implements CalculatorFactory{
    public CalculatorFactoryImplementation() throws RemoteException {
        super();
    }
    // create new instance of CalculatorImplementation and return it for use
    public Calculator create_new_calculator() throws RemoteException{
        return new CalculatorImplementation();
    }
}
