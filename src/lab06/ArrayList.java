/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 * Implements generic List interface using a generic array structure.
 * @author ryan.quinn.nelson and michael.kleinsasser
 * @param <E> generic type to be implemented
 */

public class ArrayList<E> implements List<E>
{
    
    private E[] data;
    private int size = 0;
    
    /**
     * Constructs ArrayList object with given fixed capacity.
     * @param capacity maximum number of elements in ArrayList
     */
    public ArrayList(int capacity)
    {
        data = (E[]) new Object[capacity];      //narrowing cast
    }
    
    //Public accessor method
    /**
     * Returns number of items in list.
     * @return number of items in list
     */
    @Override
    public int size() 
    {
        return size;
    }

    /**
     * Tests whether list contains any items.
     * @return true if list contains items, false otherwise
     */
    @Override
    public boolean isEmpty() 
    {
        return size == 0;
    }

    /**
     * Returns but does not remove the element at given index.
     * @param index index which to return element from
     * @return element 
     * @throws IndexOutOfBoundsException if index position is not in array
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException 
    {
        checkIndex(index, size);
        
        return data[index];   
    }

    
    
    
    
    //Public update methods
    /**
     * Replaces the element at given index with given element, returns the element replaced.
     * @param index given index to replace element at
     * @param e element to add to list
     * @return element replaced
     * @throws IndexOutOfBoundsException if index position is not in array
     */
    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException 
    {
        checkIndex(index, size+1);  //+1 prevents element from being added in a way that leaves a null element between existing and new element
        
        E old = data[index];
        data[index] = e;
        
        return old;
    }

    
    /**
     * Inserts given element at given index, shifting all subsequent elements later.
     * @param index index at which to add element
     * @param e element to add to list
     * @throws IndexOutOfBoundsException if given index is not in array
     * @throws IllegalStateException if array is full
     */
    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException, IllegalStateException
    {
        checkIndex(index, size+1);
        
        if(size == data.length) //array is full and last element can't be shifted to the right
        {
            throw new IllegalStateException("Array is full and can't be shifted.");
        }
        
        //shifts all array elements from index to end one to the right
        for(int j = size-1; j >= index; j--)
        {
            data[j+1] = data[j];
        }
        
        data[index] = e;
        size++;  
    }
    
    /**
     * Inserts given element at end of list.
     * @param e element to add to list
     */
    public void add(E e)    
    {
        if(size == data.length) //array is full and last element can't be shifted to the right
        {
            throw new IllegalStateException("Array is full and can't be shifted.");
        }
        
        data[size] = e;
        size++;
    }

    
    /**
     * Removes and returns the element at given index, shifting all subsequent elements earlier.
     * @param index index to remove element from
     * @return element that was removed
     * @throws IndexOutOfBoundsException if given index is not in array 
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException 
    {
        checkIndex(index, size);
        
        E removed = data[index];
        
        for(int j = index; j < size-1; j++)
        {
            data[j] = data[j+1];
        }
        data[size-1] = null;        //assist garbage collection
        size--;
        
        return removed;
    }
    
    //protected utilities
    /**
     * Checks whether given index is in the range [0, n-1].
     * @param index     index to check
     * @param size  number of elements in the array
     * @throws IndexOutOfBoundsException if index is outside the range
     */
    protected void checkIndex(int index, int size) throws IndexOutOfBoundsException
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Illegal Index " + index);
        }
    }
    
    
    //additional methods
    /**
     * Returns a String representation of ArrayList object.
     * @return a String representation of ArrayList object
     */
    @Override
    public String toString()
    {
        if(isEmpty())
        {
            return "Empty list.";
        }
        
        String answer = "";
        
        for(int i = 0; i < size; i++)
        {
            answer += data[i];
            
            if(i < size-1)
            {
                answer +=", ";
            }
        }
        return answer;
    }
    
    /**
     * Tests whether this is equal to given Object.
     * @param o object to compare with this
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof ArrayList))
        {
            return false;
        }
        else
        {
            ArrayList<E> other = (ArrayList<E>) o;
            
            if(this.size() != other.size())
            {
                return false;
            }
            
            for(int i = 0; i < this.size(); i++)
            {
                if(!this.data[i].equals(other.data[i]))
                {
                    return false;
                }
            }
            return true;
        }
    }
    
}
