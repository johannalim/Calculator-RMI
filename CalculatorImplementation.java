// the implementation class for the remote operations
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    protected CalculatorImplementation() throws RemoteException {
        super();
    }

    // stack to be operated on
    public Stack<Integer> stack = new Stack<Integer>();

    // input: integer value
    // push (add) input value to top of stack
    // output: no return value, stack has new value
    public void pushValue(int val) {
        stack.push(val);
    }

    // input: operator name (String)
    // pops all values from stack and performs 1 of 4 available operations on them depending on String input
    // min - find and push the min value
    // max - find and push the max value
    // lcm - calculate and push the least common multiple
    // gcd - calculate and push the greatest common divisor
    // output: no return value, stack has new value
    public void pushOperation(String operator) {
        switch(operator) {
            case "min": 
                int min = stack.pop();
                while (stack.empty() != true) {
                    int num = stack.pop();
                    if (num < min) { min = num; }
                }
                stack.push(min);
                break;
            case "max":
                int max = stack.pop();
                while (stack.empty() != true) {
                    int num = stack.pop();
                    if (num > max) { max = num; }
                }
                stack.push(max);
                break;
            case "lcm":{
                int lcm = stack.pop();
                while (stack.empty() != true) {
                    int num1 = lcm;
                    int next = stack.pop();
                    int num2 = next;
                    while (num2 != 0) {
                        int hold = num1;
                        num1 = num2;
                        num2 = hold%num2;
                    }
                    lcm = (lcm * next) / num1;
                }
                stack.push(lcm);
                break;}
            case "gcd":
                int gcd = 0;
                while (stack.empty() != true) {
                    int num1 = gcd;
                    int num2 = stack.pop();
                    while (num1 > 0 && num2 > 0) {
                        if (num1 > num2) {num1 = num1%num2;}
                        else {num2 = num2%num1;}
                    }
                    if (num1 == 0) {gcd = num2;}
                    else {gcd = num1;}
                }
                stack.push(gcd);
                break;
        }
    }

    // no input
    // pops last value from stack
    // output: returns popped value
    public int pop() {
        return stack.pop();
    }

    // no input
    // output: return true if the stack is empty, false otherwise
    public boolean isEmpty() {
        return stack.empty();
    }

    // takes integer value as input to determine delay length
    // wait millis milliseconds before carrying out the pop operation
    // output: returns the pop value
    public int delayPop(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return pop();
    }
}