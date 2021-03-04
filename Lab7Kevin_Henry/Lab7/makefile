default: cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque
	javac -Xlint cpsc2150/MyDeque/ArrayDeque.java cpsc2150/MyDeque/DequeApp.java cpsc2150/MyDeque/ListDeque.java cpsc2150/MyDeque/IDeque.java cpsc2150/MyDeque/AbsDeque.java cpsc2150/MyDeque/IntegerDequeApp.java

runChar: cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque
	java cpsc2150.MyDeque.DequeApp

runInt: cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque
	java cpsc2150.MyDeque.IntegerDequeApp

test: cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque
	javac -Xlint -cp .:/usr/share/java/junit4.jar cpsc2150/MyDeque/TestArrayDeque.java cpsc2150/MyDeque/TestListDeque.java cpsc2150/MyDeque/ArrayDeque.java cpsc2150/MyDeque/DequeApp.java cpsc2150/MyDeque/ListDeque.java cpsc2150/MyDeque/IDeque.java cpsc2150/MyDeque/AbsDeque.java cpsc2150/MyDeque/IntegerDequeApp.java

testArr: cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.MyDeque.TestArrayDeque

testList: cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque cpsc2150/MyDeque
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.MyDeque.TestListDeque

clean:
	rm -f cpsc2150/MyDeque/*.class
