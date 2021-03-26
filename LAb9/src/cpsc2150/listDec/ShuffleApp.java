package cpsc2150.listDec;
import java.util.ArrayList;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/22/2021
public class ShuffleApp {

    public static void main(String[] args)
    {
        int max_in_list = 25;
        int num_swaps = 50;
        int num_shuffles = 10;
        IShuffleList<Integer> sl = new ShuffleList<>(new ArrayList<Integer>());

        for(int i = 1; i <= max_in_list; i++)
        {
            sl.add(i);
        }

        System.out.println("Unshuffled List:");
        System.out.println(sl.toString());

        for(int i = 1; i <= num_shuffles; i++)
        {
            sl.shuffle(num_swaps);
            System.out.println("Shuffled " + i + " times:");
            System.out.println(sl.toString());
        }

    }
}
