/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* *
 * Stuff To Do
 * so them after every button press, it can be called and the application can be updated
 * remember to remove the old stuff before updating to make a new array
 */
package zhppan_final_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author zheny
 */
public class CardGameGUI extends Application {

    //Declaring: Images and ImageView ArrayList
    final String FILE_PROTOCOL = "file:";
    final String IMAGES_PATH = "./images/";
    List<Image> cardImages = new ArrayList<Image>(); //first image is back of card, i might change that to end of deck if i need to make an easier formula
    List<ImageView> cardIV = new ArrayList<ImageView>();
    List<Image> bocImages = new ArrayList<Image>();
    List<ImageView> backOfCardIV = new ArrayList<ImageView>();

    //Declaring and Initializing: Top Bar Buttons
    Button newGameBtn = new Button("New Game");
    Button cardSelectionBtn = new Button("Random Card Selection");
    Button saveBtn = new Button("Save");
    Button exitBtn = new Button("Exit");

    //Declaring and Initializing: Hand Buttons
    List<Button> handBtns = new ArrayList<Button>();
    FlowPane handBox = new FlowPane();

    //Declaring and Initializing: CheckBox
    CheckBox cheatCB = new CheckBox("Cheat");

    //Declaring and Initializing: Panes 
    VBox container = new VBox();
    HBox toolbar = new HBox();
    HBox infoBar = new HBox();
    BorderPane gamePane = new BorderPane();

    //Declaring and Initializing: Panes in gamePane
    VBox centerSP = new VBox();
    StackPane topSP = new StackPane();
    StackPane rightSP = new StackPane();
    StackPane leftSP = new StackPane();

    //Declaring and Initializing: StackPanes in the CENTER gamePane
    StackPane diamondSP = new StackPane();
    StackPane clubSP = new StackPane();
    StackPane heartSP = new StackPane();
    StackPane spadeSP = new StackPane();
    List<Integer> diamondSPIndex = new ArrayList<Integer>();
    List<Integer> clubSPIndex = new ArrayList<Integer>();
    List<Integer> heartSPIndex = new ArrayList<Integer>();
    List<Integer> spadeSPIndex = new ArrayList<Integer>();

    //Labels
    Label infoLabel;
    Label turnLabel;
    
    HBox nameBox = new HBox();
    Label p0Label = new Label();
    Label p1Label = new Label();
    Label p2Label = new Label();
    Label p3Label = new Label();
    

    //Declaring and Initializing: Scenes 
    Scene scene = new Scene(container);

    //Declaring: Stage
    Stage stage;

    //Non GUI variable
    Board board;
    Deck newDeck = new Deck();
    List<Card> compareDeck = newDeck.getCards();
    List<Integer> handP0Index = new ArrayList<Integer>();
    List<Integer> handP0Index2 = new ArrayList<Integer>();
    List<Integer> handP1Index = new ArrayList<Integer>();
    List<Integer> handP2Index = new ArrayList<Integer>();
    List<Integer> handP3Index = new ArrayList<Integer>();
    Player[] players = new Player[4];
    //int turn = board.getTurn();

