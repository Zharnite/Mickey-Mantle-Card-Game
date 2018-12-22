/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhppan_final_project;

/**
 *
 * @author zheny
 */
public class Card {
    private String suit;
    private String value;
    private int point;
    
    
    public Card(String suit, String value, int point) {
        this.suit = suit;
        this.value = value;
        this.point = point;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public int getPoint() {
        return point;
    }
    
    public boolean equals(Card other) {
        if(suit.equals(other.getSuit()) && point == other.getPoint()) {
            return true;
        }
        return false;
    }
    
    //NEED toString() method
    public String toString() {
        String s = "";
        s += value + " of " + suit + " with a point value of " + point;
        return s;
    }
}
