/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhppan_final_project;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author zheny
 */
public class Deck {
    private List<Card> cards;
    
    public Deck() {
        cards = new ArrayList<Card>();
        makeDeck();
        //shuffle();
    }
    
    public List<Card> getCards() {
        return cards;
    }
    
    public int getSize(){
        return cards.size();
    }
    
    private void makeDeck() {
        String[] suitsArr = {"diamonds", "clubs", "hearts", "spades"};
        String[] valueArr = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        //int[] pointArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}; // dont even need
        for(int i = 0; i < suitsArr.length; i++) {
            for(int j = 0; j < valueArr.length; j++) {
                cards.add(new Card(suitsArr[i], valueArr[j], j + 1));
            }
        }
    }
    
    public void shuffle() {
        for(int i = 0; i < cards.size(); i++) {
            int num = (int)(Math.random() * cards.size());
            Card cardAtI = cards.set(i, cards.get(num));
            cards.set(num, cardAtI);
        }
    }
    
    //COMPLETE THIS
    public Card deal() {
        Card theCard = cards.get(cards.size() - 1);
        removeTopCard();
        return theCard;
    }
    
    private void removeTopCard() {
        cards.remove(cards.size() - 1); //SEE IF THIS WORKS
    }
    
    public String toString() {
        String s = "";
        s += "There are " + cards.size() + " cards in the deck. \n";
        s += "The deck has: \n";
        for(int i = 0; i < cards.size(); i++) {
            s += "\t[" + cards.get(i).toString() + "]\n";
        }
        return s;
    }
}
