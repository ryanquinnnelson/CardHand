/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a single players hand of cards.
 * @author ryan.quinn.nelson and michael.kleinsasser
 */
public class CardHand implements Iterable<Card>
{
    // -------------- nested PositionIterator class -------------//
    /**
     * Implements Iterator interface for Position objects of type Card.
     */
    private class PositionIterator implements Iterator<Position<Card>>
    {
        private Position<Card> cursor = hand.first();
        private Position<Card> recent = null;
        private char suitCharacter; 
        
        public PositionIterator()
        {
            //empty on purpose
        }
        
        /**
         * Constructs PositionIterator object where cursor is given as argument.
         * @param suit Position for cursor to start
         */
        public PositionIterator(Position<Card> suit)
        {
            cursor = suit;
            suitCharacter = suit.getElement().getSuit();
        }
        
        
        /**
         * Tests whether iterator has another element in the same suit.
         * @return true if another element exists, false otherwise
         */
        @Override
        public boolean hasNext() 
        {
             //first expression short-circuits second to prevent NullPointerException
            return cursor != null && cursor.getElement().getSuit() == suitCharacter;
        }

        /**
         * Returns next Position in iterator.
         * @return next Position in iterator
         * @throws NoSuchElementException if iterator has no remaining elements in the same suit
         */
        
        @Override
        public Position<Card> next() throws NoSuchElementException
        {
            if(cursor == null) throw new NoSuchElementException("nothing left");
            
            recent = cursor;
            cursor = hand.after(cursor);
            return recent;
        }

        
    }  // -------------- end of nested PositionIterator class -------------// 
    
    private Position<Card>       hearts;            //represents the highest Card object with suit of Heart in CardHand
    private Position<Card>       clubs;             //represents the highest Card object with suit of Club in CardHand
    private Position<Card>       spades;            //represents the highest Card object with suit of Spade in CardHand
    private Position<Card>       diamonds;          //represents the highest Card object with suit of Diamond in CardHand
    private LinkedPositionalList<Card>  hand;       //represents hand of Card objects as a Linked Positional List
    
    
    /**
     * Constructs CardHand object with empty list and empty position references.
     * Cards are organized in CardHand so that the highest Card of a suit is the 
     * Position field, and any additional Card objects of the same suit follow
     * subsequently in order from highest rank to lowest rank.
     */
    public CardHand(){
        
        hand        = new LinkedPositionalList<Card>();
        
        hearts      = null; //indicates hand of Card objects doesn't contain a Card object with suit of Heart
        clubs       = null; //indicates hand of Card objects doesn't contain a Card object with suit of Club
        spades      = null; //indicates hand of Card objects doesn't contain a Card object with suit of Spade
        diamonds    = null; //indicates hand of Card objects doesn't contain a Card object with suit of Diamond
        
    }
    
    
    
    //private Utility methods
    /**
     * Performs operations to add a Card of Heart suit to hand.
     * @param c card to add to hand
     */
    private void addHeart(Card c)
    {
        if(hearts == null)  //checks whether hand has any hearts cards yet
        {
            hearts = hand.addLast(c);   //adds card to the end of the hand list and stores reference to first hearts Position    
        } 
        else //hand contains at least another hearts card
        {
            //determine correct position of new card within the subset of other hearts cards in hand
            Iterator<Position<Card>> suitIt = suitIterator(hearts); //constructs Iterator to go through all Positions for specific suit
            
            Position<Card> newest;          //reference to Position of new card once it is added
            Position<Card> current = null;  //used for traversing the existing cards in the suit
            
            
            while (suitIt.hasNext()) //iterates through all hearts in hand
            {
                current = suitIt.next();

                if (c.getRank() > current.getElement().getRank()) //if new Card's rank is greater than existing card, Card belong before it
                {
                    newest = hand.addBefore(current, c);

                    if (current == hearts) //if the card it went before was the reference to the first hearts card in hand
                    {
                        hearts = newest;
                    }
                    return; //skips remainder of method
                }

            }
            hand.addAfter(current, c);  //if here, new Card had smaller rank than any of the other hearts in hand
        }
    }
    