    @Override
    public void start(Stage primaryStage) {
        //Making the players if there is not a saved file
        players[0] = new Player("You", true);
        players[1] = new Player("Player_One_Left");
        players[2] = new Player("Player_Two_Top");
        players[3] = new Player("Player_Three_Right");
        int maxHand = 13;
        board = new Board(players, maxHand);

        File check = new File("stats.txt");
        if (check.exists()) {
            try {
                board.loadBoard();
            } catch (FileNotFoundException ex) {
                System.out.println("Failed load");
            }
        } else {
            try {
                board.saveBoard();
            } catch (FileNotFoundException ex) {
                System.out.println("Initial save failed");
            }
        }

        //Making a new game
        board.newGame();

        //Configuratoins: Content
        //Subcategory: Toolbar
        toolbar.getChildren().add(cheatCB);
        toolbar.getChildren().add(newGameBtn);
        toolbar.getChildren().add(cardSelectionBtn); //add checkbox somewhere here or down next to info bar
        toolbar.getChildren().add(saveBtn);
        toolbar.getChildren().add(exitBtn);

        //Subcategory: Infobar
        Player thePlayer = board.getPlayer(0);
        String info = "  |  Games Played: " + thePlayer.getNumPlays() + "  |  "
                + "Games Won: " + thePlayer.getNumWins() + "  |  "
                + "Games Lost: " + thePlayer.getNumLoses() + "  |  "
                + "Win Pecentage: " + thePlayer.getWinPercentage() + "  |  ";
        infoLabel = new Label(info);
        infoBar.getChildren().add(infoLabel);

        int turnNum = board.getTurn();
        String turnStr = "Turn Number: " + turnNum + "  |  ";
        turnLabel = new Label(turnStr);
        infoBar.getChildren().add(turnLabel);

        initImages();
        initIV();
        initBOCImages();
        initBOCIV();
        sortIV();
        initToolbarBtns();

        handP0Index = getCardIndexes(players[0].getHand());
        handP0Index2 = getCardIndexes(players[0].getHand());
        handP1Index = getCardIndexes(players[1].getHand());
        handP2Index = getCardIndexes(players[2].getHand());
        handP3Index = getCardIndexes(players[3].getHand());
        //=============================

        //Subcategory: Game Area
        initHandBtns();
        initHandBtnActions();
        initHandBox();
        disableBtns();
        
        String s = "";
        s = "                                      ";
        Label space1 = new Label(s);
        s = "                                                                                                                    ";
        Label space2 = new Label(s);
        s = players[1].getName();
        p1Label.setText(s);
        s = players[2].getName();
        p2Label.setText(s);
        s = players[3].getName();
        p3Label.setText(s);
        nameBox.getChildren().add(p1Label);
        nameBox.getChildren().add(space1);
        nameBox.getChildren().add(p2Label);
        nameBox.getChildren().add(space2);
        nameBox.getChildren().add(p3Label);

        //=============================
        //diamondSP.setPrefHeight(100);
        //clubSP.setPrefHeight(100);
        //heartSP.setPrefHeight(100);
        //spadeSP.setPrefHeight(100);
        //Position of initial cards
        if (!cheatCB.isSelected()) {
            AIBackOfCards();
        }
        else {
            leftSP = updateSP(handP1Index, 0, 30, 0, 0);
            topSP = updateSP(handP2Index, 30, 0, -200, 0);
            rightSP = updateSP(handP3Index, 0, 30, 0, 0);
        }
        

        leftSP.setPrefHeight(700);
        leftSP.setAlignment(TOP_CENTER);
        rightSP.setPrefHeight(700);
        rightSP.setAlignment(TOP_CENTER);

        centerSP.getChildren().add(diamondSP);
        centerSP.getChildren().add(clubSP);
        centerSP.getChildren().add(heartSP);
        centerSP.getChildren().add(spadeSP);
        gamePane.setLeft(leftSP);
        gamePane.setTop(topSP);
        gamePane.setRight(rightSP);
        gamePane.setBottom(handBox);
        gamePane.setCenter(centerSP);
        container.getChildren().add(toolbar);
        container.getChildren().add(infoBar);
        container.getChildren().add(nameBox);//new
        container.getChildren().add(gamePane);
        stage = primaryStage;
        stage.setWidth(1000);
        stage.setHeight(900);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        nextTurn();
        while (!players[0].hasMove2(board.getPlayArea()) && !board.gameIsWon()) {
            nextTurn();
            nextTurn();
            nextTurn();
            nextTurn();
        }
        nextTurn();

    }

