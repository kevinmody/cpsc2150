package cpsc2150.sets;
import java.util.*;

public class ArraySet<T> extends SetAbs<T> implements ISet<T> {
    private T[] set;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArraySet(){
        set = (T[]) new Object[MAX_SIZE];
        depth = 0;
    }

    public void add(T val){
        set[depth] = val;
        depth++;
    }

    public T remove(){
        T removeVal = set[0];
        for(int i = 0; i < depth - 1; i++){
            set[i] = set[i+1];
        }
        depth--;
        return removeVal;
    }

    public boolean contains(T val){
        for(int i = 0; i < depth; i++){
            if(set[i].equals(val)){
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return depth;
    }

}
