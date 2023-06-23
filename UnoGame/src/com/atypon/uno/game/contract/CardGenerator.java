package com.atypon.uno.game.contract;

import com.atypon.uno.game.card.Card;

import java.util.List;

public interface CardGenerator {
    List<Card> getInitialCards();

    int getNumberOfCards();
}