    private void updateDisplay() {
        diamondSPIndex = getCardIndexes(board.dList);
        clubSPIndex = getCardIndexes(board.cList);
        heartSPIndex = getCardIndexes(board.hList);
        spadeSPIndex = getCardIndexes(board.sList);
        updateSP(diamondSP, diamondSPIndex, 30, 0.01, -200, 0);
        updateSP(clubSP, clubSPIndex, 30, 0.01, -200, 0);
        updateSP(heartSP, heartSPIndex, 30, 0.01, -200, 0);
        updateSP(spadeSP, spadeSPIndex, 30, 0.01, -200, 0);

        handP1Index = getCardIndexes(players[1].getHand());
        handP2Index = getCardIndexes(players[2].getHand());
        handP3Index = getCardIndexes(players[3].getHand());
//        updateSP(leftSP, handP1Index, 0, 30, 0, 0);
//        updateSP(topSP, handP2Index, 30, 0, -200, 0);
//        updateSP(rightSP, handP3Index, 0, 30, 0, 0);

        if (!cheatCB.isSelected()) {
            AIBackOfCards();
        }
        else {
            updateSP(leftSP, handP1Index, 0.01, 30, 0.01, 0.01);
            updateSP(topSP, handP2Index, 30, 0.01, -200, 0.01);
            updateSP(rightSP, handP3Index, 0.01, 30, 0.01, 0.01);
        }

        if (!board.gameIsWon()) {
            int turnNum = board.getTurn();
            String turnStr = "Turn Number: " + turnNum + "  |  "
                    + board.determinePlayerTurn().getName() + "'s Turn";
            turnLabel.setText(turnStr);
        }
    }

    private void AIBackOfCards() {
        updateSP(leftSP, handP1Index, 52, 0.01, 30, 0.01, 0.01);
        updateSP(topSP, handP2Index, 52, 30, 0.01, -200, 0.01);
        updateSP(rightSP, handP3Index, 52, 0.01, 30, 0.01, 0.01);
    }

    private void AIMove(Player p) {
        board.incTurn();
        Card c = p.cheat(board.getPlayArea());
        board.playAreaAdd(c);
        addToCorrectList(c);
        p.removeCard(c);
        System.out.println(p.getName() + " played: " + c);

    }

    //use for inithandhelper later
    private void addToCorrectList(Card c) {
        if (c.getSuit().equals("diamonds")) {
            board.dListUpdate(c);
        } else if (c.getSuit().equals("clubs")) {
            board.cListUpdate(c);
        } else if (c.getSuit().equals("hearts")) {
            board.hListUpdate(c);
        } else if (c.getSuit().equals("spades")) {
            board.sListUpdate(c);
        } else {
            System.out.println("Error in addtocorrectlist(), check spelling");
        }
    }

    private void nextTurn() {
        Player p = board.determinePlayerTurn();
        System.out.println(p.getName() + "'s turn");
        if (p.equals(players[0])) {
            if (p.hasMove2(board.getPlayArea())) {
                enableBtns();
            } else {
                board.incTurn();
                System.out.println("Your turn skipped.");
            }
        } else {
            if (p.hasMove(board.getPlayArea())) {
                AIMove(p);
            } else {
                board.incTurn();
                System.out.println(p.getName() + " turn skipped.");
            }
        }

        updateDisplay();

    }

    public void disableBtns() {
        for (int i = 0; i < handBtns.size(); i++) {
            handBtns.get(i).setDisable(true);
        }
    }

    public void enableBtns() {
        for (int i = 0; i < handBtns.size(); i++) {
            handBtns.get(i).setDisable(false);
        }
    }

    private void initToolbarBtns() { //finish this,, update new game as i go
        initNewGameBtn();
        initCardSelectionBtn();
        cheatCB.setOnAction(e -> {
            updateDisplay();
        });
        saveBtn.setOnAction(e -> {
            System.out.println("Saving");
            try {
                board.saveBoard();
            } catch (FileNotFoundException fnf) {
                System.out.println("Error Saving");
            }
            System.out.println("Save Successful?");
        });
        exitBtn.setOnAction(e -> {
            System.out.println("Exiting Board");
            board.exitBoard();
        });
    }

    //Tested and Working Methods
    private void initHandBtns() {
        for (int i = 0; i < handP0Index.size(); i++) {
            Button b = new Button("", cardIV.get(handP0Index.get(i)));
            handBtns.add(b);
        }
    }

