/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textFlow;

import Sudoku.SudokuViewController;
import escaperoom.EscapeRoom;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 *
 * @author Samuel Sommerschield
 */
public class textViewController implements Initializable 
{
    
    @FXML
    private TextArea info;
    
    @FXML
    private Button nextBtn;
    
    @FXML
    private GridPane stuffHolder;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        BackgroundImage myBI;
        myBI = new BackgroundImage(new Image("file:images/dudeWgun.jpg",300,300,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        //then you set to your node
        stuffHolder.setBackground(new Background(myBI));

        info.setText("You're headed to your favorite casino tonight to play your "
                + "favorite games. \nIt's been a rough week and you need to relieve some stress. "
                + "You see a man \n with a gun hanging outside the entrance. It's not to abnormal, but you've never \n seen him before. "
                + "You continue in and head to your favorite room with your favorite \n game. Upon entering, you hear the door lock behind you. "
                + "The owner comes on \n the intercom. 'Ladies and gents, I've grown tired of watching you play for money. \n"
                + "Tonight you play for your lives! In front of you you should find a randomly selected \n game and you must win to exit your room. "
                + "If you fail to do so, you will be electrified \n until you die. Should you win, you must play poker \nwith the androids at the poker table before I'll let you out. "
                + "I look forward to\n an entertaining show!'"
                + "\nYou look down to see a game of sudoku...");
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                SudokuViewController sudoku = new SudokuViewController();
        
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Sudoku/SudokuView.fxml"));
        
                fxmlLoader.setController(sudoku);
        
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
