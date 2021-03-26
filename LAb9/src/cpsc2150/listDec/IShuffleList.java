package cpsc2150.listDec;
import java.util.List;
import java.util.Random;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/22/2021
public interface IShuffleList<T> extends List<T>{
    /**
     * @pre this.size() >= 2
     * @post this.get(r1) = #this.get(r2) and this.get(r2) = #this.get(r1)
     * @param swaps is number of times two random positions are swapped
     */
    default void shuffle(int swaps){
        for(int i = 0; i<swaps; i++){
            Random rand = new Random();
            int r1 = rand.nextInt(this.size());
            int r2 = rand.nextInt(this.size());
            this.swap(r1, r2);
        }
    }

    /**
     * @pre this.size() >= 2
     * @post this.get(i) = #this.get(r2) and this.get(j) = #this.get(r1)
     * @param i is one of the positions whose value is swapped
     * @param j is one of the positions whose value is swapped
     */
    default void swap(int i, int j){
        T a = this.get(i);
        T b = this.get(j);
        this.set(i, b);
        this.set(j, a);
    }
}
