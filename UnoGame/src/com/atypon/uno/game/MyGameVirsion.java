package com.atypon.uno.game;

import com.atypon.uno.game.card.*;
import com.atypon.uno.game.constant.Action;
import com.atypon.uno.game.constant.Color;
import com.atypon.uno.game.constant.Status;
import com.atypon.uno.game.contract.CardGenerator;
import com.atypon.uno.game.contract.PlayerCardGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGameVirsion extends Game {
    Scanner c = new Scanner(System.in);
    private final List<Card> cardsPlayed = new ArrayList<>();

    private Card lastCard;

    protected MyGameVirsion(CardGenerator generationCard, PlayerCardGenerator generationPlayersCardsDefaultWay) {
        super(generationCard, generationPlayersCardsDefaultWay);
    }

    @Override
    public Player[] getPlayers() {
        return player;
    }

    @Override
    public int getCounter() {
        for (Card card : getCards()) {
            if (card.getStatus() == Status.DRAWN) counter++;
        }
        return counter;
    }

    @Override
    public void play() {
        int index;
        boolean nextPlayerFlag = true;
        int playerFlag = 0;
        counter = getCounter();
        while (true) {
            index = (int) (Math.random() * cards.size());
            if (cards.get(index).getStatus() == Status.NOT_USING && cards.get(index) instanceof NumberedCard) {
                cards.get(index).setStatus(Status.DRAWN);
                lastCard = cards.get(index);
                break;
            }
        }
        while (true) {
            if (checkTheLastCardBeforePlaying(player[playerFlag])){
                if(!hasLegalCard(player[playerFlag]))
                    playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                }
            else {
                lastCard.display();
                System.out.println();
                System.out.println("************");
                playCard(player[playerFlag]);
                if (lastCard instanceof SpecialCard) {
                    if (((SpecialCard) lastCard).getAction() == Action.X4) {
                        playedTheWildCard();
                        playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                        PlayedSkipCard(player[playerFlag]);
                        playerFlag = getNextPlayer(playerFlag, !nextPlayerFlag);
                    } else if (((SpecialCard) lastCard).getAction() == Action.X2) {
                        playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                        PlayedSkipCard(player[playerFlag]);
                        playerFlag = getNextPlayer(playerFlag, !nextPlayerFlag);
                    }
                }
                if(checkGameStatus(player[playerFlag]))
                    break;
                if (lastCard instanceof SpecialCard) {
                    if (((SpecialCard) lastCard).getAction() == Action.TURN)
                        nextPlayerFlag = !nextPlayerFlag;
                    else if(((SpecialCard) lastCard).getAction() == Action.CHANG_COLOR)
                        playedTheWildCard();
                    else if(((SpecialCard) lastCard).getAction() == Action.X4)
                        playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                    else if(((SpecialCard) lastCard).getAction() == Action.X2)
                        playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                    else{
                        playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                        PlayedSkipCard(player[playerFlag]);
                    }
                }
                playerFlag = getNextPlayer(playerFlag, nextPlayerFlag);
                System.out.println("************");
            }
        }
    }

    private boolean hasLegalCard( Player currentPlayer) {
        for (int i = 0; i < currentPlayer.getSize(); i++) {
            if (choiceLegalCard(currentPlayer, i)) return true;
        }
        return false;
    }

    private boolean choiceLegalCard( Player currentPlayer, int index) {
        if (currentPlayer.getCard(index).getColor() == lastCard.getColor()) return true;
        else if (currentPlayer.getCard(index) instanceof WildCard) return true;
        else if (lastCard instanceof WildCard) {
            return currentPlayer.getCard(index).getColor() == ((WildCard) lastCard).getNextColor();
        } else if (currentPlayer.getCard(index) instanceof NumberedCard && lastCard instanceof NumberedCard) {
            return ((NumberedCard) currentPlayer.getCard(index)).getNumber() == ((NumberedCard) lastCard).getNumber();
        } else if (currentPlayer.getCard(index) instanceof ActionCard && lastCard instanceof ActionCard) {
            return ((ActionCard) currentPlayer.getCard(index)).getAction() == ((ActionCard) lastCard).getAction();
        }
        return false;
    }

    private int getNextPlayer(int numberOfCurrentPlayer, boolean nextPlayerFlag) {
        if (nextPlayerFlag)
            numberOfCurrentPlayer++;
        else numberOfCurrentPlayer--;
        if (numberOfCurrentPlayer < 0 || numberOfCurrentPlayer >= getNumberOfPlayers()){
            if(nextPlayerFlag)numberOfCurrentPlayer=0;
            else numberOfCurrentPlayer=getNumberOfPlayers()-1;
        }
        return numberOfCurrentPlayer;
    }

    private boolean checkTheLastCardBeforePlaying(Player player){
        if (!hasLegalCard(player)) {
            if (counter < numberOfGameCards) {
                addOneCard(player, cards);
            } else addOneCard(player, cardsPlayed);
            counter++;
            return true;
        }
        return false;
    }

    private void PlayedSkipCard(Player player){
        if (((SpecialCard) lastCard).getAction() == Action.X4) {
            if (numberOfGameCards - counter >= 4) {
                addManyCard(4, player, cards);
            } else if (numberOfGameCards - counter < 4 && numberOfGameCards - counter > 0) {
                addManyCard(numberOfGameCards - counter, player, cards);
                addManyCard(4 - (numberOfGameCards - counter), player, cardsPlayed);
            } else addManyCard(4, player, cardsPlayed);
            counter += 4;
        }
        else if (((SpecialCard) lastCard).getAction() == Action.X2) {
            if (numberOfGameCards - counter >= 2) {
                addManyCard(2, player, cards);
            } else if (numberOfGameCards - counter == 1) {
                addOneCard(player, cards);
                addOneCard(player, cardsPlayed);
            } else addManyCard(2, player, cardsPlayed);
            counter += 2;
        }
    }

    private void playedTheWildCard(){
        if (lastCard instanceof WildCard) {
            int numberOfColor;
            System.out.println("Choice your color");
            System.out.println("Enter 1 for Red");
            System.out.println("Enter 2 for Yellow");
            System.out.println("Enter 3 for Green");
            System.out.println("Enter 4 for Blue");
            while (true) {
                numberOfColor = c.nextInt();
                if (numberOfColor >= 1 && numberOfColor <= 4) {
                    if (numberOfColor == 1) ((WildCard) lastCard).setNextColor(Color.RED);
                    else if (numberOfColor == 2) ((WildCard) lastCard).setNextColor(Color.YELLOW);
                    else if (numberOfColor == 3) ((WildCard) lastCard).setNextColor(Color.GREEN);
                    else ((WildCard) lastCard).setNextColor(Color.BLUE);
                    break;
                }
                System.out.println("Choice your color again");
            }
        }
    }

    private void playCard(Player player){
        int numberOfPlayerCard;
        player.desPlay();
        if (player.getCards().size() == 2) System.out.println("Uno");
        System.out.println("Enter number your card ");
        while (true) {
            numberOfPlayerCard = c.nextInt();
            if (numberOfPlayerCard >= 0 && numberOfPlayerCard < player.getSize())
                if (choiceLegalCard(player, numberOfPlayerCard))
                    break;
            System.out.println("Enter number your card again");
        }
        player.getCard(numberOfPlayerCard).setStatus(Status.NOT_USING);
        cardsPlayed.add(player.getCard(numberOfPlayerCard));
        lastCard = player.getCard(numberOfPlayerCard);
        player.removeCard(numberOfPlayerCard);
    }

    private boolean checkGameStatus(Player player){
        if (player.getCards().isEmpty()) {
            System.out.println(player.getPlayerName() + " Win");
            for (int i = 0; i < getNumberOfPlayers(); i++) {
                System.out.println(this.player[i].getPlayerName() + " scour : " + countScour(this.player[i]));
            }
            return true;
        }
        return false;
    }

    @Override
    public int countScour(Player player) {
        int scour = 0;
        if (player.getSize() == 0) return 0;
        else {
            for (int i = 0; i < player.getSize(); i++) scour += player.getCard(i).getCardScour();
            return scour;
        }
    }
}
