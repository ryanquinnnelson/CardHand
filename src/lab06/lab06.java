/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 * Represents dealing a card game.
 * @author ryan.quinn.nelson and michael.kleinsasser
 */
public class lab06 
{

    /**
     * Creates an instance of a game with 4 players and 13 cards per hand.
     * Deals cards to each player one at a time and displays the hands of each player after each round is dealt.
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       Game one = new Game(4, 13);
       System.out.println("---------------- Starting Game ----------------\n");
       
       for(int i = 0; i < one.getCardsPerHand(); i++)
       {
           System.out.println("Card " + (i+1) + ":");
           one.getCard();
           one.displayHands();
           System.out.println("");
           
       }
       
        System.out.println("---------------- Game over ----------------");
    }
    
}
