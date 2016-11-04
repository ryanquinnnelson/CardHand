/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements PositionalList interface using a doubly linked list structure.
 * @author ryan.quinn.nelson and michael.kleinsasser
 * @param <E> generic type to be implemented
 */
public class LinkedPositionalList<E> implements PositionalList<E> , Iterable<E>
{

    // -------------- nested Node class -------------- //
    /**
     * Class implements Position interface using a Node structure.
     * @param <E> generic type to be implemented
     */
    private static class Node<E> implements Position<E>
    {
        private E       element;    //reference to element stored at this node
        private Node<E> prev;       //reference to previous node in the list
        private Node<E> next;       //reference to next node in the list
        
        /**
         * Constructs Node object.
         * @param e element to be stored at this node
         * @param prev previous node in the list
         * @param next next node in the list
         */
        public Node(E e, Node<E> prev, Node<E> next)
        {
            element     = e;
            this.prev   = prev;
            this.next   = next;
        }

        /**
         * Returns the previous node before this.
         * @return previous node before this
         */
        public Node<E> getPrev() {
            return prev;
        }
        
        /**
         * Returns the next node after this.
         * @return the next node after this
         */
        public Node<E> getNext() {
            return next;
        }
        
        /**
         * Returns the element at this node.
         * @return the element at this node
         * @throws IllegalStateException if this.next is null, indicating position is invalid
         */
        @Override
        public E getElement() throws IllegalStateException
        {
            if(next == null)
            {
                throw new IllegalStateException("position no longer valid");
            }
            
            return element;
        }
        
        /**
         * Sets the previous node before this.
         * @param prev previous node before this
         */
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        /**
         * Sets the next node after this.
         * @param next the next node after this
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }
        

        /**
         * Sets the element of this node.
         * @param e element to store at this node
         */
        public void setElement(E e)
        {
            element = e;
        }
        
        /**
         * Returns a String representation of Node object.
         * @return String representation of Node object
         */
        @Override
        public String toString()
        {
            return element.toString();
        }
        
        /**
         * Tests whether this is equal to given object.
         * @param o object to compare with this
         * @return true if objects are equal, false otherwise
         */
        @Override
        public boolean equals(Object o)
        {
           if(!(o instanceof Node))
           {
               return false;
           }
           else
           {
               Node<E> other = (Node<E>) o;
               
               boolean b1 = this.element.equals(other.element);
               boolean b2 = this.next == other.next;        //point to same object
               boolean b3 = this.prev == other.prev;
               
               return b1 && b2 && b3;
           }
        }
        
    } // ------------ end of nested Node class ---------------- //
    

    // -------------- nested PositionIterator class -------------//
    /**
     * Implements Iterator interface for generic Position type.
     */
    private class PositionIterator implements Iterator<Position<E>>
    {
        private Position<E> cursor = first();
        private Position<E> recent = null;

        /**
         * Tests whether iterator has another element.
         * @return true if another element exists, false otherwise
         */
        @Override
        public boolean hasNext() 
        {
            return (cursor != null);
        }

        /**
         * Returns next Position in iterator.
         * @return next Position in iterator
         * @throws NoSuchElementException if iterator has no remaining elements
         */
        @Override
        public Position<E> next() throws NoSuchElementException
        {
            if(cursor == null) throw new NoSuchElementException("nothing left");
            
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }
    }  // -------------- end of nested PositionIterator class -------------//  
    
    
 
    //----------------- nested PositionIterable class --------------------//
    /**
     * Implements Iterable interface for generic Position type.
     */
    private class PositionIterable implements Iterable<Position<E>>
    {
        /**
         * Returns instance of generic Position iterator.
         * @return generic Position iterator
         */
        @Override
        public Iterator<Position<E>> iterator() 
        {
            return new PositionIterator();
        }  
        
        
    } //----------------- end of nested PositionIterable class --------------------//
    
    
   
    //-------------------- nested ElementIterator class -----------------------//
    /**
     * Implements generic Iterator interface using adapter pattern from PositionIterator.
     */
    private class ElementIterator implements Iterator<E>
    {
        private Iterator<Position<E>> positionIterator = new PositionIterator();

        /**
         * Tests whether iterator has another element.
         * @return true if another element exists, false otherwise
         */
        @Override
        public boolean hasNext() 
        {
            return positionIterator.hasNext();
        }

        /**
         * Returns next element in iterator.
         * @return next element in iterator
         * 
         */
        @Override
        public E next() 
        {
            return positionIterator.next().getElement();
        }
    }//-------------------- end of nested ElementIterator class -----------------------//
    
    
   
    private Node<E> header;     //sentinel node at the front of the list
    private Node<E> trailer;    //sentinel node at the end of the list
    private int size = 0;       //number of elements in the list
    
