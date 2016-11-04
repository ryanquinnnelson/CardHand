/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

import java.util.Iterator;

/**
 * Represents a card game.
 * @author ryan.quinn.nelson and michael.kleinsasser
 */
public class Game 
{
    private int         players;            //number of players in game
    private int         cardsPerHand;       //total number of cards each player gets
    private Deck        deck;               //represents deck of cards
    private CardHand [] hands;              //represents an array of all hands in the game
    
    /**
     * Constructs Game object with a given number of players.
     * @param players number of players in game
     * @param cards total number of cards each player gets 
     */
    public Game(int players, int cards)
    {
        this.players = players;
        cardsPerHand = cards;
        
        deck = new Deck();      //construct new deck
        deck.shuffle();         //puts cards in deck in random order
               
        hands = new CardHand [this.players];    //constructs array with a cell for each player's hand
                
        for(int i = 0; i < players; i++)        //constructs CardHand object for each cell in array
        {
            hands[i] = new CardHand();
        }
    }
    
    
    
    //public accessor methods
    
    /**
     * Returns maximum number of cards allowed in a player's hand.
     * @return number of cards allowed in the player's hand
     */
    public int getCardsPerHand()
    {
        return cardsPerHand;
    }
    
    /**
     * Returns the CardHand of a single player.
     * @param index location of player in CardHand array
     * @return CardHand representing the hand of a single player
     * @throws ArrayIndexOutOfBoundsException if index is outside array
     */
    public  CardHand getPlayerHand(int index) throws ArrayIndexOutOfBoundsException
    {
        if(index < 0 || index >= hands.length)
        {
            throw new ArrayIndexOutOfBoundsException("index is out of bounds: " + index);
        }
        return hands[index];
    }
    
    
    
    
    
    
    
    
    //additional methods
    /**
     * Adds one Card object from the Deck to each CardHand in the hands array.
     * Simulates dealing one Card to each player from the Deck.
     */
    public void getCard()
    {
        for(CardHand ch : hands)    //iterates through all positions in hands array
        {
            Card dealt = deck.dealCard();
            ch.addCard2(dealt);
        }
    }
    
    /**
     * Prints out all of the cards in each player's hand.
     */
    public void displayHands()
    {
        for(int i = 0; i < hands.length; i++)
        {
            System.out.print("Player " + (i+1) + ": ");
            
            Iterator<Card> iter = hands[i].iterator();      //constructs iterator of all Card objects in current CardHand
            
            
            
            while(iter.hasNext())     //prints out all Card objects
            {
                System.out.printf("%-6s", iter.next());     //pads printing with 6 spaces per entry to keep output orderly
            }
            System.out.println("");    
        }
    }
    
    /**
     * Returns String representation of Game object.
     * @return String representation of Game object
     */
    @Override
    public String toString()
    {
        return "Game of " + players + " players with " + cardsPerHand + " cards per hand";
    }
    
    /**
     * Tests whether this is equal to given Object.
     * @param o object to compare with this
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Game))
        {
            return false;
        }
        else
        {
            Game other = (Game) o;
            
            boolean b1 = this.players == other.players;
            boolean b2 = this.deck.equals(other.deck);
            boolean b3 = this.cardsPerHand == other.cardsPerHand;
            
            return b1 && b2 && b3;
        }
    }
    
    /**
     * Testing only.
     * @param args 
     */
    public static void main(String[] args) 
    {
        System.out.println("Testing game methods\n");
        Game two = new Game(4, 13);
        Game forty = new Game(4, 13);
        System.out.println("toString " + two);
        
        System.out.println("equals " + two.equals(forty));
        System.out.println("getPlayerHand " + two.getPlayerHand(0));
        
        System.out.println("getcardsperhand " + two.getCardsPerHand());
        System.out.println("getCard, displayHands:");
        for(int i = 0; i < 3; i++)
        {
            two.getCard();
            two.displayHands(); 
            System.out.println("");
        }
        System.out.println("getPlayerHand " + two.getPlayerHand(0));
        System.out.println("toString " + two);
        
        
    }
}
