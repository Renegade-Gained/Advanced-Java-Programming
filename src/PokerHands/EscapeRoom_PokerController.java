/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerHands;

import escaperoom.EscapeRoom;
import escaperoom.EscapeRoomViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.image.*;
import javafx.stage.Popup;
import javax.swing.JOptionPane;
import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author Samuel Sommerschield
 */

//TODO-
/*
EscapeRoom_Poker JavaFX FXML Application
Display the player's hand and hide the computer hand
Allow the player to bet or fold. Each game will only be played with a simple bet or fold for one game round. 10 points a hand to bet.
Display the computer's hand
Each hand is read from the file as the computer's hand and the player's hand.
If using text to display the cards, the full card notation must be displayed. NOT the card codes!
Bonus: Incorporate the card images cardimages.zip when displaying the cards, not the text card notations.
Indicate each poker hand type for computer and player hand (ie. Royal Flush)
Indicate winner! If bet was selected the player earned the 10 points. If fold, was selected, the player earned 0 points.
Games are played in sequence order as read from the p05_poker.txt file
Allow the user to play a new game (up to 1000 games)
The player must earn a minimum of 50 points to reveal the lock code for the escape room.
*/

public class EscapeRoom_PokerController implements Initializable 
{
    
    @FXML
    private TextArea gameInfo;
    
    @FXML
    private Button foldBtn, betBtn, nextBtn;
    
    @FXML
    private GridPane playerHand, computerHand;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        PokerGames newGame = new PokerGames();
        gameInfo.setEditable(false);
        newGame.readFile();
        newGame.generateHands();
        
        setupCards(newGame);
        
        foldBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                boolean playerWon = false;
                
                if(newGame.currentPlayer.getScore() > newGame.currentComputer.getScore())
                {
                    newGame.computerPoints -= 5;
                    playerWon = true;
                }
                else if(newGame.currentPlayer.getScore() < newGame.currentComputer.getScore())
                {
                    newGame.playerPoints -= 5;
                }
                
                //get high card
                else if(newGame.currentPlayer.getScore() == newGame.currentComputer.getScore())
                {
                    for(int i = 4; i >= 0; i--)
                    {
                        if(newGame.currentPlayer.getHighCard(i) > newGame.currentComputer.getHighCard(i))
                        {
                            newGame.computerPoints -= 5;
                            playerWon = true;
                            break;
                        }
                    
                        else if(newGame.currentPlayer.getHighCard(i) < newGame.currentComputer.getHighCard(i))
                        {
                            newGame.playerPoints -= 5;
                            break;
                        }
                    }
                }
                
                else
                {
                    System.out.println("fold button ain't working chief");
                }                
                
                gameInfo.appendText("\n");
                    
