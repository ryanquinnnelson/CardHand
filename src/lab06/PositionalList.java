/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 * A collection of positions, each of which stores an element.
 * @author ryan.quinn.nelson and michael.kleinsasser
 * @param <E> generic type to be implemented
 */
public interface PositionalList<E>
{
    /**
     * Returns the number of elements in list.
     * @return the number of elements in list
     */
    int size();
    
    /**
     * Tests whether list contains any elements.
     * @return true if list contains elements, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns the position of the first element in list.
     * @return the position of the first element in list (null if list is empty)
     */
    Position<E> first();
    
    /**
     * Returns the position of the last element in list.
     * @return the position of the last element in list (null if list is empty)
     */
    Position<E> last();
    
    /**
     * Returns the position of the element before given position.
     * @param p given position in the list
     * @return the position of the element immediately before given position (null if given is first position)
     * @throws IllegalArgumentException if given position is not valid
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;
    
    /**
     * Returns the position of the element after given position.
     * @param p given position in the list
     * @return the position of the element immediately after given position (null if given is first position)
     * @throws IllegalArgumentException if given position is not valid 
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;
    
    /**
     * Inserts a new element at the front of the list and returns the position of new element.
     * @param e element to be inserted in the list
     * @return position of the new element
     */
    Position<E> addFirst(E e);
    
    /**
     * Inserts a new element at the end of the list and returns the position of new element.
     * @param e element to be inserted in the list
     * @return position of the new element
     */
    Position<E> addLast(E e);
    
    /**
     * Inserts a new element immediately before given position and returns the position of the new element.
     * @param p given position
     * @param e element to be inserted in the list
     * @return position of the new element
     * @throws IllegalArgumentException if given position is not valid
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;
    
    /**
     * Inserts a new element immediately after given position and returns the position of the new element.
     * @param p given position
     * @param e element to be inserted in the list
     * @return position of the new element
     * @throws IllegalArgumentException if given position is not valid 
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;
    
    /**
     * Replaces element at given position with given element and returns the element that was replaced.
     * @param p given position
     * @param e element to be inserted in the list
     * @return element that was replaced
     * @throws IllegalArgumentException if given position is not valid 
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;
    
    /**
     * Removes and returns the element at given position, invalidating the position.
     * @param p given position
     * @return element that was removed from the list
     * @throws IllegalArgumentException if given position is not valid 
     */
    E remove(Position<E> p) throws IllegalArgumentException;
}