    /**
     * Constructs a LinkedPositionalList object with header and trailer nodes.
     */
    public LinkedPositionalList()
    {
        header  = new Node<>(null, null, null);      //creates header
        trailer = new Node<>(null, header, null);   //creates trailer
        header.setNext(trailer);                    //header is followed by trailer
    }
    
    
    
    
    
    
    
    
    //Private Utilities
    /**
     * Checks whether given position is a valid position and returns it as a node.
     * @param p given position
     * @return Node object representing the position
     * @throws IllegalArgumentException if position is invalid
     * @throws IllegalArgumentExceptioni if position is no longer in list
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid position.");   //position is not a Node object
        
        Node<E> node = (Node<E>) p; //safe cast
        
        if(node.getNext() == null) throw new IllegalArgumentException("Position no longer in list.");   //convention for invalid node

        return node;
    }
    
    /**
     * Returns the given node as a Position or null if it is a sentinel.
     * @param node given node to return as a Position
     * @return Position object representing given node (null if given is sentinel)
     */
    private Position<E> position(Node<E> node){
        if(node == header || node == trailer)
        {
            return null;    //prevents user access to sentinels
        }
        
        return node;
    }
    
    /**
     * Adds given element to the linked list between given nodes.
     * @param e element to be added to the list
     * @param predecessor node before the new node
     * @param successor node after the new node
     * @return position of the new element
     */
    private Position<E> addBetween(E e, Node<E> predecessor, Node<E> successor){
        Node<E> newest = new Node(e,predecessor,successor); //constructs new node
        
        predecessor.setNext(newest);    //links previous node to new node
        successor.setPrev(newest);      //links new node to next node
        size++;
        return newest;                                                          
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //public accessor methods
    /**
     * Returns the number of elements in list.
     * @return number of elements in list
     */
    @Override
    public int size() 
    {
        return size;
    }

    /**
     * Tests whether list contains any elements.
     * @return true if list contains elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the position of the first element in list.
     * @return the position of the first element in list (null if list is empty)
     */
    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    /**
     * Returns the position of the last element in list.
     * @return the position of the last element in list (null if list is empty)
     */
    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the position of the element before given position.
     * @param p given position in the list
     * @return the position of the element immediately before given position (null if given is first position)
     * @throws IllegalArgumentException if given position is not valid
     */
    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());  
    }

    /**
     * Returns the position of the element after given position.
     * @param p given position in the list
     * @return the position of the element immediately after given position (null if given is first position)
     * @throws IllegalArgumentException if given position is not valid 
     */
    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //public update methods
    /**
     * Inserts a new element at the front of the list and returns the position of new element.
     * @param e element to be inserted in the list
     * @return position of the new element
     */
    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /**
     * Inserts a new element at the end of the list and returns the position of new element.
     * @param e element to be inserted in the list
     * @return position of the new element
     */
    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Inserts a new element immediately before given position and returns the position of the new element.
     * @param p given position
     * @param e element to be inserted in the list
     * @return position of the new element
     * @throws IllegalArgumentException if given position is not valid
     */
    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * Inserts a new element immediately after given position and returns the position of the new element.
     * @param p given position
     * @param e element to be inserted in the list
     * @return position of the new element
     * @throws IllegalArgumentException if given position is not valid  
     */
    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /**
     * Replaces element at given position with given element and returns the element that was replaced.
     * @param p given position
     * @param e element to be inserted in the list
     * @return element that was replaced
     * @throws IllegalArgumentException if given position is not valid
     */
    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        
        E contents = node.getElement();     //store for return
        node.setElement(e);
        
        return contents;
    }

    /**
     * Removes and returns the element at given position, invalidating the position.
     * @param p given position
     * @return element that was removed from the list
     * @throws IllegalArgumentException if given position is not valid  
     */
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        
        E contents = node.getElement();     //store for return
        
        predecessor.setNext(successor);     //update links
        successor.setPrev(predecessor);     
        
        size--;
        
        node.setElement(null);              //assist garbage collection
        node.setNext(null);
        node.setPrev(null);
        
        return contents;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //additional methods
    /**
     * Returns an Iterable object of generic Position type.
     * @return Iterable object of generic Position type.
     */
    public Iterable<Position<E>> positions()
    {
        return new PositionIterable();
    }

    /**
     * Returns a generic ElementIterator object.
     * @return generic ElementIterator object.
     */
    @Override
    public Iterator<E> iterator()
    {
        return new ElementIterator();
    }
    
    /**
     * Returns String representation of LinkedPositionalList object.
     * @return String representation of LinkedPositionalList object
     */
    @Override
    public String toString()
    {
        if(isEmpty())
        {
            return "Empty list.";
        }
        
        String answer = "";
        
        Iterator<E> iter = iterator();
        
        while(iter.hasNext())
        {
            answer += iter.next();
            
            if(iter.hasNext())
            {
                answer+= ", ";
            }
        }

        return answer;
    }
    
    /**
     * Tests whether given is equal to this.
     * @param o other to compare for equality
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof LinkedPositionalList))
        {
            return false;
        }
        else
        {
            LinkedPositionalList<E> other = (LinkedPositionalList<E>) o;
            
            if(this.size() != other.size())
            {
                return false;
            }
            
            //traverse lists and compare values
            Node<E> walkA = this.header.getNext();
            Node<E> walkB = other.header.getNext();
            
            for(int i = 0; i < this.size(); i++)
            {
                if(!walkA.getElement().equals(walkB.getElement()))
                {
                    return false;
                }
                
                walkA = walkA.getNext();
                walkB = walkB.getNext();
            }
            return true;
        }
    }
}
