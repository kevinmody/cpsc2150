package cpsc2150.MyDeque;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/13/2021

/**
 * @Correspondence Deque size = myLength and Deque = myQ
 * @invariants myLength >=0 and myLength <= MAX_LENGTH
 */


public class ArrayDeque extends AbsDeque{
    // where the data is stored. myQ[0] is the front of the deque
    private final Character[] myQ;
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
        myQ = new Character[MAX_LENGTH];
    }

    // Adds x to the end of the deque
    public void enqueue(Character x) {
        myQ[myLength] = x;
        myLength++;
    }




    //removes and returns the Character at the front of the deque
    public Character dequeue() {
        // Create another array of size one less
        myLength--;
        Character temp = myQ[0];

        for(int i = 0; i < myLength; i++){
            myQ[i]= myQ[i+1];
        }


        return temp;
    }


    // Adds x to the front of the deque
    public void inject(Character x) {

        for(int i = myLength-1; i >= 0; i--){
            myQ[i+1]= myQ[i];
        }
        myQ[0]=x;
        myLength = myLength + 1;
    }
    //removes and returns the Character at the end of the deque
    public Character removeLast() {

        Character temp = myQ[myLength-1];

        myQ[myLength-1] = null;
        myLength = myLength - 1;
        return temp;
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