    /**
     * Performs operations to add a Card of Club suit to hand.
     * @param c card to add to hand
     */
    private void addClub(Card c)
    {
        if(clubs == null) //checks whether hand has any clubs cards yet
        {
            clubs = hand.addLast(c);//adds card to the end of the hand list and stores reference to first clubs Position 
            
        } 
        else//hand contains at least another clubs card
        {
            //determine correct position of new card within the subset of other clubs cards in hand
            Iterator<Position<Card>> suitIt = suitIterator(clubs); //constructs Iterator to go through all Positions for specific suit
            
            Position<Card> newest;          //reference to Position of new card once it is added
            Position<Card> current = null;  //used for traversing the existing cards in the suit
            
            while (suitIt.hasNext()) //iterates through all clubs in hand
            {
                current = suitIt.next();

                if (c.getRank() > current.getElement().getRank()) //if new Card's rank is greater than existing card, Card belong before it
                {
                    newest = hand.addBefore(current, c);

                    if (current == clubs) //if the card it went before was the reference to the first clubs card in hand
                    {
                        clubs = newest;
                    }
                    return;//skips remainder of method
                }

            }
            hand.addAfter(current, c);//if here, new Card had smaller rank than any of the other clubs in hand
        }
    }
    
    /**
     * Performs operations to add a Card of Spade suit to hand.
     * @param c card to add to hand
     */
    private void addSpade(Card c)
    {
        if(spades == null)  //checks whether hand has any spades cards yet
        {
            spades = hand.addLast(c);   //adds card to the end of the hand list and stores reference to first spades Position    
        } 
        else //hand contains at least another spades card
        {
            //determine correct position of new card within the subset of other spades cards in hand
            Iterator<Position<Card>> suitIt = suitIterator(spades); //constructs Iterator to go through all Positions for specific suit
            
            Position<Card> newest;          //reference to Position of new card once it is added
            Position<Card> current = null;  //used for traversing the existing cards in the suit
            
            
            while (suitIt.hasNext()) //iterates through all spades in hand
            {
                current = suitIt.next();

                if (c.getRank() > current.getElement().getRank()) //if new Card's rank is greater than existing card, Card belong before it
                {
                    newest = hand.addBefore(current, c);

                    if (current == spades) //if the card it went before was the reference to the first spades card in hand
                    {
                        spades = newest;
                    }
                    return;//skips remainder of method
                }

            }
            hand.addAfter(current, c);  //if here, new Card had smaller rank than any of the other spades in hand
        }
    }
    
    /**
     * Performs operations to add a Card of Diamond suit to hand.
     * @param c card to add to hand
     */
    private void addDiamond(Card c)
    {
        if(diamonds == null)  //checks whether hand has any diamonds cards yet
        {
            diamonds = hand.addLast(c);   //adds card to the end of the hand list and stores reference to first diamonds Position    
        } 
        else //hand contains at least another diamonds card
        {
            //determine correct position of new card within the subset of other diamonds cards in hand
            Iterator<Position<Card>> suitIt = suitIterator(diamonds); //constructs Iterator to go through all Positions for specific suit
            
            Position<Card> newest;          //reference to Position of new card once it is added
            Position<Card> current = null;  //used for traversing the existing cards in the suit
            
            
            while (suitIt.hasNext()) //iterates through all diamonds in hand
            {
                current = suitIt.next();

                if (c.getRank() > current.getElement().getRank()) //if new Card's rank is greater than existing card, Card belong before it
                {
                    newest = hand.addBefore(current, c);

                    if (current == diamonds) //if the card it went before was the reference to the first diamonds card in hand
                    {
                        diamonds = newest;
                    }
                    return;//skips remainder of method
                }

            }
            hand.addAfter(current, c);  //if here, new Card had smaller rank than any of the other diamonds in hand
        }
    }
    
    /**
     * Performs operations to add a Card of given suit to hand. 
     * @param c card to add
     * @param suit one of the four suits
     * @return Position representing first card of given suit in hand
     */
    private Position<Card> add(Card c, Position<Card> suit)
    {
        
        if(suit == null) //checks whether hand has any cards of given suit yet
        { 
            return hand.addLast(c);//adds card to the end of the hand list and stores reference to first Position of suit   
        } 
        else //hand contains at least another card of the same suit
        {
            //determine correct position of new card within the subset of other cards of same suit in hand
            Iterator<Position<Card>> suitIt = suitIterator(suit);//constructs Iterator to go through all Positions for given suit
            
            Position<Card> newest;          //reference to Position of new card once it is added
            Position<Card> current = null;  //used for traversing the existing cards in the suit
            
            while (suitIt.hasNext()) //iterates through all cards of a single suit in hand
            {
                current = suitIt.next();

                if (c.getRank() > current.getElement().getRank()) //if new Card's rank is greater than existing card, Card belong before it
                {
                    newest = hand.addBefore(current, c);

                    if (current == getPosition(suit)) //if the card it went before was the reference to the first card of that suit in hand
                    {
                        return newest; //returns reference to new first Position of suit in hand
                    }
                    return getPosition(suit); //returns reference to existing first Position of suit in hand
                }

            }
            hand.addAfter(current, c); //if here, new Card had smaller rank than any of the other cards of same suit in hand
            return getPosition(suit); //returns reference to existing first Position of suit in hand
        }
    }
    
