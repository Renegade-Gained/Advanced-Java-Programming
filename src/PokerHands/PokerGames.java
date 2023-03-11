/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Samuel Sommerschield
 */
public class PokerGames 
{
    //TODO -
    /*
    Required: PokerGames Model (NO GUI Components!)
    ReadFile - reads all 1000 games from file p054_poker.txt
    Collection of Computer PokerHands Model objects and Player PokerHands Model objects
    */
    
    /**
     * The <code>playerHand</code> field will be the ArrayList that holds all
     * the player hands read in from the file.
     */
    private ArrayList<String> playerHands;
    
    /**
     * The <code>computerHands</code> field will be the ArrayList that holds all
     * the computer hands read in from the file.
     */
    private ArrayList<String> computerHands;
    
    /**
     * 
     */
    public PokerHand currentPlayer;
    
    /**
     * 
     */
    public PokerHand currentComputer;
    
    /**
     * 
     */
    public int playerPoints;
    
    /**
     * 
     */
    public int computerPoints;
    
    
    public PokerGames()
    {
        playerPoints = 30;
        computerPoints = 30;
    }
    
    /**
     * Modified Julie Schneider's readfile code.
     * The method reads in poker hands from a file and stores them in an ArrayList<String>
     * and substrings it into the private member variables <code>playerHands</code> and
     * <code>computerHands</code>.
     */
    public void readFile()
    {
        ArrayList<String> readInHands = new ArrayList<String>();
        File file;
        FileReader fileReader;
        BufferedReader bufferedReader;
        String record;
        
        try
        {
            file = new File("p054_poker.txt");
            fileReader = new FileReader(file); // throw FileNotFoundException
            bufferedReader = new BufferedReader(fileReader);
            
            while((record = bufferedReader.readLine()) != null)
            {   
                record = bufferedReader.readLine();
                
                //remove all whitespace for easier parsing later
                record = record.replaceAll("\\s","");
                
                readInHands.add(record);
                
                //testing
                //System.out.println(record);
            }
            
            if(bufferedReader != null)
            {
                bufferedReader.close();
            }
            else
                System.out.println("No need to close - NOT open");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("FileReader instantiation");
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("readLine() method");
        }
        
        //initialize my private member variables, I guess
        playerHands = new ArrayList<String>();
        computerHands = new ArrayList<String>();
        
        //for separating the player's hands the computer's hands
        String tempStr = new String();
        String tempStrTwo = new String();
        String tempStrThree = new String();
        
        for(int i = 0; i < readInHands.size(); i++)
        {
            tempStr = readInHands.get(i);
            tempStrTwo = tempStr.substring(0, 10);
            tempStrThree = tempStr.substring(10);
            
            //testing
            //System.out.println(tempStrTwo);
            //System.out.println(tempStrThree);
            
            playerHands.add(tempStrTwo);
            computerHands.add(tempStrThree);
        }
    } // end of readfile
    
    /**
     * 
     */
    public void generateHands()
    {
        String tempStr = new String();
        String tempStrTwo = new String();
        String tempStrThree = new String();
        
        //for instantiating the cards as cards
        int rankIn = 0;
        int suitIn = 0;
        int handPicker = 0;
        
        //initialize random number generator
        Random rand = new Random();
         
        //TODO - pick two random hands instead of side by side predetermined hands
        handPicker = rand.nextInt(500);
        
        tempStr = playerHands.get(handPicker);
        tempStrTwo = computerHands.get(handPicker);
        
        //initialize my private member variables, I guess
        currentPlayer = new PokerHand();
        currentComputer = new PokerHand();
        
        
        int k = 0, j = 0;
        
        for(int i = 0; i < tempStr.length(); i+=2)
        {
            tempStrThree = tempStr.substring(i, i+2);
            
            //testing
            //System.out.println(tempStrThree);
            
            //conversion for rank
            if (tempStrThree.charAt(0) == 'A')
            {
                rankIn = 14;
            }
                
            else if(tempStrThree.charAt(0) == 'K')
            {
                rankIn = 13;
            }
                
            else if(tempStrThree.charAt(0) == 'Q')
            {
                rankIn = 12;
            }
                
            else if(tempStrThree.charAt(0) == 'J')
            {
                rankIn = 11;
            }
                
            else if(tempStrThree.charAt(0) == 'T')
            {
                rankIn = 10;
            }
                
            else
            {
                rankIn = Character.getNumericValue(tempStrThree.charAt(0));
            } 
                
            if(tempStrThree.charAt(1) == 'C')
            {
                suitIn = 1;
            }
                
            else if(tempStrThree.charAt(1) == 'D')
            {
                suitIn = 2;
            }
                
            else if(tempStrThree.charAt(1) == 'H')
            {
                suitIn = 3;
            }
                
            else if(tempStrThree.charAt(1) == 'S')
            {
                suitIn = 4;
            }
                
            else
            {
                System.out.println("Error in suit conversion");
            }
            
            
            Card tempCard = new Card(rankIn, suitIn);
            if(j < 5)
            {
                currentPlayer.setHand(j, tempCard);
            }
            j++;
            //testing
            //System.out.println(tempCard.toString());
        }
        
        for(int i = 0; i < tempStrTwo.length(); i+=2)
        {
            tempStrThree = tempStrTwo.substring(i, i+2);
            
            //testing
            //System.out.println(tempStrThree);
            
            //conversion for rank
            if (tempStrThree.charAt(0) == 'A')
            {
                rankIn = 14;
            }
                
            else if(tempStrThree.charAt(0) == 'K')
            {
                rankIn = 13;
            }
                
            else if(tempStrThree.charAt(0) == 'Q')
            {
                rankIn = 12;
            }
                
            else if(tempStrThree.charAt(0) == 'J')
            {
                rankIn = 11;
            }
                
            else if(tempStrThree.charAt(0) == 'T')
            {
                rankIn = 10;
            }
                
            else
            {
                rankIn = Character.getNumericValue(tempStrThree.charAt(0));
            } 
                
            if(tempStrThree.charAt(1) == 'C')
            {
                suitIn = 1;
            }
                
            else if(tempStrThree.charAt(1) == 'D')
            {
                suitIn = 2;
            }
                
            else if(tempStrThree.charAt(1) == 'H')
            {
                suitIn = 3;
            }
                
            else if(tempStrThree.charAt(1) == 'S')
            {
                suitIn = 4;
            }
                
            else
            {
                System.out.println("Error in suit conversion");
            }
            
            
            Card tempCard = new Card(rankIn, suitIn);
            if(k < 5)
            {
                currentComputer.setHand(k, tempCard);
            }
            
            k++;
            
            currentPlayer.setType();
            currentComputer.setType();
            currentPlayer.ScoreHand();
            currentComputer.ScoreHand();
            
            //testing
            //System.out.println(tempCard.toString());
        }
    }// end of generateHands
}
