/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;

/**
 * Represents a traditional playing card with rank and suit.
 * @author ryan.quinn.nelson and michael.kleinsasser
 */
public class Card 
{
    private char suit;      //represents suit of card (i.e. 'c' for "clubs")
    private int rank;       //represents rank of card (i.e. 2 is 2, 11 is Jack, 14 is Ace)
    
   

    /**
     * Constructs Card object with given suit and rank.
     * @param suit given suit (e.g. "Clubs")
     * @param rank given rank as an integer (e.g. 10 is 10, 11 is Jack, 14 is Ace)
     */
    public Card(char suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    //public accessor methods
    /**
     * Returns the suit of this.
     * @return suit of this
     */
    public char getSuit() {
        return suit;
    }

    /**
     * Returns the rank of this.
     * @return rank of this
     */
    public int getRank() {
        return rank;
    }
    
    //public update methods
    /**
     * Sets the suit of this.
     * @param suit the suit to set for this
     */
    public void setSuit(char suit) {
        this.suit = suit;
    }
    
    /**
     * Sets the rank of this.
     * @param rank the rank to set for this
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    //additional methods
    /**
     * Returns a String representation of card. 
     * @return String representation of card
     */
    @Override
    public String toString()
    {
        String name = "";
        
        if(rank > 10)   //converts integer value to String of traditional card rank
        {                     
            switch(rank)
            {
                case 11:
                    name = "J";
                    break;
                case 12:
                    name = "Q";
                    break;
                case 13:
                    name = "K";
                    break;
                case 14:
                    name = "A";
                    break;
            }       
        }
        else        //integer value is traditional card rank
        {
            name = rank + "";
        }

        return name + "of" + suit;
    }
    
    /**
     * Tests whether this is equal to given Object.
     * @param o object to compare with this
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Card))
        {
            return false;
        }
        else
        {
            Card other = (Card) o;
            
            return this.rank == other.getRank() && this.suit == other.getSuit();
        }
    }
    
    /**
     * Testing only.
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Test Card Methods");
        Card one = new Card('H', 14);
        Card two = new Card('C', 10);
        Card three = new Card('C', 10);
        Card four = new Card('S', 12);
        Card five = new Card('D', 2);
//        System.out.println(one);
//        System.out.println(two);
//        System.out.println(two.equals(three));
//        System.out.println(one.equals(two));
//        
//        System.out.println("\nTest ArrayList methods");
//        ArrayList<Card> test1 = new ArrayList(15);
//        ArrayList<Card> test2 = new ArrayList(15);
//        System.out.println(test1);
//        System.out.println(test1.isEmpty());
//        System.out.println(test1.size());
//        
//        test1.add(one);
//        test1.add(two);
//        test2.add(one);
//        test2.add(two);
//        System.out.println(test1);
//        System.out.println(test1.equals(test2));
//        System.out.println(test1.isEmpty());
//        System.out.println(test1.size());
//        System.out.println(test1.get(0));
//        
//        System.out.println(test1.set(0, two));
//        System.out.println(test1.get(0));
//        System.out.println("Test2 size: " + test2.size());
//        test2.add(2, three);
//        System.out.println(test1.equals(test2));
//        System.out.println(test2);
//        System.out.println(test2.remove(0));
//        
//        
//        System.out.println("\nTest LinkedPositionalList methods");
//        LinkedPositionalList<Card> test3 = new LinkedPositionalList<>();
//        LinkedPositionalList<Card> test4 = new LinkedPositionalList<>();
//        System.out.println("First " + test3.first());
//        System.out.println("IsEmpty " + test3.isEmpty());
//        System.out.println("Equals " + test3.equals(test4));
//        System.out.println("size " + test3.size());
//        System.out.println("toString " + test3);
//        
//        test3.addFirst(one);
//        test3.addLast(two);
//        System.out.println("First " + test3.first());
//        System.out.println("IsEmpty " + test3.isEmpty());
//        System.out.println("Equals " + test3.equals(test4));
//        System.out.println("size " + test3.size());
//        System.out.println("last " + test3.last());
//        System.out.println("toString " + test3);
//        
//        test4.addFirst(one);
//        test4.addLast(two);
//        
//        System.out.println("Equals " + test3.equals(test4));
//        
//        for(Position<Card> c : test3.positions())
//        {
//            System.out.println("positions " + c.getElement());
//        }
//        
//        System.out.println("toString " + test3);
//        System.out.println("after " + test3.after(test3.first()));
//        System.out.println("before " + test3.before(test3.last()));
//        System.out.println("before " + test3.before(test3.first()));
//        
//        System.out.println("AddBefore " + test3.addBefore(test3.last(), four));
//        System.out.println("AddAfter " + test3.addAfter(test3.first(), five));
//        System.out.println("toString " + test3);
//        
//        System.out.println("remove " + test3.remove(test3.first()));
//        System.out.println("toString " + test3);
//        System.out.println("set " + test3.set(test3.first(), one));
//        System.out.println("toString " + test3);
    }
    
}