    /**
     * Performs operations necessary to remove a card from the hand without losing reference to first Position of each suit.
     * @param p Position to remove from hand
     * @return Card removed from hand
     */
    private Card removeCard(Position<Card> p)
    {
        Position<Card> toRemove = getPosition(p);
        Position<Card> next = null;
        
        //checks if Position to be removed was a reference to first Position of any suit
        //if it is, need to shift reference of first Position to next Card of that suit in hand
        //if no additional cards of that suit exist in hand, null the first Position reference of that suit
        if (toRemove == hearts) 
        {
            try 
            {
                next = hand.after(hearts); //look at position immediately after hearts
                
                if(next != null && next.getElement().getSuit() != 'H')  //no more cards of that suit 
                {
                    hearts = null; //null the reference
                }
                else
                {
                    hearts = next; //otherwise reference is shifted to next
                }
            } 
            catch (IllegalArgumentException iae)    //if position after hearts is trailer 
            {
                hearts = null;
            }    
        }
        else if(toRemove == clubs)
        {
            try 
            {
                next = hand.after(clubs); //look at position immediately after hearts
                
                if(next != null && next.getElement().getSuit() != 'C')  //no more cards of that suit 
                {
                    clubs = null; //null the reference
                }
                else
                {
                    clubs = next; //otherwise reference is shifted to next
                }
            } 
            catch (IllegalArgumentException iae)    //if position after hearts is trailer 
            {
                clubs = null;
            }
        }
        else if(toRemove == spades)
        {
            try 
            {
                next = hand.after(spades); //look at position immediately after hearts
                
                if(next != null && next.getElement().getSuit() != 'S')  //no more cards of that suit 
                {
                    spades = null; //null the reference
                }
                else
                {
                    spades = next; //otherwise reference is shifted to next
                }
            } 
            catch (IllegalArgumentException iae)    //if position after hearts is trailer 
            {
                spades = null;
            }
        }
        else if(toRemove == diamonds)
        {
            try 
            {
                next = hand.after(diamonds); //look at position immediately after hearts
                
                if(next != null && next.getElement().getSuit() != 'D')  //no more cards of that suit 
                {
                    diamonds = null; //null the reference
                }
                else
                {
                    diamonds = next; //otherwise reference is shifted to next
                }
            } 
            catch (IllegalArgumentException iae)    //if position after hearts is trailer 
            {
                diamonds = null;
            }
        }
        else //not a reference to first Position of suit
        {
            //do nothing
        } 
        return hand.remove(p); 
    }
    
    
    
    
    
    //public accessor methods
    /**
     * Returns linked positional list.
     * @return linked positional list
     */
    public LinkedPositionalList<Card> getHand()
    {
        return hand;
    }
    
    /**
     * Returns Position field for given suit.
     * @param suit given suit to return Position for
     * @return Position field for given suit
     */
    public Position<Card> getPosition(Position<Card> suit)
    {
        
        switch(suit.getElement().getSuit())
        {
            case 'H':
                return hearts;
            case 'C':
                return clubs;
            case 'S':
                return spades;
            case 'D':
                return diamonds;
            default:
                return null;
        } 
    }
    
    /**
     * Returns Position field for heart suit.
     * @return Position field for heart suit
     */
    public Position<Card> getHeartsPosition()
    {
        return hearts;
    }
    
    /**
     * Returns Position field for club suit.
     * @return Position field for club suit
     */
    public Position<Card> getClubsPosition()
    {
        return clubs;
    }
    
    /**
     * Returns Position field for spade suit.
     * @return Position field for spade suit
     */
    public Position<Card> getSpadesPosition()
    {
        return spades;
    }
    
    /**
     * Returns Position field for diamond suit.
     * @return Position field for diamond suit
     */
    public Position<Card> getDiamondsPosition()
    {
        return diamonds;
    }


    
    
    
    
