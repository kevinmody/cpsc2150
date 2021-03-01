package cpsc2150.MyDeque;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/13/2021

/**
 * @Correspondence Deque size = myLength and Deque = myQ
 * @invariants myLength >=0 and myLength <= MAX_LENGTH
 */

import java.util.*;

 public class ArrayDeque<T> extends AbsDeque <T>{
    // where the data is stored. myQ[0] is the front of the deque
    private T[] myQ;
    // tracks how many items in the deque
    // also used to find the end of the deque
    private int myLength;
    // complete the class

    /**
     * @pre none
     * @post myLength = 0 myQ is a character array of MAX_LENGTH
     */
    public ArrayDeque(){
        myLength=0;
        myQ = (T[]) (new Object[IDeque.MAX_LENGTH]);
    }

    // Adds x to the end of the deque
    public void enqueue(T x) {
        myQ[myLength] =  x;
        myLength++;
    }

    //removes and returns the Character at the front of the deque
    public T dequeue() {
        // Create another array of size one less
        myLength--;
        T temp = myQ[0];

        for(int i = 0; i < myLength; i++){
            myQ[i]= myQ[i+1];
        }


        return temp;
    }


    // Adds x to the front of the deque
    public void inject(T x) {

        for(int i = myLength-1; i >= 0; i--){
            myQ[i+1]= myQ[i];
        }
        myQ[0]= (T) x;
        myLength = myLength + 1;
    }
    //removes and returns the Character at the end of the deque
    public T removeLast() {

        T temp = myQ[myLength-1];

        myQ[myLength-1] = null;
        myLength = myLength - 1;
        return (T) temp;
    }
    //returns the number of Characters in the deque
    public int length(){
        return myLength;
    }
    public void clear() {
        for (int i = 0; i < myLength; i++) {
            myQ[i]= null;

        }
        myLength=0;
    }
}
