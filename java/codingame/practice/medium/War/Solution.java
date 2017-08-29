import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        LinkedList<Card> player1Deck = new LinkedList<>();
        LinkedList<Card> player2Deck = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            player1Deck.add(new Card(cardp1));
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            player2Deck.add(new Card(cardp2));
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.err.println("Deck of Player 1 : " + String.join(";", player1Deck.stream().map(Card::toString).collect(Collectors.joining(", "))));
        System.err.println("Deck of Player 2 : " + String.join(";", player2Deck.stream().map(Card::toString).collect(Collectors.joining(", "))));

        String result = play(player1Deck,player2Deck);

        System.out.println(result);
    }

    private static String play(LinkedList<Card> player1Deck, LinkedList<Card> player2Deck){
        Card cdp1 = null;
        Card cdp2 = null;
        LinkedList<Card> potp1 = new LinkedList<>();
        LinkedList<Card> potp2 = new LinkedList<>();
        int turn  = 0;
        int warTurn = 0;
        boolean pat = false;
        while(!player1Deck.isEmpty() && !player2Deck.isEmpty() && !pat){
            cdp1 = player1Deck.poll();
            cdp2 = player2Deck.poll();
            /* When a player wins a battle, they put back the cards at the bottom of their deck 
             * in a precise order. First the cards from the first player, then the one from the second player 
             * (for a "war", all the cards from the first player then all the cards from the second player).
             * */
            if(cdp1.getValue() > cdp2.getValue()){
                if(potp1.isEmpty() && potp2.isEmpty()){
                    player1Deck.add(cdp1);
                    player1Deck.add(cdp2);
                    turn++;
                }
                else {
                    potp1.add(cdp1);
                    potp2.add(cdp2);
                    player1Deck.addAll(potp1);
                    player1Deck.addAll(potp2);
                    potp1.clear();
                    potp2.clear();
                    warTurn = 0;
                }
                
            }
            else if(cdp1.getValue() < cdp2.getValue()){
                if(potp1.isEmpty() && potp2.isEmpty()){
                    player2Deck.add(cdp1);
                    player2Deck.add(cdp2);
                    turn++;
                }
                else {
                    potp1.add(cdp1);
                    potp2.add(cdp2);
                    player2Deck.addAll(potp1);
                    player2Deck.addAll(potp2);
                    potp1.clear();
                    potp2.clear();
                    warTurn = 0;
                }
            }
            else {
                potp1.add(cdp1);
                int i = 0;
                while(!player1Deck.isEmpty() && i<3){
                    potp1.add(player1Deck.poll());
                    i++;
                }

                potp2.add(cdp2);
                i = 0;
                while(!player2Deck.isEmpty() && i<3){
                    potp2.add(player2Deck.poll());
                    i++;
                }
                pat = player1Deck.isEmpty() || player2Deck.isEmpty();
                if(warTurn == 0){
                    turn ++;
                }
                warTurn++;
            }
            
        }


        if(pat){
            return "PAT";
        }
        if (!player1Deck.isEmpty() && player2Deck.isEmpty()) {
            return "1 " + Integer.toString(turn);
        }
        if (player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            return "2 " + Integer.toString(turn);
        }
        
        return "PAT";
        
    }
}

/**
 * Each card is represented by its value followed by its suit: D, H, C, S. For example: 4H, 8C, AS.
 * The cards are ordered by value as follows, from weakest to strongest: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A.
 */
class Card {
    
    private static final Map<String,Integer> valueByRank = new HashMap();

    static {
        valueByRank.put("2",2);
        valueByRank.put("3",3);
        valueByRank.put("4",4);
        valueByRank.put("5",5);
        valueByRank.put("6",6);
        valueByRank.put("7",7);
        valueByRank.put("8",8);
        valueByRank.put("9",9);
        valueByRank.put("10",10);
        valueByRank.put("J",11);
        valueByRank.put("Q",12);
        valueByRank.put("K",13);
        valueByRank.put("A",14);
    }
    
    private String suit;
    private String rank;

    public Card(String s){
        int maxIndex = s.length()-1;
        this.suit = s.substring(maxIndex);
        this.rank = s.substring(0,maxIndex);
    }
    
    public int getValue() {
        return valueByRank.get(this.rank);
    }

    public String toString() {
        return this.rank + this.suit;
    }

}