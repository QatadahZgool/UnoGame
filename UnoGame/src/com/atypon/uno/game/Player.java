package com.atypon.uno.game;

import com.atypon.uno.game.card.Card;

import java.util.List;

public class Player {
    private final String playerName;
    private final List<Card> cards;

    public Player(String playerName, List<Card> cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCards(Card card) {
        cards.add(card);
    }

    public Card getCard(int numberOfCard) {
        return cards.get(numberOfCard);
    }

    public void removeCard(int index) {
        cards.remove(index);
    }

    public int getSize() {
        return cards.size();
    }

    public void desPlay() {
        System.out.println(playerName);
        int i = 0;
        for (Card card : cards) {
            card.display();
            System.out.println("     " + i++);
        }
    }
}
