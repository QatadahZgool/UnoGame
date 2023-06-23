package com.atypon.uno.game.card;

import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;

public class NumberedCard extends Card {
    private final int number;

    public NumberedCard(Color color, Status status, int number) {
        super(color, status);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void operation() {

    }

    @Override
    public int getCardScour() {
        return number;
    }

    @Override
    public void display() {
        System.out.print(color + " " + number);
    }
}
