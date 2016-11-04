/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

import java.util.Random;

/**
 * Represents a traditional deck of 52 playing cards.
 *
 * @author ryan.quinn.nelson and michael.kleinsasser
 */
public class Deck 
{
    private ArrayList<Card> deck;       //represents single deck of cards

    public Deck() 
    {
        deck = new ArrayList<>(52);

        //represent all possible suits and ranks of cards in deck
        char[] suits    = {'D', 'C', 'H', 'S'};
        int[] ranks     = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        //instantiate all positions possible and add to deck in order
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                Card newest = new Card(suits[i], ranks[j]);
                deck.add(newest);
            }
        }
    }

    
    
    
    
    //public accessor methods
    /**
     * Returns number of Card objects in Deck.
     * @return number of Card objects in Deck
     */
    public int size() {
        return deck.size();
    }

    
    
    
    
    
   //additional methods
    /**
     * Pseudo-random reorganization of ordering of Card objects in Deck.
     * Approximates the result of shuffling cards once in the real world.
     */
    public void shuffle() 
    {
        ArrayList<Card> temp = new ArrayList<>(52); //temporary ArrayList

        Random rand = new Random();

        //each instance takes a random Card from deck ArrayList and adds it to temporary ArrayList
        for (int i = 0; i < 52; i++) 
        {
            int index = rand.nextInt(deck.size()); //generate random integer between 0 and maximum position in array
            temp.add(deck.remove(index));   //Deck ArrayList decreases in size as Card objects are removed
        }

        deck = temp;        //assign reference to Deck of cards in pseudo-random order to field
        temp = null;        //assist garbage collection
    }

    /**
     * Deals a Card from the deck, which removes the card from the ArrayList.
     * Always returns the Card in the last position of the ArrayList. If the deck
     * has been shuffled before calling this method, the Card returned will be random.
     * @return Card from the deck
     */
    public Card dealCard() 
    {
        return deck.remove(deck.size() - 1);
    }

    /**
     * Returns a String representation of Deck object.
     * @return String representation of Deck object
     */
    @Override
    public String toString() {
        String answer = "";

        for (int i = 0; i < deck.size(); i++) {
            answer += deck.get(i) + ", ";
        }
        return answer;
    }

    /**
     * Checks whether this is equal to given Object.
     * @param o object to compare with this
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deck)) {
            return false;
        } else {
            Deck other = (Deck) o;

            if (this.deck.size() != other.deck.size()) {
                return false;
            }

            //Deck objects are the same if all Card objects in the deck are in the same order
            for (int i = 0; i < deck.size(); i++) 
            {
                if (!this.deck.get(i).equals(other.deck.get(i))) 
                {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Testing only.
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("testing Deck methods\n");
        Deck one = new Deck();
        Deck two = new Deck();
        System.out.println("size " + one.size());
        System.out.println("toString " + one);
        System.out.println("Equals " + one.equals(two));

        one.shuffle();
        System.out.println("Equals " + one.equals(two));
        System.out.println("toString " + one);

        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("dealCard " + one.dealCard());
        System.out.println("size " + one.size());
        System.out.println("toString " + one);
        
        
    }
}
