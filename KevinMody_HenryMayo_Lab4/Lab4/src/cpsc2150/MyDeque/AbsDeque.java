package cpsc2150.MyDeque;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/13/2021
public abstract class AbsDeque implements IDeque{
    @Override
    public String toString()
    {
        //System.out.println("Deque is : ");
        String deque="<";
        int length = length();
        for(int i=1;i<length+1;i++){ //shifting by 1 nad starting at index at 1
            if(i==length()){
                deque+=get(i);
            }
            else{
                deque+=(get(i)+", ");

            }

        }
        deque+=">\n";
        return  deque;

    }
}
