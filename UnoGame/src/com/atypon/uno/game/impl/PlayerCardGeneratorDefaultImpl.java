package com.atypon.uno.game.impl;

import com.atypon.uno.game.card.Card;
import com.atypon.uno.game.constant.Status;
import com.atypon.uno.game.contract.CardGenerator;
import com.atypon.uno.game.contract.PlayerCardGenerator;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardGeneratorDefaultImpl implements PlayerCardGenerator {
    protected final List<Card> cards;
    protected final List<List<Card>> playersCards;
    protected final int numberOfPlayers;
    protected final int numberOfPlayerCards;

    public PlayerCardGeneratorDefaultImpl(int numberOfPlayers, int numberOfPlayerCards, CardGenerator generationCard) {
        this.cards = generationCard.getInitialCards();
        this.playersCards = new ArrayList<>(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfPlayerCards = numberOfPlayerCards;
    }

    @Override
    public List<List<Card>> generatePlayersCards() {
        int index;
        for (int i = 0; i < numberOfPlayers; i++) {
            playersCards.add(new ArrayList<>());
        }
        for (int i = 0; i < numberOfPlayerCards; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                while (true) {
                    index = (int) (Math.random() * cards.size());
                    if (cards.get(index).getStatus() == Status.NOT_USING) {
                        cards.get(index).setStatus(Status.DRAWN);
                        playersCards.get(j).add(cards.get(index));
                        break;
                    }
                }
            }
        }
        return playersCards;
    }

    @Override
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
