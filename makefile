
JFLAGS = -g
JC =javac
JVM= java 
.SUFFIXES:.java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSPATH = .:./src

vpath %.java src

CLASSES = \
	Calculator.java \
	CalculatorImplementation.java \
	CalculatorFactory.java \
	CalculatorFactoryImplementation.java \
	CalculatorServer.java \
	CalculatorClient.java \
	CalculatorTest.java

default:classes

classes:$(CLASSES:.java=.class)

clean:\
	$(RM) *.class