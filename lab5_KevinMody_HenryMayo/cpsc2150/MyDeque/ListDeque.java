package cpsc2150.MyDeque;

//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
///Date: 02/13/2021

import java.util.*;
/**
 * @Correspondence Deque size is myQ.size() and Deque = myQ
 * @invariants myQ.size() >=0 and myQ.size() <= MAX_LENGTH
 */


public class ListDeque <T> extends AbsDeque <T>{
    // this time store the deque in a list
// myQ.get(0) is the front of the deque
    private final List<T> myQ;


    /**
     * @pre none
     * @post myQ is a list
     */
    public ListDeque() {
        myQ = new ArrayList<T>();
    }

    public void enqueue(T x) {
        if(myQ.size() < MAX_LENGTH)
            myQ.add(x);
    }

    //removes and returns the Character at the front of the deque

    public T dequeue() {
        T frontChar = myQ.get(0);
        myQ.remove(0);
        return frontChar;
    }

    // Adds x to the front of the deque
    public void inject(T x) {
        myQ.add(0,  x);
    }

    //removes and returns the Character at the end of the deque
    public T removeLast() {
        T LastChar = myQ.get(myQ.size() - 1);
        myQ.remove(myQ.size() - 1);
        return LastChar;
    }

    //returns the number of Characters in the deque
    public int length() {
        return myQ.size();
    }

    public void clear() {
        for (int i = 0; i < myQ.size(); i++) {
            myQ.remove(i);
            i--;
        }
    }
}