                if(playerWon == true)
                {
                    gameInfo.appendText("You didn't win because you didn't \n bet, but Android lost five points.");
                }
                else if(playerWon == false)
                {
                    gameInfo.appendText("You only lost five points because \n you didn't bet.");
                }
                betBtn.setDisable(true);
                foldBtn.setDisable(true);
                nextBtn.setDisable(false);
                revealComputer(newGame);
            }
        });
        
        betBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                boolean playerWon = false;
                
                if(newGame.currentPlayer.getScore() > newGame.currentComputer.getScore())
                {
                    newGame.computerPoints -= 10;
                    newGame.playerPoints += 10;
                    playerWon = true;
                }
                else if(newGame.currentPlayer.getScore() < newGame.currentComputer.getScore())
                {
                    newGame.playerPoints -= 10;
                    newGame.computerPoints += 10;
                }
                
                //get high card
                else if(newGame.currentPlayer.getScore() == newGame.currentComputer.getScore())
                {
                    for(int i = 4; i >= 0; i--)
                    {
                        if(newGame.currentPlayer.getHighCard(i) > newGame.currentComputer.getHighCard(i))
                        {
                            newGame.computerPoints -= 10;
                            newGame.playerPoints += 10;
                            playerWon = true;
                            break;
                        }
                    
                        else if(newGame.currentPlayer.getHighCard(i) < newGame.currentComputer.getHighCard(i))
                        {
                            newGame.playerPoints -= 10;
                            newGame.computerPoints += 10;
                            break;
                        }
                    }
                }
                else
                {
                    System.out.println("bet button ain't working chief");
                }
                
                gameInfo.appendText("\n");
                if(playerWon == false)
                {
                    gameInfo.appendText("You lost ten points.");
                }
                else if(playerWon == true)
                {
                    gameInfo.appendText("You won ten points.");
                }
                
                betBtn.setDisable(true);
                foldBtn.setDisable(true);
                nextBtn.setDisable(false);
                revealComputer(newGame);
            }
        });
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if(newGame.computerPoints > 0 && newGame.playerPoints > 0)
                {
                    newGame.generateHands();
                    setupCards(newGame);
                }
                
                else if(newGame.computerPoints <= 0 && newGame.playerPoints > 0)
                {
                    gameInfo.setText("You won! You get to escape...for now.");
                    nextBtn.setText("Main Menu");
                    nextBtn.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent event)
                        {
                            EscapeRoomViewController mainMenu = new EscapeRoomViewController();
                            
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/escaperoom/EscapeRoomView.fxml"));
        
                            fxmlLoader.setController(mainMenu);
        
                            try
                            {
                                fxmlLoader.load();
                            }
                            catch(IOException ex)
                            {
                                throw new RuntimeException(ex);
                            }
        
                            Parent root = fxmlLoader.getRoot();
        
                            Scene scene = new Scene(root);
        
                            //another way
                            EscapeRoom.mainStage.setScene(scene);
                            EscapeRoom.mainStage.show();
                    }
                });
                }
                
                else if(newGame.computerPoints > 0 && newGame.playerPoints <= 0)
                {
                    gameInfo.setText("You lost! The androids kill you.");
                    nextBtn.setText("Main Menu");
                    nextBtn.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent event)
                        {
                            EscapeRoomViewController mainMenu = new EscapeRoomViewController();
                            
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/escaperoom/EscapeRoomView.fxml"));
        
                            fxmlLoader.setController(mainMenu);
        
                            try
                            {
                                fxmlLoader.load();
                            }
                            catch(IOException ex)
                            {
                                throw new RuntimeException(ex);
                            }
        
                            Parent root = fxmlLoader.getRoot();
        
                            Scene scene = new Scene(root);
        
                            //another way
                            EscapeRoom.mainStage.setScene(scene);
                            EscapeRoom.mainStage.show();
                    }
                });
                }
                
                else
                {
                    System.out.println("new game doer isn't working");
                }
            }
            
        }
        
        );
    } // end of initialize
    
    
    public void setupCards(PokerGames game)
    {     
        Image hidden = new Image("file:cardimages/rrcc_card.png");
        
        for(int i = 0; i < 5; i++)
        {
            ImageView hiddenInsert = new ImageView(hidden);
            hiddenInsert.setFitWidth(75);
            hiddenInsert.setFitHeight(100);
            computerHand.add(hiddenInsert, i, 0);
        }
        
        for(int i = 0; i < 5; i++)
        {
            Image imageInsert = new Image("file:cardimages/" + (game.currentPlayer.getCard(i).getImageFile()));
            ImageView imgInsert = new ImageView(imageInsert);
            imgInsert.setFitWidth(75);
            imgInsert.setFitHeight(100);
            playerHand.add(imgInsert, i, 0);
        }
        
        gameInfo.setText("Your Points: " + game.playerPoints);
        gameInfo.appendText("\n");
        gameInfo.appendText("Android's Points: " + game.computerPoints);
        gameInfo.appendText("\n");
        gameInfo.appendText("You have a " + game.currentPlayer.getHandType() + " hand.");
        
        foldBtn.setDisable(false);
        betBtn.setDisable(false);
        nextBtn.setDisable(true);
        
        //testing
        //System.out.println("computer: "+game.currentComputer.getScore());
        //System.out.println("player: " + game.currentPlayer.getScore());
    } // end of setupcards
    
    public void revealComputer(PokerGames game)
    {
        for(int i = 0; i < 5; i++)
        {
            Image imageInsert = new Image("file:cardimages/" + (game.currentComputer.getCard(i).getImageFile()));
            ImageView imgInsert = new ImageView(imageInsert);
            imgInsert.setFitWidth(75);
            imgInsert.setFitHeight(100);
            computerHand.add(imgInsert, i, 0);
        }
    }
}


