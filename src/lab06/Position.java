/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 * Provides a general abstraction for the location of an element within a structure.
 * @author ryan.quinn.nelson and michael.kleinsasser
 * @param <E> generic type to be implemented
 */
public interface Position<E> 
{
    /**
     * Returns the element stored at this position.
     * @return the element stored at this position
     * @throws IllegalStateException if position is no longer valid
     */
    public abstract E getElement() throws IllegalStateException;
}
