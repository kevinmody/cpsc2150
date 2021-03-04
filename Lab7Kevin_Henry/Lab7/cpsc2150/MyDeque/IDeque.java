//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/04/2021
package cpsc2150.MyDeque;

public interface IDeque <T>{
    public static final int MAX_LENGTH = 100;
    // Adds x to the end of the deque
    /**
        @pre Deque size < MAX_LENGTH
        @post Deque size = Dequeue size# + 1
        @param x is a character to be added to the end of the deque
     */
    public void enqueue(T x);
    //removes and returns the Character at the front of the deque

    /**
     * @pre 0 < Deque size <= MAX_LENGTH
     * @post Deque size = Dequeue size# - 1
     * @return variable at the front of Deque
     */
    public T dequeue();
    // Adds x to the front of the deque

    /**
     * @pre 0 <= Deque size < MAX_LENGTH
     * @post Deque size = Deque size# + 1
     * @param x  is a character to be added to the front of the deque
     */
    public void inject(T x);
    //removes and returns the Character at the end of the deque

    /**
     * @pre 0 < deque size <= MAX_LENGTH
     * @post deque size = deque size# - 1
     * @return  variable at the end of deque
     */
    public T removeLast();
    //returns the number of Characters in the deque

    /**
     * @return deque size
     */
    public int length();
    //clears the entire deque

    /**
     * @pre none
     * @post deque size  = 0
     */
    public void clear();



    /**
     * @pre deque size > 0
     * @post returns the character that is placed at the beginning of the deque
     *       deque = #deque
     * @return character
     *
     */
    default T peek(){
        if(length()==0){
            System.out.println("Deque is empty!");
            return null;
        }
        else {
           T letter = dequeue();
            inject(letter);
            return letter;
        }
    }


    /**
     * @pre deque size > 0
     *  @post returns the character that is at the end of the deque
     *        deque = #deque
     * @return character
     *
     */
    default T endOfDeque() {
        if (length() == 0) {
            return null;
        } else {
            T letter = removeLast();
            enqueue(letter);
            return letter;
        }
    }


    /**
     * @pre 1 <= pos <= deque size+1
     * @param c - the character that is being placed in position pos
     * @param pos - the position the character is being added to
     * @post deque = character c added at position pos in #deque
     */
    default void insert(T c, int pos){
        pos-=1;
        if (pos==0)
            inject(c);
        else{
            T[] storage =  (T[]) new Object[pos];
            for (int i=pos; i> 0; i--){
                storage[i-1]= dequeue();
            }
            inject(c);
            for (T character : storage) {
                inject(character);
            }
        }
    }

    /**
     * @pre 1 <= pos <= deque size
     * @return character
     * @param pos - the position of the character
     * @post returns the character at pos
     *       deque = character at position pos removed from #deque
     */
    default T remove (int pos){
        pos-=1;
        T[] storage = (T[])  new Object[pos];
        for (int i=pos; i> 0; i--){
            storage[i-1]= dequeue();
        }
        T letter= dequeue();
        for (T character : storage) {
            inject(character);
        }
        return letter;
    }


    /**
     * @pre 1 <= pos <= deque size and Deque size > 0
     * @return character
     * @param pos - the character at position pos
     * @post returns the character at position pos
     *       deque = #deque
     */
    default T get(int pos) {
        pos = pos -1 ;
        T dummy;
        T storage = null;
        int length = length();
        for (int i = 0; i < length; i++) {
            dummy = dequeue();
            enqueue(dummy);
            if (pos == i) {
                storage = dummy;
            }

        }
        return storage;
    }



}
