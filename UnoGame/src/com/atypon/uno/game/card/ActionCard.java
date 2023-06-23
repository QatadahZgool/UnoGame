package com.atypon.uno.game.card;

import com.atypon.uno.game.constant.Action;
import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;

public class ActionCard extends SpecialCard {

    public ActionCard(Color color, Status status, Action action) {
        super(color, status, action);
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
        return 20;
    }

    @Override
    public void display() {
        System.out.print(color + " " + action);
    }
}
