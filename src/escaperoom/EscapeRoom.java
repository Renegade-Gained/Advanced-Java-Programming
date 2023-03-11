/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escaperoom;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 *
 * @author Samuel Sommerschield
 */
public class EscapeRoom extends Application 
{
       public static Stage mainStage;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        EscapeRoom.mainStage = stage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EscapeRoomView.fxml"));
        
        fxmlLoader.setController(new EscapeRoomViewController());
        
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
