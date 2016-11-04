/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 * Simplified version of java.util.List interface
 * @author ryan.quinn.nelson and michael.kleinsasser
 * @param <E> generic type to be implemented
 */ 
public interface List<E>
{
    /**
     * Returns number of elements in this list
     * @return int representing number of elements in this list
     */
    public abstract int size();
    
    
    /**
     * Returns whether the list is empty.
     * @return true if list is empty, false otherwise
     */
    public abstract boolean isEmpty();
    
    
    /**
     * Returns but does not remove the element at index i.
     * @param i index element is stored at
     * @return element at index i
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public abstract E get(int i) throws IndexOutOfBoundsException;
    
    /**
     * Replaces element at index i with e, returns the replaced element.
     * @param i index to place element at
     * @param e element to be added to list
     * @return replaced element
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public abstract E set(int i, E e) throws IndexOutOfBoundsException;
    
    
    /**
     * Shifts elements starting at i to the right by one, and adds element at i.
     * @param i index to place element
     * @param e element to be added to list
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public abstract void add(int i, E e) throws IndexOutOfBoundsException;      
    
    /**
     * Remove and return element at index i, and shift elements start at i+1 to the left by one.
     * @param i index to remove element from
     * @return element removed
     * @throws IndexOutOfBoundsException 
     */
    public abstract E remove(int i ) throws IndexOutOfBoundsException;
}
