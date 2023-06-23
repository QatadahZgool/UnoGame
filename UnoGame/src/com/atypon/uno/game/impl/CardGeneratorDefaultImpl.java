package com.atypon.uno.game.impl;

import com.atypon.uno.game.card.ActionCard;
import com.atypon.uno.game.card.Card;
import com.atypon.uno.game.card.NumberedCard;
import com.atypon.uno.game.card.WildCard;
import com.atypon.uno.game.constant.Action;
import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;
import com.atypon.uno.game.contract.CardGenerator;

import java.util.ArrayList;
import java.util.List;

public class CardGeneratorDefaultImpl implements CardGenerator {
    protected final int numberOfCards = 108;
    protected final List<Card> cards;

    public CardGeneratorDefaultImpl() {
        this.cards = generateCards();
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        generateNumberdCardsWithActionCards(Color.RED, cards);
        generateNumberdCardsWithActionCards(Color.YELLOW,cards);
        generateNumberdCardsWithActionCards(Color.GREEN,cards);
        generateNumberdCardsWithActionCards(Color.BLUE,cards);
        generateWildCards(cards);
        return cards;
    }

    private void generateNumberdCardsWithActionCards(Color color , List<Card> cards){
        cards.add(new NumberedCard(color, Status.NOT_USING, 0));
        for(int i=0;i<2;i++) {
            for (int j = 1; j < 10; j++) {
                cards.add(new NumberedCard(color, Status.NOT_USING, j));
            }
        }
        for(int i=0;i<2;i++){
            cards.add(new ActionCard(color, Status.NOT_USING, Action.BLOCK));
            cards.add(new ActionCard(color, Status.NOT_USING, Action.TURN));
            cards.add(new ActionCard(color, Status.NOT_USING, Action.X2));
        }
    }
    private void generateWildCards(List<Card> cards) {
        for(int i=0;i<4;i++){
            cards.add(new WildCard(Color.WILD_CARD, Status.NOT_USING, Action.CHANG_COLOR));
            cards.add(new WildCard(Color.WILD_CARD, Status.NOT_USING, Action.X4));
        }
    }
    @Override
    public int getNumberOfCards() {
        return numberOfCards;
    }

    @Override
    public List<Card> getInitialCards() {
        return cards;
    }

}