    // additional methods
    /**
     * Adds given card to hand using methods particular to that suit.
     * @param c Card to add to hand
     */
    public void addCard(Card c)
    {
        switch(c.getSuit())
        {
            case 'H':
                addHeart(c);
                break;
            case 'C':
                addClub(c);
                break;
            case 'S':
                addSpade(c);
                break;
            case 'D':
                addDiamond(c);
                break;
        }  
    }
    
    /**
     * Adds given card to hand using single method for all suits.
     * @param c card to add
     * @return Position of first card of suit in hand
     */
    public Position<Card> addCard2(Card c)
    {
        switch(c.getSuit())
        {
            case 'H':
                hearts = add(c, hearts);
                return hearts;
                
            case 'C':
                clubs = add(c, clubs);
                return clubs;
            case 'S':
                spades = add(c, spades);
                return spades;
            case 'D':
                diamonds = add(c, diamonds);
                return diamonds;
        }
        
        return null;
    }

    /**
     * Removes and returns a card of given suit from player's hand or arbitrary card if hand has no cards of that suit.
     * @param suit given suit to return
     * @return Card of given suit, or arbitrary Card of another suit if hand has no Card objects of that suit
     */
    public Card play(Position<Card> suit) throws IllegalStateException, IllegalArgumentException
    {
        Position<Card> removed;

        if(hand.isEmpty())//no Card objects in hand
        {
            return null; 
        }
        
        if(suit == null) //chose an arbitrary card to remove - default is to choose last Card in hand
        {
            return removeCard(hand.last());
        }
        
        else    //removes first Card of the suit in hand
        {
            
            Iterator<Position<Card>> iter = suitIterator(suit);
 
            if(iter.hasNext())
            {
                return removeCard(iter.next());
            }
            else
            {
                throw new IllegalStateException("problem with Iterator");    //something went wrong
            }
        }
    }
    
    
    
    /**
     * Returns PositionIterator object that iterates through given suit.
     * @param suit suit of the particular PositionIterator
     * @return PositionIterator of type Card for a single suit
     */
    public Iterator<Position<Card>> suitIterator(Position<Card> suit)
    {
        Iterator<Position<Card>> iter = new PositionIterator(suit);
        
        return iter;
    }
    
    /**
     * Returns ElementIterator object of type Card that iterates through every Card in hand.
     * @return ElementIterator object of type Card 
     */
    @Override
    public Iterator<Card> iterator()
    {
        return hand.iterator();
    }
   
    /**
     * Returns a String representation of CardHand object.
     * @return String representation of CardHand object
     */
    @Override
    public String toString()
    {
        if(hand.isEmpty())
        {
            return "Empty hand.";
        }
        
        String answer = "CardHand= ";
        
        Iterator<Card> iter = iterator();
        
        while(iter.hasNext())
        {
            answer += iter.next().toString();
            
            if(iter.hasNext())
            {
                answer += ", ";
            }
        }
        
        return answer;
    }
    
    /**
     * Tests whether this is equal to given object.
     * @param o object to compare with this
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof CardHand))
        {
            return false;
        }
        else
        {
            CardHand other = (CardHand) o;
            
            return this.hand.equals(other.hand);
        }
    }
    
    /**
     * Testing only.
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Testing CardHand methods\n");
        Deck deck = new Deck();
        deck.shuffle();
        
        CardHand one = new CardHand();
        CardHand three = new CardHand();
        System.out.println("equals " + one.equals(three));
        System.out.println("toString " + one);
        while(deck.size() != 39)
        {
            Card temp = deck.dealCard();
            
            one.addCard2(temp);
        }
        
        System.out.println("getHand " + one.getHand());
        
        System.out.println("toString " + one);
        System.out.println("getSpades " + one.getSpadesPosition());
        System.out.println("getSpades " + one.getHeartsPosition());
        System.out.println("getSpades " + one.getDiamondsPosition());
        System.out.println("getSpades " + one.getClubsPosition());
        
        System.out.println("equals " + one.equals(three));
        
        System.out.println("Testing play() method");
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        System.out.println("Player 1 plays " + one.play(one.getSpadesPosition()));
        
        

//        //Tests play() method
//        CardHand four = new CardHand();
//        four.addCard(new Card('H', 10));
//        four.addCard(new Card('C', 10));
//        four.addCard(new Card('D', 10));
//        four.addCard(new Card('S', 10));
//        
//        Position<Card> temp = four.getHeartsPosition();
//        four.play(temp);
//        four.play(temp);

       
    }
}
