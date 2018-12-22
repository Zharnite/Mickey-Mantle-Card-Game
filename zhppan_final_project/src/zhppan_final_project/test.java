/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhppan_final_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author zheny
 */
public class test extends Application {

    public void start(Stage primaryStage) {
//        Pane pane = new Pane();
//        Button aBtn = new Button("Button Name");
//        pane.getChildren().add(aBtn);
//        Scene scene = new Scene(pane, 100, 150);
//        primaryStage.setTitle("FXTest");
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    public static void main(String[] args) {
        
        
        //newGame() test
        Player[] players = new Player[4];
        players[0] = new Player("Regolith");
        players[1] = new Player("Geotherm");
        players[2] = new Player("Solidus");
        players[3] = new Player("Liquidus");
        int maxHand = 13;
        
        Board board = new Board(players, maxHand);
        System.out.println(board);
        
        board.newGame();
        board.updateStats(players[0]);
        System.out.println(board);
        board.newGame();
        board.updateStats(players[0]);
        System.out.println(board);
        board.newGame();
        board.updateStats(players[1]);
        System.out.println(board);
        board.newGame();
        board.updateStats(players[1]);
        System.out.println(board);
        
        try {
            board.saveBoard();
        }
        catch(FileNotFoundException fnf) {
            System.out.println("File not Found Crashes");
            System.exit(0);
        }
        
        try {
            board.loadBoard();
        }
        catch(FileNotFoundException fnf) { //make up fake players for them and use that instead of assumption of created characters
            System.out.println("File not Found Crashes");
            System.exit(0);
        }
        
        System.out.println(board);
        
        System.exit(0);
        
        
        /*
        Done, Tested, and Worked code
        
        
        //Test to see if methods work
        Player[] players = new Player[4];
        players[0] = new Player("Zharnite");
        players[1] = new Player("Felix");
        players[2] = new Player("Rhelia");
        players[3] = new Player("Aphelion");
        int maxHand = 13;
        
        Board board = new Board(players, maxHand);
        System.out.println(board);
        
        Card c1 = new Card("spades", "3", 3);
        Card c2 = new Card("spades", "4", 4);
        Card c3 = new Card("hearts", "4", 4);
        Card c4 = new Card("diamonds", "7", 7);
        Card c5 = new Card("spades", "5", 5);
        
        board.playAreaAdd(c1);
        board.playAreaAdd(c2);
        board.playAreaAdd(c3);
        board.playAreaAdd(c4);
        
        for(int i = 0; i < players.length; i++) {
            boolean check = players[i].hasMove(board.playArea);
            System.out.println(check);
            if(check) {
                Card selected = players[i].cheat(board.playArea);
                System.out.println(selected);
            }
        }
        
        
        
        //System.out.println(board.isLegal(c2));
        //System.out.println(board.isLegal(c3));
        //System.out.println(board.isLegal(c4));
        System.out.println(board.isLegal(c5));
        
        System.out.println(board);
        board.playAreaSort();
        System.out.println("playAreaSort done");
        System.out.println(board);
        
        
        */
    }
}
