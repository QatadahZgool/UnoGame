package com.atypon.uno.game.card;

import com.atypon.uno.game.constant.Action;
import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;

public abstract class SpecialCard extends Card {
    protected final Action action;

    public SpecialCard(Color color, Status status, Action action) {
        super(color, status);

        this.action = action;
    }

    public abstract Action getAction();
}
