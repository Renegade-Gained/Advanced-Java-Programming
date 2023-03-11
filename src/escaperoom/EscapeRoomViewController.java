/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escaperoom;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import textFlow.textViewController;

/**
 *
 * @author saint
 */
public class EscapeRoomViewController implements Initializable 
{
    
    @FXML
    private Button startBtn, quitBtn;
    
    @FXML
    private Label title;
    
    @FXML
    private BorderPane master;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        title.setTextFill(Color.web("ffffff", .8));
        
        BackgroundImage myBI;
        myBI = new BackgroundImage(new Image("file:images/alley.jpg",300,300,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        master.setBackground(new Background(myBI));
        
        startBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                textViewController intro = new textViewController();
        
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/textFlow/textView.fxml"));
        
                fxmlLoader.setController(intro);
        
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
        
        quitBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Platform.exit();
                System.exit(0);
            }
        });
    }    
    
}
