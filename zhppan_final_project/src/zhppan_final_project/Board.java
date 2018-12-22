/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhppan_final_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author zheny
 */
public class Board {

    protected Player[] players;
    protected int maxHandSize;
    protected List<Card> playArea;
    protected Deck deck;
    protected int turn;

    List<Card> dList = new ArrayList<Card>(); //diamonds
    List<Card> cList = new ArrayList<Card>(); //clubs
    List<Card> hList = new ArrayList<Card>(); //hearts
    List<Card> sList = new ArrayList<Card>(); //spades
    //gotta check player move as well
    //need to remove player card once cheat method has run
    //need to make sure hasmove is true before running cheat
    //how will moves happen in the gui?
    //is legal should also consider turn num

    public Board(Player[] players, int maxHandSize) {
        turn = 0;
        this.players = players;
        this.maxHandSize = maxHandSize;
        playArea = new ArrayList<Card>();
        dList = new ArrayList<Card>();
        cList = new ArrayList<Card>();
        hList = new ArrayList<Card>();
        sList = new ArrayList<Card>();
        deck = new Deck();
        deck.shuffle();
        dealAllCards();
    }

    public void newGame() {
        for (Player p : players) {
            p.clearHand();
        }
        turn = 0;
        playArea = new ArrayList<Card>();
        dList = new ArrayList<Card>();
        cList = new ArrayList<Card>();
        hList = new ArrayList<Card>();
        sList = new ArrayList<Card>();
        deck = new Deck();
        deck.shuffle();
        dealAllCards();
        //dealAllCards("SHAM!!");

    }

    //precondition: move is legal and can be played
    public void dListUpdate(Card c) {
        int point = c.getPoint();
        if (point == 7) {
            dList.add(c);
        } else {
            int dListLow = dList.get(0).getPoint();
            int dListHigh = dList.get(dList.size() - 1).getPoint();
            if (point == 1) {
                if (dListLow == 2) {
                    dList.add(0, c);
                } else {
                    dList.add(c);
                }
            } else if (point < dListLow) {
                dList.add(0, c);
            } else if (point > dListHigh) {
                dList.add(c);
            }
        }
    }

    public void cListUpdate(Card c) {
        int point = c.getPoint();
        if (point == 7) {
            cList.add(c);
        } else {
            int cListLow = cList.get(0).getPoint();
            int cListHigh = cList.get(cList.size() - 1).getPoint();
            if (point == 1) {
                if (cListLow == 2) {
                    cList.add(0, c);
                } else {
                    cList.add(c);
                }
            } else if (point < cListLow) {
                cList.add(0, c);
            } else if (point > cListHigh) {
                cList.add(c);
            }
        }
    }

    public void hListUpdate(Card c) {
        int point = c.getPoint();
        if (point == 7) {
            hList.add(c);
        } else {
            int hListLow = hList.get(0).getPoint();
            int hListHigh = hList.get(hList.size() - 1).getPoint();
            if (point == 1) {
                if (hListLow == 2) {
                    hList.add(0, c);
                } else {
                    hList.add(c);
                }
            } else if (point < hListLow) {
                hList.add(0, c);
            } else if (point > hListHigh) {
                hList.add(c);
            }
        }
    }

