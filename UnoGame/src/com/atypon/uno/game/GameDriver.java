package com.atypon.uno.game;

import com.atypon.uno.game.contract.CardGenerator;
import com.atypon.uno.game.contract.PlayerCardGenerator;
import com.atypon.uno.game.impl.CardGeneratorDefaultImpl;
import com.atypon.uno.game.impl.PlayerCardGeneratorDefaultImpl;

public class GameDriver {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CardGenerator cardGenerator = new CardGeneratorDefaultImpl();
        PlayerCardGenerator playerCardGenerator = new PlayerCardGeneratorDefaultImpl(4, 7, cardGenerator);
        Game game = new MyGameVirsion(cardGenerator, playerCardGenerator);
        game.play();

    }

}