    private void initHandBox() {
        for (int i = 0; i < handBtns.size(); i++) {
            handBox.getChildren().addAll(handBtns.get(i));
        }
        handBox.setAlignment(BOTTOM_CENTER);
    }

    //boolean hasMove = players[0].hasMove(board.getPlayArea()); thenlet them click, also if it's the player's turn to move
    private void initHandBtnActions() {
        handBtns.get(0).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(0));
            initHandHelper(0, c);
        });
        handBtns.get(1).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(1));
            initHandHelper(1, c);
        });
        handBtns.get(2).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(2));
            initHandHelper(2, c);
        });
        handBtns.get(3).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(3));
            initHandHelper(3, c);
        });
        handBtns.get(4).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(4));
            initHandHelper(4, c);
        });
        handBtns.get(5).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(5));
            initHandHelper(5, c);
        });
        handBtns.get(6).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(6));
            initHandHelper(6, c);
        });
        handBtns.get(7).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(7));
            initHandHelper(7, c);
        });
        handBtns.get(8).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(8));
            initHandHelper(8, c);
        });
        handBtns.get(9).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(9));
            initHandHelper(9, c);
        });
        handBtns.get(10).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(10));
            initHandHelper(10, c);
        });
        handBtns.get(11).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(11));
            initHandHelper(11, c);
        });
        handBtns.get(12).setOnAction(e -> {
            Card c = compareDeck.get(handP0Index.get(12));
            initHandHelper(12, c);
        });
    }

    private void initCardSelectionBtn() {
        cardSelectionBtn.setOnAction(e -> { // only returns the card
            Player p = board.determinePlayerTurn();
            if (p.equals(players[0]) && !board.gameIsWon()) {
                System.out.println("Selecting Random Card");
                if (players[0].hasMove2(board.getPlayArea())) {
                    Card c = players[0].cheat2(board.getPlayArea());
                    board.incTurn();
                    addToCorrectList(c);
                    System.out.println("You played: " + c);
                    board.playAreaAdd(c);
                    int index = 0; //error prone
                    for (int i = 0; i < handP0Index.size(); i++) {
                        if (compareDeck.get(handP0Index.get(i)).equals(c)) {
                            index = i;
                        }
                    }
                    handBox.getChildren().remove(handBtns.get(index));
                    players[0].removeCard2(c);
                    disableBtns();
                    if (!board.gameIsWon()) {
                        nextTurn();
                    }
                    if (!board.gameIsWon()) {
                        nextTurn();
                    }
                    if (!board.gameIsWon()) {
                        nextTurn();
                    }

                    while (!players[0].hasMove2(board.getPlayArea()) && !board.gameIsWon()) {
                        if (!board.gameIsWon()) {
                            nextTurn();
                        }
                        if (!board.gameIsWon()) {
                            nextTurn();
                        }
                        if (!board.gameIsWon()) {
                            nextTurn();
                        }
                        if (!board.gameIsWon()) {
                            nextTurn();
                        }
                    }
                    if (!board.gameIsWon()) {
                        nextTurn();
                    }

                    if (board.gameIsWon()) {

                        for (int i = 0; i < players.length; i++) {
                            if (board.checkWin(players[i])) {
                                System.out.println(players[i].getName() + " WINS!!");
                                turnLabel.setText(players[i].getName() + " WINS!!");
                                disableBtns();
                                board.updateStats(players[i]);
                                Player thePlayer = board.getPlayer(0);
                                String info = "  |  Games Played: " + thePlayer.getNumPlays() + "  |  "
                                        + "Games Won: " + thePlayer.getNumWins() + "  |  "
                                        + "Games Lost: " + thePlayer.getNumLoses() + "  |  "
                                        + "Win Pecentage: " + thePlayer.getWinPercentage() + "  |  ";
                                infoLabel.setText(info);
                                updateDisplay();
                                try {
                                    board.saveBoard();
                                } catch (FileNotFoundException ex) {
                                    System.out.println("Error saving in cardgamegui initHandHelper");
                                }
                            }
                        }
                        updateDisplay();
                    }

                } else {
                    System.out.println("Turn should have been skipped already");
                }
            } else {
                System.out.println("Not Valid");
            }
        });
    }

    private void initHandHelper(int index, Card c) { //change update
        boolean isLegal = board.isLegal(c);
        String suit = c.getSuit();
        if (isLegal) {
            board.incTurn();
            addToCorrectList(c);
            updateDisplay();
            System.out.println("You played: " + c);
            board.playAreaAdd(c); //
            handBox.getChildren().remove(handBtns.get(index));
            players[0].removeCard2(c);//useful?

            disableBtns();
            if (!board.gameIsWon()) {
                nextTurn();
            }
            if (!board.gameIsWon()) {
                nextTurn();
            }
            if (!board.gameIsWon()) {
                nextTurn();
            }

            while (!players[0].hasMove2(board.getPlayArea()) && !board.gameIsWon()) {
                if (!board.gameIsWon()) {
                    nextTurn();
                }
                if (!board.gameIsWon()) {
                    nextTurn();
                }
                if (!board.gameIsWon()) {
                    nextTurn();
                }
                if (!board.gameIsWon()) {
                    nextTurn();
                }
            }
            if (!board.gameIsWon()) {
                nextTurn();
            }

            for (int i = 0; i < players.length; i++) {
                if (board.checkWin(players[i])) {
                    System.out.println(players[i].getName() + " WINS!!");
                    turnLabel.setText(players[i].getName() + " WINS!!");
                    disableBtns();
                    board.updateStats(players[i]);
                    Player thePlayer = board.getPlayer(0);
                    String info = "  |  Games Played: " + thePlayer.getNumPlays() + "  |  "
                            + "Games Won: " + thePlayer.getNumWins() + "  |  "
                            + "Games Lost: " + thePlayer.getNumLoses() + "  |  "
                            + "Win Pecentage: " + thePlayer.getWinPercentage() + "  |  ";
                    infoLabel.setText(info);
                    updateDisplay();
                    try {
                        board.saveBoard();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error saving in cardgamegui initHandHelper");
                    }
                }
            }
            updateDisplay();
        } else {
            System.out.println("Invalid Move");
        }
    }

    //New game button in another method because of long length
    private void initNewGameBtn() {
        newGameBtn.setOnAction(e -> { //havent checked the buttons yet
            board.newGame(); //update the score(maybe not), the hand, the playarea, buttons

            handBtns = new ArrayList<Button>();
            handBox = new FlowPane();
            cardImages = new ArrayList<Image>();
            cardIV = new ArrayList<ImageView>();
            initImages();
            initIV();
            sortIV();
//            initBOCImages();
//            initBOCIV();

            leftSP.getChildren().clear();
            topSP.getChildren().clear();
            rightSP.getChildren().clear();
            diamondSP.getChildren().clear();
            clubSP.getChildren().clear();
            heartSP.getChildren().clear();
            spadeSP.getChildren().clear();
            infoBar.getChildren().clear();
//            centerSP.getChildren().clear();
//            gamePane.getChildren().clear();

            handP0Index = getCardIndexes(players[0].getHand());
            handP0Index2 = getCardIndexes(players[0].getHand());
            handP1Index = getCardIndexes(players[1].getHand());
            handP2Index = getCardIndexes(players[2].getHand());
            handP3Index = getCardIndexes(players[3].getHand());
            if(!cheatCB.isSelected()) {
                AIBackOfCards();
            }
            else {
                leftSP = updateSP(handP1Index, 0.01, 30, 0.01, 0.01);
                topSP = updateSP(handP2Index, 30, 0.01, -200, 0.01);
                rightSP = updateSP(handP3Index, 0.01, 30, 0.01, 0.01);
            }
            
//            updateSP(leftSP, handP1Index, 0, 30, 0, 0);
//            updateSP(topSP, handP2Index, 30, 0, -200, 0);
//            updateSP(rightSP, handP3Index, 0, 30, 0, 0);
            Player thePlayer = board.getPlayer(0);
            String info = "  |  Games Played: " + thePlayer.getNumPlays() + "  |  "
                    + "Games Won: " + thePlayer.getNumWins() + "  |  "
                    + "Games Lost: " + thePlayer.getNumLoses() + "  |  "
                    + "Win Pecentage: " + thePlayer.getWinPercentage() + "  |  ";
            Label infoLabel = new Label(info);
            infoBar.getChildren().add(infoLabel);

            int turnNum = board.getTurn();
            String turnStr = "Turn Number: " + turnNum + "  |  ";
            turnLabel = new Label(turnStr);
            infoBar.getChildren().add(turnLabel);

            diamondSPIndex = getCardIndexes(board.dList);
            clubSPIndex = getCardIndexes(board.cList);
            heartSPIndex = getCardIndexes(board.hList);
            spadeSPIndex = getCardIndexes(board.sList);
            diamondSP = updateSP(diamondSPIndex, 30, 0, -200, 0);
            clubSP = updateSP(clubSPIndex, 30, 0, -200, 0);
            heartSP = updateSP(heartSPIndex, 30, 0, -200, 0);
            spadeSP = updateSP(spadeSPIndex, 30, 0, -200, 0);

            leftSP.setPrefHeight(700);
            leftSP.setAlignment(TOP_CENTER);
            rightSP.setPrefHeight(700);
            rightSP.setAlignment(TOP_CENTER);

            centerSP.getChildren().add(diamondSP);
            centerSP.getChildren().add(clubSP);
            centerSP.getChildren().add(heartSP);
            centerSP.getChildren().add(spadeSP);
            gamePane.setLeft(leftSP);
            gamePane.setTop(topSP);
            gamePane.setRight(rightSP);
            gamePane.setBottom(handBox);
            gamePane.setCenter(centerSP);

            initHandBtns();
            initHandBtnActions();
            initHandBox();
            disableBtns();

            stage.setScene(scene);

            System.out.println("Made New Game");

            nextTurn();
            while (!players[0].hasMove2(board.getPlayArea()) && !board.gameIsWon()) {
                nextTurn();
                nextTurn();
                nextTurn();
                nextTurn();
            }
            nextTurn();
        });
    }

    //Returns an updated StackPane with inputed indexes
    private StackPane updateSP(List<Integer> cardIndex, double x, double y, double offsetX, double offsetY) {
        StackPane sp = new StackPane();
        for (int i = 0; i < cardIndex.size(); i++) {
            sp.getChildren().add(cardIV.get(cardIndex.get(i)));
            if (x != 0) {
                cardIV.get(cardIndex.get(i)).setTranslateX(offsetX + (i * x));
            }
            if (y != 0) {
                cardIV.get(cardIndex.get(i)).setTranslateY(offsetY + (i * y));
            }
        }
        return sp;
    }

    private void updateSP(StackPane sp, List<Integer> cardIndex, int x, int y, int offsetX, int offsetY) {
        sp.getChildren().clear();
        for (int i = 0; i < cardIndex.size(); i++) {
            sp.getChildren().add(cardIV.get(cardIndex.get(i)));
            if (x != 0) {
                cardIV.get(cardIndex.get(i)).setTranslateX(offsetX + (i * x));
            }
            if (y != 0) {
                cardIV.get(cardIndex.get(i)).setTranslateY(offsetY + (i * y));
            }
        }
    }
    
    private void updateSP(StackPane sp, List<Integer> cardIndex, int n, double x, double y, double offsetX, double offsetY) {
        sp.getChildren().clear();
        for (int i = 0; i < cardIndex.size(); i++) {
            sp.getChildren().add(backOfCardIV.get(cardIndex.get(i)));
            if (x != 0) {
                backOfCardIV.get(cardIndex.get(i)).setTranslateX(offsetX + (i * x));
            }
            if (y != 0) {
                backOfCardIV.get(cardIndex.get(i)).setTranslateY(offsetY + (i * y));
            }
        }
    }
    
    private void updateSP(StackPane sp, List<Integer> cardIndex, double x, double y, double offsetX, double offsetY) {
        sp.getChildren().clear();
        for (int i = 0; i < cardIndex.size(); i++) {
            sp.getChildren().add(cardIV.get(cardIndex.get(i)));
            if (x != 0) {
                cardIV.get(cardIndex.get(i)).setTranslateX(offsetX + (i * x));
            }
            if (y != 0) {
                cardIV.get(cardIndex.get(i)).setTranslateY(offsetY + (i * y));
            }
        }
    }

    //Given a card array, returns the indexes of the cards to use for ImageViews
    private List<Integer> getCardIndexes(List<Card> cardArr) {
        List<Integer> indexes = new ArrayList<Integer>();
        //List<Card> handP0 = players[0].getHand();
        for (int i = 0; i < cardArr.size(); i++) { //see if this works
            for (int j = 0; j < compareDeck.size(); j++) {
                if (cardArr.get(i).equals(compareDeck.get(j))) {//make get card method for 
                    indexes.add(j);
                }
            }
        }
        return indexes;
    }

    //Another version for singular cards
    private int getCardIndexes(Card c) {
        for (int i = 0; i < compareDeck.size(); i++) {
            if (c.equals(compareDeck.get(i))) {
                return i;
            }
        }
        return 52;
    }

    //Sorts cardIV in correct order for comparision, Back of Card is the last image ImageView
    private void sortIV() {
        List<ImageView> tempIV = new ArrayList<ImageView>();
        ImageView backCard = cardIV.remove(0);

        for (int i = 0; i < 4; i++) {
            for (int j = i; j < cardIV.size(); j += 4) {
                tempIV.add(cardIV.get(j));
            }
        }
        tempIV.add(backCard);
        cardIV = tempIV;
    }

    //Makes and Adds ImageView objects in List<ImageView> cardIV
    private void initIV() {
        for (int i = 0; i < cardImages.size(); i++) {
            Image img = cardImages.get(i);
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(80);
            imgView.setPreserveRatio(true);
            cardIV.add(imgView);
        }
    }
    
    private void initBOCIV() {
        for (int i = 0; i < bocImages.size(); i++) {
            Image img = bocImages.get(i);
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(80);
            imgView.setPreserveRatio(true);
            backOfCardIV.add(imgView);
        }
    }
    
    private void initBOCImages() {
        for(int i = 0; i < 53; i++) {
            String s = FILE_PROTOCOL + IMAGES_PATH + "Back_Of_Card.png";
            Image img = loadImage(s);
            bocImages.add(img);
        }
    }

    //Adds Image objects in List<Image> cardImages, Helper method of initIV()
    private void initImages() { //init in similar order to cardsort
        String s = FILE_PROTOCOL + IMAGES_PATH + "Back_Of_Card.png";
        Image img = loadImage(s);
        cardImages.add(img);
        String suit = "";

        for (int i = 1; i < 14; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    suit = "diamond";
                } else if (j == 1) {
                    suit = "club";
                } else if (j == 2) {
                    suit = "heart";
                } else if (j == 3) {
                    suit = "spade";
                }

                if (i == 1) {
                    s = FILE_PROTOCOL + IMAGES_PATH + "Playing_card_" + suit + "_A.jpg";
                } else if (i == 11) {
                    s = FILE_PROTOCOL + IMAGES_PATH + "Playing_card_" + suit + "_J.jpg";
                } else if (i == 12) {
                    s = FILE_PROTOCOL + IMAGES_PATH + "Playing_card_" + suit + "_Q.jpg";
                } else if (i == 13) {
                    s = FILE_PROTOCOL + IMAGES_PATH + "Playing_card_" + suit + "_K.jpg";
                } else {
                    s = FILE_PROTOCOL + IMAGES_PATH + "Playing_card_" + suit + "_" + i + ".jpg";
                }
                img = loadImage(s);
                cardImages.add(img);
            }
        }
    }

    //Helper method in initImages()
    private Image loadImage(String imageFileURL) {
        Image image = new Image(imageFileURL);
        if (!image.isError()) {
            return image;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
