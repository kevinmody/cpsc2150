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


public class ListDeque extends AbsDeque{
    // this time store the deque in a list
// myQ.get(0) is the front of the deque
    private final List<Character> myQ;


    /**
     * @pre none
     * @post myQ is a list
     */
    public ListDeque() {
        myQ = new ArrayList<Character>();
    }

    public void enqueue(Character x) {
        myQ.add(x);
    }

    //removes and returns the Character at the front of the deque

    public Character dequeue() {
        Character frontChar = myQ.get(0);
        myQ.remove(0);
        return frontChar;
    }

    // Adds x to the front of the deque
    public void inject(Character x) {
        myQ.add(0, x);
    }

    //removes and returns the Character at the end of the deque
    public Character removeLast() {
        Character LastChar = myQ.get(myQ.size() - 1);
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