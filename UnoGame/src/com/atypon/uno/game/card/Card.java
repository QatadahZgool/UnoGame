package com.atypon.uno.game.card;

import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;

public abstract class Card {
    protected final Color color;
    protected Status status;

    public Card(Color color, Status status) {
        this.color = color;
        this.status = status;
    }

    public Color getColor() {
        return color;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public abstract void operation();

    public abstract int getCardScour();

    public abstract void display();
}
