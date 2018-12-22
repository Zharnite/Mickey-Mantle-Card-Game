/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhppan_final_project;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
public class test2 extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    TextField txtCharacter;
    TextField txtActor;
    @Override public void start(Stage primaryStage)
    {
        // Create the Character label
        Label lblCharacter = new Label("Character's Name:");
        lblCharacter.setMinWidth(100);
        lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);
        // Create the Character text field
        txtCharacter = new TextField();
        txtCharacter.setMinWidth(200);
        txtCharacter.setMaxWidth(200);
        txtCharacter.setPromptText("Enter the name of the character here.");
        // Create the Actor label
        Label lblActor = new Label("Actor's Name:");
        lblActor.setMinWidth(100);
        lblActor.setAlignment(Pos.BOTTOM_RIGHT);
        // Create the Actor text field
        txtActor = new TextField();
        txtActor.setMinWidth(200);
        txtActor.setMaxWidth(200);
        txtActor.setPromptText("Enter the name of the actor here.");
        // Create the Role labels
        Label lblRole1 = new Label("The role of ");
        Label lblRole2 = new Label();
        Label lblRole3 = new Label(" will be played by ");
        Label lblRole4 = new Label();
        // Create the Character pane
        HBox paneCharacter = new HBox(20, lblCharacter, txtCharacter);
        paneCharacter.setPadding(new Insets(10));
        // Create the Actor pane
        HBox paneActor = new HBox(20, lblActor, txtActor);
        paneActor.setPadding(new Insets(10));
        // Create the Role pane
        HBox paneRole = new HBox(lblRole1, lblRole2, lblRole3, lblRole4);
        paneRole.setPadding(new Insets(10));
        // Add the Character and Actor panes to a VBox
        VBox pane = new VBox(10, paneCharacter, paneActor, paneRole);
        // Create the bindings
        lblRole2.textProperty().bind(txtCharacter.textProperty());
        lblRole4.textProperty().bind(txtActor.textProperty());
        // Set the stage
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Role Player");
        primaryStage.show();    }
}

/*

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test2 extends Application {

    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("My First JavaFX GUI");

//Scene 1
        Label label1 = new Label("This is the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> primaryStage.setScene(scene2));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 300, 250);

//Scene 2
        Label label2 = new Label("This is the second scene");
        Button button2 = new Button("Go to scene 1");
        button2.setOnAction(e -> primaryStage.setScene(scene1));
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, 300, 250);

        primaryStage.setScene(scene1);
        primaryStage.show();
        
        Stage stage = new Stage();
        Button button3 = new Button("This is scene 3.");
        Scene scene3 = new Scene(button3);
        stage.setTitle("Second Stage");
        stage.setScene(scene3);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
*/