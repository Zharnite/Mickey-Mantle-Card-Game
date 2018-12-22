/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhppan_final_project;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author zheny
 */
public class Player {
    protected String name;
    protected List<Card> hand;
    protected List<Card> hand2;
    protected boolean seeCards;
    protected Card selectedCard; //not yet used
    
    protected int numPlays; // also need winning percentage
    protected int numWins; //info saved to file, need to figure this out
    protected int numLoses;
    
    public Player(String name) {//FINISH THIS
        hand = new ArrayList<Card>();
        hand2 = new ArrayList<Card>();
        this.name = name;
        this.seeCards = false;
        numPlays = 0;
        numWins = 0;
        numLoses = 0;
    }
    
    public Player(String name, boolean seeCards) {
        hand = new ArrayList<Card>();
        hand2 = new ArrayList<Card>();
        this.name = name;
        this.seeCards = seeCards;
        numPlays = 0;
        numWins = 0;
        numLoses = 0;
    }
    
    public void clearHand() {
        hand = new ArrayList<Card>();
        hand2 = new ArrayList<Card>();
    }
    
    //play area will be passed in, which is an array list
    public boolean hasMove(List<Card> playAreaList) { // how will they be able to play the 7 and the null spots also ace condition and end card condition
        //first move, first player must have a 7 or skip
        if(playAreaList.size() == 0 && !hasSeven()) {
            return false;
        }
        
        if(hasSeven()) { //if you have a 7
            return true;
        }
        
        //need a way to change playAreaList 
        //seems to work but inefficient
        for(int i = 0; i < hand.size(); i++) { // loop through to compare cards
            for(int j = 0; j < playAreaList.size(); j++) {
                String handSuit = hand.get(i).getSuit();
                String playAreaSuit = playAreaList.get(j).getSuit();
                int handValue = hand.get(i).getPoint();
                int playAreaValue = playAreaList.get(j).getPoint();
                
                if(playAreaValue == 1) { //if there is an ace on the play area
                    continue; //goes to the next iteration? Does it? Maybe it should be moved outside of the outer if statement and directly under the scope of the loop
                } //check for ace before going to all other checks
                
                if(handSuit.equals(playAreaSuit)) {
                    if(handValue == 1) { //if you have an ace
                        if(playAreaValue == 13 || playAreaValue == 2) { //if there is a 13 or 2, ace has not yet been played
                            return true;
                        }
                    }
                    
                    if(handValue ==  playAreaValue + 1 || handValue ==  playAreaValue - 1) { //all in between scenarios
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean hasSeven() {
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getPoint() == 7) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasMove2(List<Card> playAreaList) { // how will they be able to play the 7 and the null spots also ace condition and end card condition
        //first move, first player must have a 7 or skip
        if(playAreaList.size() == 0 && !hasSeven2()) {
            return false;
        }
        
        if(hasSeven2()) { //if you have a 7
            return true;
        }
        
        //need a way to change playAreaList 
        //seems to work but inefficient
        for(int i = 0; i < hand2.size(); i++) { // loop through to compare cards
            for(int j = 0; j < playAreaList.size(); j++) {
                String handSuit = hand2.get(i).getSuit();
                String playAreaSuit = playAreaList.get(j).getSuit();
                int handValue = hand2.get(i).getPoint();
                int playAreaValue = playAreaList.get(j).getPoint();
                
                if(playAreaValue == 1) { //if there is an ace on the play area
                    continue; //goes to the next iteration? Does it? Maybe it should be moved outside of the outer if statement and directly under the scope of the loop
                } //check for ace before going to all other checks
                
                if(handSuit.equals(playAreaSuit)) {
                    if(handValue == 1) { //if you have an ace
                        if(playAreaValue == 13 || playAreaValue == 2) { //if there is a 13 or 2, ace has not yet been played
                            return true;
                        }
                    }
                    
                    if(handValue ==  playAreaValue + 1 || handValue ==  playAreaValue - 1) { //all in between scenarios
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean hasSeven2() {
        for(int i = 0; i < hand2.size(); i++) {
            if(hand2.get(i).getPoint() == 7) {
                return true;
            }
        }
        return false;
    }
    
    //PRECONDITION: hasMove() NEEDS to be TRUE
    public Card cheat(List<Card> playAreaList) {//still need another method to remove the card from the hand, should I include it in here? no, lots of loop, might remove all valid plays
        Card c = hand.get(0);
        
        if(hasSeven()) { 
            for(int i = 0; i < hand.size(); i++) {
                if(hand.get(i).getPoint() == 7) {
                    return hand.get(i);
                }
            }
        }
        
        for(int i = 0; i < hand.size(); i++) {//complete this double array
            for(int j = 0; j < playAreaList.size(); j++) {
                String handSuit = hand.get(i).getSuit();
                String playAreaSuit = playAreaList.get(j).getSuit();
                int handValue = hand.get(i).getPoint();
                int playAreaValue = playAreaList.get(j).getPoint();
                
                if(playAreaValue == 1) { //if there is an ace on the play area, skip it
                    continue; 
                }
                
                if(handSuit.equals(playAreaSuit)) {
                    if(handValue == 1) { //if you have an ace
                        if(playAreaValue == 13 || playAreaValue == 2) { //if there is a 13 or 2, ace has not yet been played
                            c = hand.get(i);
                        }
                    }                    
                    else if(handValue ==  playAreaValue + 1 || handValue ==  playAreaValue - 1) { //all in between scenarios
                        c = hand.get(i);
                    }
                }
            }
        }
        
        return c;
        
    } //try catch if cant cheat, but cheat happens, or check if has move first and then you can avoid error
    
    public Card cheat2(List<Card> playAreaList) {//still need another method to remove the card from the hand, should I include it in here? no, lots of loop, might remove all valid plays
        Card c = hand2.get(0);
        
        if(hasSeven()) { 
            for(int i = 0; i < hand2.size(); i++) {
                if(hand2.get(i).getPoint() == 7) {
                    return hand2.get(i);
                }
            }
        }
        
        for(int i = 0; i < hand2.size(); i++) {//complete this double array
            for(int j = 0; j < playAreaList.size(); j++) {
                String handSuit = hand2.get(i).getSuit();
                String playAreaSuit = playAreaList.get(j).getSuit();
                int handValue = hand2.get(i).getPoint();
                int playAreaValue = playAreaList.get(j).getPoint();
                
                if(playAreaValue == 1) { //if there is an ace on the play area, skip it
                    continue; 
                }
                
                if(handSuit.equals(playAreaSuit)) {
                    if(handValue == 1) { //if you have an ace
                        if(playAreaValue == 13 || playAreaValue == 2) { //if there is a 13 or 2, ace has not yet been played
                            c = hand2.get(i);
                        }
                    }                    
                    else if(handValue ==  playAreaValue + 1 || handValue ==  playAreaValue - 1) { //all in between scenarios
                        c = hand2.get(i);
                    }
                }
            }
        }
        
        return c;
        
    }
    
    public void setName(String s) {
        name = s;
    }
    
    public String getName() {
        return name;
    }
    
    public void addCard(Card c) {
        hand.add(c);
        hand2.add(c);
    }
    
    public void removeCard(int index) {//PUBLIC OR PRIVATE, public for player remore. should it be in cheat or keep it consistent?
        hand.remove(index);
    }
    public void removeCard(Card other) {
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).equals(other)) {
                hand.remove(i);
            }
        }
    }
    
    public void removeCard2(int index) {//PUBLIC OR PRIVATE, public for player remore. should it be in cheat or keep it consistent?
        hand2.remove(index);
    }
    public void removeCard2(Card other) {
        for(int i = 0; i < hand2.size(); i++) {
            if(hand2.get(i).equals(other)) {
                hand2.remove(i);
            }
        }
    }
    
    public void setSeeCardsTrue() {
        seeCards = true;
    }
    
    public void setSeeCardsFalse() {
        seeCards = false;
    }
    
    public int getNumCardsInHand() {
        return hand.size();
    }
    
    public int getNumCardsInHand2() {
        return hand2.size();
    }
    
    public int getNumPlays() {
        return numPlays;
    }
    
    public void incNumPlays() {
        numPlays += 1;
    }
    
    public void setNumPlays(int n) {
        numPlays = n;
    }
    
    public int getNumWins() {
        return numWins;
    }
    
    public void incNumWins() {
        numWins += 1;
    }
    
    public void setNumWins(int n) {
        numWins = n;
    }
    
    public int getNumLoses() {
        return numLoses;
    }
    
    public void incNumLoses() {
        numLoses += 1;
    }
    
    public void setNumLoses(int n) {
        numLoses = n;
    }
    
    public double getWinNumber() {
        if(numPlays == 0) {
            return 0;
        }
        if(numWins > 0 && numLoses == 0) {
            return 100;
        }
        if(numWins == 0 && numLoses > 0) {
            return 0;
        }
        double num = (((double)numWins) / numPlays) * 100;
        return num;
    }
    
    public String getWinPercentage() {
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        double num = getWinNumber();
        String s = df.format(num);
        return s;
    }
    
    public List<Card> getHand() {
        return hand;
    }
    
    public List<Card> getHand2() {
        return hand2;
    }
    
    public boolean equals(Player other) {
        if(name.equals(other.getName())) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        String s = "";
        s += "Player Name: " + name + "\n";
        s += "Plays: " + numPlays + "\n";
        s += "Wins: " + numWins + "\n";
        s += "Loses: " + numLoses + "\n";
        s += "Win Percentage: " + getWinPercentage() + "%\n";
        s += "Number of Cards: " + getNumCardsInHand() + "\n";
        s += "Player hand consists of: \n";
        for(int i = 0; i < hand.size(); i++) {
            s += "\t[" + hand.get(i).toString() + "]\n";
        }
        return s;
    }
}
