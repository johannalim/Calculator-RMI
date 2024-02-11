# Assignment 1 - Calculator RMI
a1878495 Johanna Seok Inn Lim
Distributed Systems - Semester 2 2023


## Description
A Java Remote Method Invocation (RMI) system that hosts a remote CalculatorFactory object on the server. The purpose is to provide each client with a remote Calculator object. 
A CalculatorFactory works to return new Calculator remote references for each Client instance, so each client has their own Calculator object and stack. This avoids cross-interference from conflicting methods called by multiple clients on the same stack. 

The CalculatorServer class hosts the remote server and binds the CalculatorFactory stub to the registry. 
The CalculatorClient class invokes the remote method of CalculatorFactory to create new instances of Calculator objects. The main provides an interactive interface for clients to perform operations of the Calculator stack. It can also be instantiated as an object to directly call the Calculator remote methods on it. 

The CalculatorTest class automates simple testing of single client and multiple clients' use of the RMI system. 

### Files
- CalculatorServer
- CalculatorClient
- CalculatorFactory (Interface) -> CalculatorFactoryImplementation
- Calculator (Interface) -> CalculatorImplementation
- CalculatorTest


## Installation
Download interface and class files.
Access to a Java Virtual Machine (JVM) is required. 
Clients must have access to interface files. (CalculatorClient -> Calculator, CalculatorFactory)


## Usage
(Made to run on the Linux image at University of Adelaide)
1. Compile via makefile: $ make
2. Run: $ java CalculatorServer
3. To create a new client - run: $ java CalculatorClient (repeat as needed)
4. Clients will display a menu of options, take input from terminal, and run continuously until user chooses to "exit"
5. When done, exit clients and terminate server run

Calculator methods available:
- pushValue(int val)
    - pushes input value to top of client's stack
- pushOperation(String operator)
    - pops all values from stack and performs 1 of 4 operations on them
    - takes String input to determine operation (min/max/lcm/gcd)
    - pushes value calculated by operation to stack
    - min = finds minimum value, max = finds maximum value, lcm = calculates least common multiple, gcd = calculates greatest common divisor
- pop()
    - pops top value from stack
    - returns popped int value
- isEmpty()
    - checks if stack is empty
    - returns boolean true/false
- delayPop(int millis)
    - delays before performing pop()
    - takes input to determine length of delay in milliseconds
    - returns popped int value

## Testing
(Testing was done via ssh remotely to uss.cs.adelaide.edu.au)
1. Run: java CalculatorServer
2. Run: java CalculatorTest

## License
[MIT](https://choosealicense.com/licenses/mit/)