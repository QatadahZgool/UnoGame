package com.atypon.uno.game.card;

import com.atypon.uno.game.constant.Action;
import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;

public class WildCard extends SpecialCard {

    private Color nextColor = Color.WILD_CARD;

    public WildCard(Color color, Status status, Action action) {
        super(color, status, action);
    }

    public Color getNextColor() {
        return nextColor;
    }

    public void setNextColor(Color nextColor) {
        this.nextColor = nextColor;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void operation() {

    }

    @Override
    public int getCardScour() {
        return 50;
    }

    @Override
    public void display() {
        System.out.print(nextColor + " " + action);
    }
}