    public void sListUpdate(Card c) {
        int point = c.getPoint();
        if (point == 7) {
            sList.add(c);
        } else {
            int sListLow = sList.get(0).getPoint();
            int sListHigh = sList.get(sList.size() - 1).getPoint();
            if (point == 1) {
                if (sListLow == 2) {
                    sList.add(0, c);
                } else {
                    sList.add(c);
                }
            } else if (point < sListLow) {
                sList.add(0, c);
            } else if (point > sListHigh) {
                sList.add(c);
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int n) {
        return players[n];
    }

    public int getMaxHandSize() {
        return maxHandSize;
    }

    public List<Card> getCard() {
        return playArea;
    }

    public List<Card> getPlayArea() {
        return playArea;
    }

    public void playAreaAdd(Card c) {//check isLegal before adding
        playArea.add(c);
    }

    public void playAreaSort() {//todo
        List<Card> dArr = new ArrayList<Card>(); //diamonds
        List<Card> cArr = new ArrayList<Card>(); //clubs
        List<Card> hArr = new ArrayList<Card>(); //hearts
        List<Card> sArr = new ArrayList<Card>(); //spades

        for (int i = 0; i < playArea.size(); i++) {
            Card current = playArea.get(i);
            if (current.getSuit().equals("diamonds")) {
                dArr.add(current);
            } else if (current.getSuit().equals("clubs")) {
                cArr.add(current);
            } else if (current.getSuit().equals("hearts")) {
                hArr.add(current);
            } else if (current.getSuit().equals("spades")) {
                sArr.add(current);
            } else {
                System.out.println("Error in Board Class, playAreaSort() method. Check spelling of suit.");
            }
        }

        if (dArr.size() > 1) {
            singleSuitSort(dArr);
        }
        if (cArr.size() > 1) {
            singleSuitSort(cArr);
        }
        if (hArr.size() > 1) {
            singleSuitSort(hArr);
        }
        if (sArr.size() > 1) {
            singleSuitSort(sArr);
        }

        int counter = 0;
        for (int i = 0; i < dArr.size(); i++) {
            playArea.set(counter, dArr.get(i));
            counter++;
        }
        for (int i = 0; i < cArr.size(); i++) {
            playArea.set(counter, cArr.get(i));
            counter++;
        }
        for (int i = 0; i < hArr.size(); i++) {
            playArea.set(counter, hArr.get(i));
            counter++;
        }
        for (int i = 0; i < sArr.size(); i++) {
            playArea.set(counter, sArr.get(i));
            counter++;
        }

    }

    private void singleSuitSort(List<Card> suitArr) { //return list or arraylist, if its and alias, then no need to return
        List<Card> newArr = new ArrayList<Card>();
        int suitArrSize = suitArr.size();
        for (int i = 0; i < suitArrSize; i++) {
            Card smallest = suitArr.get(i);
            for (int j = i + 1; j < suitArrSize; j++) {
                Card test = suitArr.get(j);
                if (smallest.getPoint() > test.getPoint()) {
                    smallest = test;
                }
            }
            newArr.add(smallest);
        }
        suitArr = newArr;
    }

    public boolean isLegal(Card c) { //for human player does work
        String playedSuit = c.getSuit();
        int playedPoint = c.getPoint();

        if (playArea.size() == 0 && playedPoint != 7) {
            return false;
        }

        if (playedPoint == 7) {
            return true;
        }

        for (int i = 0; i < playArea.size(); i++) {
            String boardSuit = playArea.get(i).getSuit();
            int boardPoint = playArea.get(i).getPoint();

            if (boardPoint == 1) {
                continue;
            }
            if (playedSuit.equals(boardSuit)) {
                if (playedPoint == 1 && (boardPoint == 2 || boardPoint == 13)) {
                    return true;
                }
                if (playedPoint == (boardPoint + 1) || playedPoint == (boardPoint - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean gameIsWon() {
        for (int i = 0; i < players.length; i++) {
            if (checkWin(players[i])) {
                return true;
            }
        }
        return false;
    }

    //checks win and modify stats
    public boolean checkWin(Player p) { //see if this works, array objects
        if (p.getNumCardsInHand2() == 0) {
            return true;
        }
        if (p.getNumCardsInHand() == 0) {
            return true;
        }
        return false;
    }

    //Pre condition: check win is true, pass in same player
    public void updateStats(Player winner) {
        winner.incNumWins();
        for (int i = 0; i < players.length; i++) { //losers 
            players[i].incNumPlays();
            if (winner != players[i]) {
                players[i].incNumLoses();
            }
        }
    }

    //check win true, update stats, then save
    public void saveBoard() throws FileNotFoundException {
        File save = new File("stats.txt"); //properties of a file
        PrintWriter output = new PrintWriter(save); //create the file
        //write output to the file
        for (int i = 0; i < players.length; i++) {
            String name = players[i].getName();
            //System.out.println(name);
            int plays = players[i].getNumPlays();
            //System.out.println(plays);
            int wins = players[i].getNumWins();
            //System.out.println(wins);
            int loses = players[i].getNumLoses();
            //System.out.println(loses);
            output.print(name + " " + plays + " " + wins + " " + loses + " ");
            output.println();
        }

        output.close(); //clost the file
    }

    public void loadBoard() throws FileNotFoundException { //test the input out put, not done yet, if file exists, load this instead of putting in a new player, make players from these instead
        File load = new File("stats.txt"); //file check = new file stats, if file stats exist, load board from original board otherwise nothing.
        Scanner input = new Scanner(load);
        while (input.hasNext()) { //make sure info in here is not tampered with and is correct
            for (int i = 0; i < players.length; i++) {
                //name, plays, wins, loses
                players[i].setName(input.next());
                players[i].setNumPlays(Integer.parseInt(input.next()));
                players[i].setNumWins(Integer.parseInt(input.next()));
                players[i].setNumLoses(Integer.parseInt(input.next()));
            }
        }
        input.close();
    }

    //save and then exit, or does it even matter
    public void exitBoard() {
        try {
            saveBoard();
        } catch (FileNotFoundException fnf) {
            System.out.println("Error saving");
        }
        System.exit(0);// does this work
    }

    public int getTurn() {
        return turn;
    }

    public void incTurn() {//where do i do this, must do one at the very beginning, or make it one in the constructor
        turn += 1;
    }

    public Player determinePlayerTurn() {
        if (turn % 4 == 0) {
            return players[0];
        } else if (turn % 4 == 1) {
            return players[1];
        } else if (turn % 4 == 2) {
            return players[2];
        } else if (turn % 4 == 3) {
            return players[3];
        } else {
            System.out.println("Error in Board class, determinePlayerTurn(), reached else statement");
            return players[0];
        }
    }

    private void dealAllCards() { //this works
        int initDeckSize = deck.getSize();
        for (int i = 0; i < initDeckSize; i++) {
            for (int j = 0; j < players.length; j++) {
                if (players[j].getNumCardsInHand() < maxHandSize) {
                    Card c = deck.deal();
                    players[j].addCard(c);
                }
            }
        }
    }

    private void dealAllCards(String s) {
        int initDeckSize = deck.getSize();
        while (initDeckSize > 0) {
            for (int i = 0; i < players.length; i++) {
                while (players[i].getNumCardsInHand() < maxHandSize) {
                    Card c = deck.deal();
                    players[i].addCard(c);
                }
            }
            initDeckSize--;
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "Here is what's in the deck before dealing all cards: \n";
        s += deck.toString() + "\n";
        s += "Here are the cards on play area: \n";
        for (int i = 0; i < playArea.size(); i++) {
            s += playArea.get(i) + "\n";
        }
        s += "The max hand size is " + maxHandSize + " and the max play area size is " + playArea.size() + ".\n";
        s += "The board has " + players.length + " players. \n";
        for (int i = 0; i < players.length; i++) {
            s += players[i].toString() + "\n\n";
        }
        return s;
    }

}
