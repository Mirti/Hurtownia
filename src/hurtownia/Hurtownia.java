/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hurtownia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the program. Is is response for starting program
 *
 */
public class Hurtownia extends Application {
    
    /**
     * Starts program with LoginWindow class
     * 
     * @param stage - Current stage(window) of the program
     * @throws Exception - Throw exception in case of can't open LoginWindow.fxml
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));

        Scene scene = new Scene(root);
      
        stage.setTitle("Hurtownia spożywcza v.0.1");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *Main method of this application
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
