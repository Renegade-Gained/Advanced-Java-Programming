/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku;

import PokerHands.EscapeRoom_PokerController;
import escaperoom.EscapeRoom;
import escaperoom.EscapeRoomViewController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.stage.Popup;

/**
 *
 * @author Samuel Sommerschield
 */
public class SudokuViewController implements Initializable {
        
    @FXML
    private GridPane gridOne, gridTwo, gridThree, gridFour, gridFive, gridSix, gridSeven, gridEight, gridNine;
    
    @FXML
    private Button submitButton;
    
    @FXML
    private TextField triesLeft;
    
    private TextField [] txtFields;
    private ArrayList<String> input;
    private int tries;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        txtFields = new TextField[81];
        input = new ArrayList<String>();
        input.addAll(readFile());
        //testing
        //System.out.println(input);
        //input.forEach(line -> System.out.println(line));
        //System.out.println(input.get(0));
        
        setupBoard(input);
        tries = 3;
        
        submitButton.setOnAction(new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle(ActionEvent e) 
        {
            
            String holder = new String();
            char[] characterChecker = new char[9];
            char[] checkAgainst = new char[9];
            checkAgainst[0] = '1';
            checkAgainst[1] = '2';
            checkAgainst[2] = '3';
            checkAgainst[3] = '4';
            checkAgainst[4] = '5';
            checkAgainst[5] = '6';
            checkAgainst[6] = '7';
            checkAgainst[7] = '8';
            checkAgainst[8] = '9';
            
            boolean isCorrect = true;
            
            //check row by row
            for(int i = 0; i < txtFields.length; i = i+9)
            {
                for(int j = 0; j < 9; j++)
                {
                    holder = holder + (txtFields[i+j].getText());
                }
                //System.out.println(holder);
                if(holder.length() == 9)
                {
                    characterChecker = holder.toCharArray();
                    Arrays.sort(characterChecker);
                }
                if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
                holder = "";
            }
            
            //check column by column
            holder = "";
            int jump = 9;
            for(int i = 0; i < 9; i++)
            {
                holder = txtFields[i].getText() + txtFields[i+jump].getText() 
                        + txtFields[i+(jump*2)].getText() + txtFields[i+(jump*3)].getText() 
                        + txtFields[i+(jump*4)].getText() + txtFields[i+(jump*5)].getText() 
                        + txtFields[i+(jump*6)].getText() + txtFields[i+(jump*7)].getText() 
                        + txtFields[i+(jump*8)].getText();
                if(holder.length() == 9)
                {
                    characterChecker = holder.toCharArray();
                    Arrays.sort(characterChecker);
                }
                if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            }
            
            //check cell by cell, oh boy
            String gridOne, gridTwo, gridThree, gridFour, gridFive, gridSix, gridSeven, gridEight, gridNine = new String();
            gridOne = gridTwo = gridThree = gridFour = gridFive = gridSix = gridSeven = gridEight = gridNine = "";
            
            for(int i = 0; i < txtFields.length; i++)
            {
                // top left
                if((i < 3) || (i > 8 && i < 12) || (i > 17 && i < 21))
                {
                    gridOne = gridOne + txtFields[i].getText();
                }
            
                //top middle
                else if((i > 2 && i < 6) || (i > 11 && i < 15) || (i > 20 && i < 24))
                {
                    gridTwo = gridTwo + txtFields[i].getText();
                }
            
                //top right
                else if((i > 5 && i < 9) || (i > 14 && i < 18) || (i > 23 && i < 27))
                {
                    gridThree = gridThree + txtFields[i].getText();
                }
            
                //top grids complete
            
                //middle left
                else if((i > 26 && i < 30) || (i > 35 && i < 39) || (i > 44 && i < 48))
                {
                    gridFour = gridFour + txtFields[i].getText();
                }
            
                //center
                else if((i > 29 && i < 33)|| (i > 38 && i < 42) || (i > 47 && i < 51))
                {
                    gridFive = gridFive + txtFields[i].getText();
                }
            
                //middle right
                else if((i > 32  && i < 36) || (i > 41 && i < 45) || (i > 50 && i < 54))
                {
                    gridSix = gridSix + txtFields[i].getText();
                }
            
                //middle grids complete
            
                //bottom left
                else if((i > 53  && i < 57) || (i > 62 && i < 66) || (i > 71 && i < 75))
                {
                    gridSeven = gridSeven + txtFields[i].getText();
                }
            
                //bottom middle
                else if((i > 56  && i < 60) || (i > 65 && i < 69) || (i > 74 && i < 78))
                {
                    gridEight = gridEight + txtFields[i].getText();
                }
            
                //bottom right
                else if((i > 59  && i < 63) || (i > 68 && i < 72) || (i > 77 && i < 81))
                {
                    gridNine = gridNine + txtFields[i].getText();
                }
            
                // bottom grids done
            }
            
            if(gridOne.length() == 9)
            {
                characterChecker = gridOne.toCharArray();
                Arrays.sort(characterChecker);
            }
            
            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridTwo.length() == 9)
            {
                characterChecker = gridTwo.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridThree.length() == 9)
            {
                characterChecker = gridThree.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridFour.length() == 9)
            {
                characterChecker = gridFour.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridFive.length() == 9)
            {
                characterChecker = gridFive.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridSix.length() == 9)
            {
                characterChecker = gridSix.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridSeven.length() == 9)
            {
                characterChecker = gridSeven.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridEight.length() == 9)
            {
                characterChecker = gridEight.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
                {
                    isCorrect = false;
                }
            
            if(gridNine.length() == 9)
            {
                characterChecker = gridNine.toCharArray();
                Arrays.sort(characterChecker);
            }

            if(!Arrays.equals(checkAgainst, characterChecker))
            {
                isCorrect = false;
            }
            
            tries--;
            if(isCorrect == false)
            {
                //TODO - if timesClicked == 3, main menu
                if(tries > 0)
                {
                    triesLeft.setText("Tries Remaining: " + Integer.toString(tries));
                }
                else if(tries <= 0)
                {
                    submitButton.setText("Main Menu");
                    triesLeft.setText("You lost! Click main menu to return to the main menu.");
                    submitButton.setOnAction(new EventHandler<ActionEvent>()
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
            }
            else if(isCorrect == true)
            {
                triesLeft.setText("You won! Click next to move on.");
                submitButton.setText("Next");
                submitButton.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        EscapeRoom_PokerController poker = new EscapeRoom_PokerController();
                            
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PokerHands/EscapeRoom_Poker.fxml"));
        
                        fxmlLoader.setController(poker);
        
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
        }
    });
    }// end of initialize 
    
    //modified Julie Schneider's readfile code
    private ArrayList<String> readFile()
    {
        ArrayList<String> sudokuPuzzles = new ArrayList<String>();
        File file;
        FileReader fileReader;
        BufferedReader bufferedReader;
        String record;
        
        try
        {
            file = new File("p096_sudoku.txt");
            fileReader = new FileReader(file); // throw FileNotFoundException
            bufferedReader = new BufferedReader(fileReader);
            
            while((record = bufferedReader.readLine()) != null)
            {   
                //ignore first line "Grid #"
                record = bufferedReader.readLine();
                
                for(int i = 0; i < 8; i++)
                {
                    record = record + bufferedReader.readLine(); 
                }
                
                sudokuPuzzles.add(record);
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
        
        return sudokuPuzzles;
    } // end of readfile

    /**
     *
     * @param input
     */
    public void setupBoard(ArrayList<String> input)
    {
        //use a random board
        Random rand = new Random();
        
        int board = 0;
        board = rand.nextInt(50);
        
        //testing
        //System.out.println("The board picked is #" + (board+1));
        
        String tempstr = new String();
        String tempstrTwo = new String();
        tempstr = input.get(board);
        
        
        // break board into the text field array
        for(int i = 0; i < txtFields.length; i++)
        {
            tempstrTwo = tempstr.substring((i), (i + 1));
            //System.out.println(tempstrTwo);
            txtFields[i] = new TextField();
            txtFields[i].setMaxWidth(45);
            txtFields[i].setMinWidth(45);
            if("0".equals(tempstrTwo))
            {
                tempstrTwo = "";
            }
            txtFields[i].setText(tempstrTwo);
            
            //don't let the player edit the set numbers
            if(!"".equals(tempstrTwo))
            {
                txtFields[i].setEditable(false);
                txtFields[i].setStyle("-fx-font-weight: bold;");
            }
        }
        
        
        int col = 1;
        int row = 1;
        int masterCounter = 0;
        
        // surely there's a better way...
        for(int i = 0; i < txtFields.length; i++)
        {
            //in the individual grids, column never exceeds 3
            if(col > 3)
            {
                col = 1;
            }
            
            //after completing a line of the puzzle, increment the row for the current grids being worked on
            if(masterCounter == 9)
            {
                row++;
                masterCounter = 0;
            }
            
            //in the individual grids, row never exceeds 3
            if(row > 3)
            {
                row = 1;
            }
            
            // top left
            if((i < 3) || (i > 8 && i < 12) || (i > 17 && i < 21))
            {
                gridFive.add(txtFields[i], col, row);
            }
            
            //top middle
            else if((i > 2 && i < 6) || (i > 11 && i < 15) || (i > 20 && i < 24))
            {
                gridFour.add(txtFields[i], col, row);
            }
            
            //top right
            else if((i > 5 && i < 9) || (i > 14 && i < 18) || (i > 23 && i < 27))
            {
                gridThree.add(txtFields[i], col, row);
            }
            
            //top grids complete
            
            //middle left
            else if((i > 26 && i < 30) || (i > 35 && i < 39) || (i > 44 && i < 48))
            {
                gridSix.add(txtFields[i], col, row);
            }
            
            //center
            else if((i > 29 && i < 33)|| (i > 38 && i < 42) || (i > 47 && i < 51))
            {
                gridOne.add(txtFields[i], col, row);
            }
            
            //middle right
            else if((i > 32  && i < 36) || (i > 41 && i < 45) || (i > 50 && i < 54))
            {
                gridTwo.add(txtFields[i], col, row);
            }
            
            //middle grids complete
            
            //bottom left
            else if((i > 53  && i < 57) || (i > 62 && i < 66) || (i > 71 && i < 75))
            {
                gridSeven.add(txtFields[i], col, row);
            }
            
            //bottom middle
            else if((i > 56  && i < 60) || (i > 65 && i < 69) || (i > 74 && i < 78))
            {
                gridEight.add(txtFields[i], col, row);
            }
            
            //bottom right
            else if((i > 59  && i < 63) || (i > 68 && i < 72) || (i > 77 && i < 81))
            {
                gridNine.add(txtFields[i], col, row);
            }
            
            // bottom grids done
            
            //column counter
            col++;
            //counter for lines completed
            masterCounter++;
        }
    }
}
