/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHands;

import java.util.Arrays;
        
/**
 *
 * @author Samuel Sommerschield
 */
public class PokerHand 
{
    //TODO -
    /*
    Required: PokerHand Model (NO GUI Components!)
    Constructors, getters, setters
    Container of 5 Card Model objects
    PokerHand Type (ie. Royal Flush, Full House, Two Pair, ...)
    ScoreHand
    */
    
    /**
     * The <code>hand</code> field will be the array that holds one player's 
     * hand of cards.
     */
    private Card[] hand;
    
    /**
     * The <code>score</code> field will be the amount of points the hand of
     * cards is worth.
     */
    private int score;
    
    /**
     * The <code>handType</code> field will be the type of hand the player
     * is holding.
     */
    private String handType;
    
    //Constructors
    //PokerHand has 5 cards
    
    /**
     * Default constructor of PokerHand
     * Creates a hand of cards 2-6 of Spades
     */
    public PokerHand()
    {
        hand = new Card[5];
        hand[0] = new Card(2,4);
        hand[1] = new Card(3,4);
        hand[2] = new Card(4,4);
        hand[3] = new Card(5,4);
        hand[4] = new Card(6,4);
        
        this.setType();
        this.ScoreHand();
        
    }
    
    /**
     * Other constructor takes 5 cards as arguments
     * @param cardOne as a card object
     * @param cardTwo as a card object
     * @param cardThree as a card object
     * @param cardFour as a card object
     * @param cardFive as a card object
     * Calls ScoreHand to set <code>score</code>
     * Calls setType to set <code>handType</code>
     */
    public PokerHand(Card cardOne, Card cardTwo, Card cardThree, Card cardFour, Card cardFive)
    {
        hand = new Card[5];
        hand[0] = cardOne;
        hand[1] = cardTwo;
        hand[2] = cardThree;
        hand[3] = cardFour;
        hand[4] = cardFive;
        
        this.setType();
        this.ScoreHand();
    }
    
    //Getters
    
    /**
     * The method returns the field representing handType as String
     * @return String
     */
    public String getHandType() {return handType;}
    
    /**
     * The method returns the field representing score as an int
     * @return int
     */
    public int getScore() {return score;}
    
    /**
     * The method returns the field representing hand as Card array.
     * @return Card[]
     */
    public Card[] getHand() {return hand;}
    
    /**
     * The method returns a card from the Card array from the specified index.
     * @param index
     * @return Card
     */
    public Card getCard(int index) { return hand[index]; }
    
    /**
     * 
     */
    public int getHighCard(int index)
    {
        int high[] = new int[5];
        for(int i = 0; i < 5; i++)
        {
            high[i] = hand[i].getRank();
        }
        
        Arrays.sort(high);
        
        return high[index];
    }
    //Setters
    
    /**
     * 
     * 
     * @param index
     * @param card
     */
    public void setHand(int index, Card card)
    {
        hand[index] = card;
    }
    
    /**
     * The method determines the type of hand being held by the player and sets
     * the type.
     */
    public void setType()
    {
        //one pair, two pairs, three of a kind, full house and four of a kind checker
        int pairs = 0;
        
        boolean royalChecker = false, straightFlushChecker = false, flushChecker = false, straightChecker = false;
        
        //Check errything
        //Royal Flush
        royalChecker = this.isRoyalFlush();
        
        //Flush
        flushChecker = this.isFlush();
        
        //Straight
        straightChecker = this.isStraight();
        
        if(flushChecker == true && straightChecker == true)
        {
            straightFlushChecker = true;
        }
        
        //check entire array for pairs
        //0 - 4
        for(int i = 1; i < 5; i++)
        {
            if(hand[0].getRank() == hand[i].getRank())
            {
                pairs++;
            }
        }
        
        //1-4
        for(int i = 2; i < 5; i++)
        {
            if(hand[1].getRank() == hand[i].getRank())
            {
                pairs++;
            }
        }
        
        //2-4
        for(int i = 3; i < 5; i++)
        {
            if(hand[2].getRank() == hand[i].getRank())
            {
                pairs++;
            }
        }
        
        //3-4
        if(hand[3].getRank() == hand[4].getRank())
        {
            pairs++;
        }
        //end of checking for number of pairs
        //start checking for what kind based on booleans or pairs
        
        //Check for Royal Flush
        if(royalChecker == true)
        {
            handType = "Royal Flush";
        }
        
        //Check for Straight Flush
        else if(straightFlushChecker == true)
        {
            handType = "Straight Flush";
        }
        
        //Check for Four of a Kind
        else if(pairs == 6)
        {
            handType = "Four of a Kind";
        }
        
        //Check for Full House
        else if(pairs == 4)
        {
            handType = "Full House";
        }
        
        //Check for Flush
        else if(flushChecker == true)
        {
            handType = "Flush";
        }
        
        //Check for Straight
        else if(straightChecker == true)
        {
            handType = "Straight";
        }
        
        //Check for Three of a Kind
        else if(pairs == 3)
        {
            handType = "Three of a Kind";
        }
        
        //Check for Two Pairs
        else if(pairs ==2)
        {
            handType = "Two Pairs";
        }
        
        //Check for One Pair
        else if(pairs == 1)
        {
            handType = "One Pair";
        }
        
        else
        {
            handType = "High Card";
        }
    }
    
    //Other
    
    /*
    High Card: Highest value card.
    One Pair: Two cards of the same value.
    Two Pairs: Two different pairs.
    Three of a Kind: Three cards of the same value.
    Straight: All cards are consecutive values.
    Flush: All cards of the same suit.
    Full House: Three of a kind and a pair.
    Four of a Kind: Four cards of the same value.
    Straight Flush: All cards are consecutive values of same suit.
    Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    */
    
