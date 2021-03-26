package cpsc2150.listDec;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Collection;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/22/2021
public class ShuffleList<T> implements IShuffleList<T> {
    List<T> myList;

    public ShuffleList(List<T> l)
    {
        myList = l;
    }

    public boolean add(Object e){
        return myList.add((T)(e));
    }

    public T get(int i)
    {
        return myList.get(i);

    }

    public void add(int index, Object e){
        myList.add(index, (T)(e));
    }

    public int size()
    {
        return myList.size();
    }

    public void clear()
    {
        myList.clear();
    }

    public T set(int i, Object x)
    {
       return myList.set(i, (T)(x));
    }

    public List<T> subList(int i, int j)
    {
        return myList.subList(i, j);
    }

    public ListIterator<T>  listIterator()
    {
        return myList.listIterator();
    }

    public ListIterator<T> listIterator(int i)
    {
        return myList.listIterator(i);
    }

    public int lastIndexOf(Object o)
    {
        return myList.lastIndexOf(o);
    }

    public int indexOf(Object o)
    {
        return myList.indexOf(o);
    }

    @Override
    public int hashCode()
    {
        return myList.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        return myList.equals(o);
    }

    @Override
    public String toString()
    {
        return myList.toString();
    }

    public boolean addAll(Collection<? extends T> c)
    {
        return myList.addAll(c);
    }

    public boolean removeAll(Collection<?> c)
    {
        return myList.removeAll(c);
    }

    public boolean retainAll(Collection<?> c)
    {
        return myList.retainAll(c);
    }

    public boolean addAll(int i, Collection<? extends T> c)
    {
        return myList.addAll(i, c);
    }

    public boolean containsAll(Collection<?> c)
    {
        return myList.containsAll(c);
    }

    public T remove(int index)
    {
        return myList.remove(index);
    }

    public boolean remove(Object o)
    {
        return myList.remove(o);
    }

    public boolean isEmpty()
    {
        return myList.isEmpty();
    }

    public boolean contains(Object obj){
        return myList.contains(obj);
    }

    public Iterator<T> iterator(){
        return myList.iterator();
    }

    public Object[] toArray(){
        return myList.toArray();
    }


    public Object[] toArray(Object[] ts){
        return myList.toArray(ts);
    }
}
