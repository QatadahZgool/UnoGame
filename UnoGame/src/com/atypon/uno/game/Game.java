package com.atypon.uno.game;

import com.atypon.uno.game.card.Card;
import com.atypon.uno.game.constant.Status;
import com.atypon.uno.game.contract.CardGenerator;
import com.atypon.uno.game.contract.PlayerCardGenerator;
import com.atypon.uno.game.exception.InvalidInitialCardsException;
import com.atypon.uno.game.exception.InvalidNumberOfPlayerException;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Game {
    protected static int counter = 0;
    protected final Player[] player;
    protected final List<Card> cards;
    protected final int numberOfPlayers;
    protected final int numberOfGameCards;
    protected List<List<Card>> playersCards;
    protected Scanner cs = new Scanner(System.in);

    protected Game(CardGenerator generationCard, PlayerCardGenerator generationPlayersCards) {
        this.numberOfPlayers = generationPlayersCards.getNumberOfPlayers();
        this.player = new Player[numberOfPlayers];
        this.cards = generationCard.getInitialCards();
        this.numberOfGameCards = generationCard.getNumberOfCards();
        this.playersCards = generationPlayersCards.generatePlayersCards();
        validate();
        setPlayers();
    }

    private void validate() {
        if(numberOfPlayers < 2)
            throw new InvalidNumberOfPlayerException("The number of players can't be less than 2");
        if(cards.size() != numberOfGameCards)
            throw new InvalidInitialCardsException(String.format("The initial list of cards should contains card equal to %s", numberOfGameCards));
        for(Card card : cards){
            if(Objects.isNull(card))
                throw new IllegalArgumentException("Card can't be null");
        }
    }

    private void setPlayers() {
        String playerName;
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            System.out.println("Enter player name" + (i + 1));
            playerName = cs.next();
            player[i] = new Player(playerName, playersCards.get(i));
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Card> getCards() {
        return cards;
    }

    protected void addOneCard(Player player, List<Card> cards) {
        int index;
        while (true) {
            index = (int) (Math.random() * cards.size());
            if (cards.get(index).getStatus() == Status.NOT_USING) {
                cards.get(index).setStatus(Status.DRAWN);
                player.addCards(cards.get(index));
                break;
            }
        }
    }


    protected void addManyCard(int numberOfCards, Player player, List<Card> cards) {
        for (int i = 0; i < numberOfCards; i++) {
            addOneCard(player,cards);
        }
    }

    public abstract Player[] getPlayers();

    public abstract void play();

    public abstract int getCounter();

    public abstract int countScour(Player player);

}