    /*
    The cards are valued in the order:
    2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
    */
    
    
    /**
     * The method calculates the score based off the cards in the player's
     * hand and sets the score.
     */
    public void ScoreHand()
    {
        int finalScore = 0;
        int highCard = 0;
        int ranks[] = new int[5];
        
        for(int i = 0; i < hand.length; i++)
        {
            ranks[i] = hand[i].getRank();
        }
        
        //sort the array for easy use later
        Arrays.sort(ranks);
        
        //high card will always be the fourth element
        highCard = ranks[4];
        
        //Royal Flush Points
        if("Royal Flush".equals(handType))
        {
            score = 1000 + (hand[0].getSuit());
        }
        
        //Straight Flush Points
        else if("Straight Flush".equals(handType))
        {
            score = 900 + (hand[0].getSuit());
        }
        
        //Four of a Kind Points
        else if("Four of a Kind".equals(handType))
        {
            score = 800 + ranks[3];
        }
        
        //Full House Points
        else if("Full House".equals(handType))
        {
                //if the three of a kind is at the front of the array
                if(ranks[0] == ranks[2])
                {
                    score = 700 + ranks[0];
                }
                
                //else if the three of a kind is at the end of the array
                else if(ranks[4] == ranks[2])
                {
                    score = 700 + ranks[4];
                }
                
                //default case, should never happen
                else
                {
                    score = 700;
                    System.out.println("Error in Full House of ScoreHand");
                }
        }
        
        //Flush Points
        else if("Flush".equals(handType))
        {
            score = 600 + (hand[0].getSuit());
        }
        
        //Straight Points
        else if("Straight".equals(handType))
        {
            score = 500 + highCard;
        }
        
        //Three of a Kind Points
        else if("Three of a Kind".equals(handType))
        {
            score = 400 + ranks[2];
        }
        
        //Two Pairs Points
        else if("Two Pairs".equals(handType))
        {
            //if the highest pair is sorted to the end of the array
                if(ranks[4] == ranks[3])
                {
                    score = 300 + ranks[4];
                }
                
                //else if the highest card is not a pair
                else if(ranks[3] == ranks[2])
                {
                    score = 300 + ranks[3];
                }
                
                //default case, should never happen
                else
                {
                    score = 300;
                    System.out.println("Error in Two Pairs of ScoreHand");
                }
        }
        
        //One Pair Points
        else if("One Pair".equals(handType))
        {
            //if pair is at front
            if(ranks[0] == ranks[1])
            {
                score = 20 + ranks[0];
            }
            
            //if pair is at 1 and 2
            else if (ranks[1] == ranks[2])
            {
                score = 20 + ranks[1];
            }
            
            //if pair is at 2 and 3
            else if (ranks[2] == ranks[3])
            {
                score = 20 + ranks[2];
            }
            
            //if pair is at 4 and 5
            else if (ranks[3] == ranks[4])
            {
                score = 20 + ranks[3];
            }
            
            //default case, should never happen
            else
            {
                score = 20;
            }
        }
        
        //High Card Points
        else if("High Card".equals(handType))
        {
            score = highCard;
        }
        
        else
        {
            System.out.println("Error in ScoreHand");
        }
        
        finalScore = score;
    }
    
    /**
     * The method determines if the hand is a royal flush and returns a boolean
     * value.
     * @return boolean
     */
    public boolean isRoyalFlush()
    {
        boolean isRoyalFlush = false, sameSuit = false;
        
        boolean ten = false, jack = false, queen = false, king = false, ace = false;
        
        for(int i = 0; i < hand.length; i++)
        {
            if(hand[i].getRank() == 14)
            {
                ace = true;
            }
            else if(hand[i].getRank() == 13)
            {
                king = true;
            }
            else if(hand[i].getRank() == 12)
            {
                queen = true;
            }
            else if(hand[i].getRank() == 11)
            {
                jack = true;
            }
            else if(hand[i].getRank() == 10)
            {
                ten = true;
            }
        }
        
        sameSuit = this.isFlush();
        
        if(ace == true && king == true && queen == true && jack == true && ten == true && sameSuit == true)
        {
            isRoyalFlush = true;
        }
        
        return isRoyalFlush;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isStraight()
    {
        boolean isStraight = true;
        
        int checkHand[] = new int[5];
        
        for(int i = 0; i < checkHand.length; i++)
        {
            checkHand[i] = hand[i].getRank();
        }
        
        Arrays.sort(checkHand);
        
        for(int i = 0; i < 4; i++)
        {
            if(checkHand[i] != (checkHand[i+1] + 1))
            {
                isStraight = false;
            }
        }
        
        return isStraight;
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isFlush()
    {
        boolean isFlush = true;
        
        //check for same suit
         for(int i = 1; i < 5; i++)
        {
            if(hand[0].getSuit() != hand[i].getSuit())
            {
                isFlush = false;
            }
        }
        
        //1-4
        if(isFlush == true)
        {
            for(int i = 2; i < 5; i++)
            {
                if(hand[1].getSuit() != hand[i].getSuit())
                {
                    isFlush = false;
                }
            }
        }
        
        //2-4
        if(isFlush == true)
        {
            for(int i = 3; i < 5; i++)
            {
                if(hand[2].getSuit() != hand[i].getSuit())
                {
                    isFlush = false;
                }
            }
        }
        
        //3-4
        if(hand[3].getSuit() != hand[4].getSuit())
        {
            isFlush = false;
        }
        
        return isFlush;
    }
    
}
