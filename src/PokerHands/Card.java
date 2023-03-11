/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHands;

/**
 * The <code>Card</code> class represents a single playing card of a rank and suit
 * @author Julie Schneider
 * Modified by: Samuel Sommerschield
 */
public class Card implements Comparable<Card>
{
    
    /**
     * The <code>fileExtension</code> will be the shared file extension
     * for all images.
     */
    
    private static String fileExtension;
    
    /**
     * The <code>imageFile</code> field will be a String representing the file
     * name that corresponds to the card.
     */
    
    private String imageFile;
    
    //Fields: Card has a data
    /**
     * The <code>rank</code> field will be an integer value
     * 2-10, 11 Jack, 12 Queen, 13 King and 14 Ace
     */
    
    private int rank;
   
    /**
     * The <code>suit</code> field will be an integer value
     * 1 Clubs, 2 Diamonds, 3 Hearts, 4 Spades
     */
    
    private int suit;
    
    //Constructors
    /**
     * Default constructor of the Card - creates a card of 2 1
     * representing 2 of Clubs
     */
    public Card()
    {
        this(2, 1);
    }
    
    /**
     * Other constructor that takes a rank and suit as the argument
     * @param rankIn as int that must be 2-14
     * @param suitIn as int that must be 1-4
     */
    public Card(int rankIn, int suitIn)
    {
        setRank(rankIn);
        setSuit(suitIn);
        Card.fileExtension = ".png";
    }
    //Getters
    
    /**
     * 
     * @return 
     */
    public String getImageFile()
    {
        char chRank, chSuit;
        
        if(rank == 14)
        {
            chRank = 'A';
        }
        
        else if(rank == 13)
        {
            chRank = 'K';
        }
        
        else if(rank == 12)
        {
            chRank = 'Q';
        }
        
        else if(rank == 11)
        {
            chRank = 'J';
        }
        
        //added for compatibility to image files and txt file
        else if(rank == 10)
        {
            chRank = 'T';
        }
        
        else
        {
            chRank = Integer.toString(rank).charAt(0);
        }
        
        if(suit == 1)
        {
            chSuit = 'C';
        }
        
        else if(suit == 2)
        {
            chSuit = 'D';
        }
        
        else if(suit == 3)
        {
            chSuit = 'H';
        }
        
        else
        {
            chSuit = 'S';
        }
        
        imageFile = Character.toString(chRank) + Character.toString(chSuit) + Card.fileExtension;
        
        return imageFile;
    }
    
    
    /**
     * The method returns the field representing the rank as an int
     * @return int
     */
    public int getRank(){return this.rank;}
    
    
    /**
     * the method returns the field representing the suit as an int
     * @return int
     */
    public int getSuit(){return this.suit;}
    
    //Setters
    /**
     * The method takes an int value between 2-14 to set the field <code>rank</code>
     * @param rankIn as int
     */
    public void setRank(int rankIn)
    {
        if(rankIn >= 2 && rankIn <= 14)
        {
            this.rank = rankIn;
        }
    }
    
    /**
     * The method takes an int value between 1-4 to set the field <code>suit</code>
     * @param suitIn as int
     */
    public void setSuit(int suitIn)
    {
        if(suitIn >= 1 && suitIn <= 4)
        {
            this.suit = suitIn;
        }
    }
    
    //Other
    /**
     * The compareTo method compares 2 <code>Card</code> objects and return 0
     * if they are equal, 1 if the first is greater than the second or -1
     * if the first is less than the second.
     * @param card2 of type <code>Card</code>
     * @return int
     * @see Comparable
     */
    
    @Override
    public int compareTo(Card card2)
    {
        // TODO - add the comparisons for the suit
        
        if(this.rank > card2.rank)
        {
            return 1;
        }
        else if(this.rank < card2.rank)
        {
            return -1;
        }
        else
        {
            if(this.suit > card2.suit)
            {
                return 1;
            }
            else if(this.suit < card2.suit)
            {
                return -1;
            }
            return 0;
        }
    }
    
    /**
     * The toString is overridden to take a Card object and determine the long
     * notation of the card; Therefore, Card(2,1) will return 2 of Clubs.
     * @return String
     */
    @Override
    public String toString()
    {
        String card;
        if(rank == 14)
        {
            card = "Ace of ";
        }
        
        else if(rank == 13)
        {
            card = "King of ";
        }
        
        else if(rank == 12)
        {
            card = "Queen of ";
        }
        
        else if(rank == 11)
        {
            card = "Jack of ";
        }
        
        else
        {
            card = Integer.toString(rank) + " of ";
        }
        
        if(suit == 1)
        {
            card += "Clubs";
        }
        
        else if(suit == 2)
        {
            card += "Diamonds";
        }
        
        else if(suit == 3)
        {
            card += "Hearts";
        }
        
        else
        {
            card += "Spades";
        }
        
        return card;
    }
}
