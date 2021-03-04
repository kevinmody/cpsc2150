package cpsc2150.MyDeque;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/04/2021

public abstract class AbsDeque<T> implements IDeque <T> {
    @Override
    public String toString() {
        //System.out.println("Deque is : ");
        StringBuilder deque = new StringBuilder("<");
        int length = length();
        for (int i = 1; i < length + 1; i++) { //shifting by 1 nad starting at index at 1
            if (i == length()) {
                deque.append(get(i));
            } else {
                deque.append(get(i)).append(", ");

            }

        }
        deque.append(">");
        return deque.toString();

    }
}
