package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static sk.uniba.fmph.dcs.GameCardType.GAME_CARD_TYPE_COPPER;

public class Game{

    private GameState gameState;
    boolean playPhase;
    boolean buyPhase;
    private Turn turn;

    public Game(){
       // gameState = new GameState();
        playPhase = true;
        buyPhase = false;
        turn = new Turn(1, 1, 0);
    }

    public boolean playCard(int handIdx){
        if (!playPhase) return false;

        Card card = (Card) turn.getHand().play(handIdx);

        if (card != null){
            if (card.cardType().isAction()) turn.getTurnStatus().actions--;
            turn.getPlay().putTo(card);
            turn.updateStatus(card);
            return true;
        }

        turn.getHand().addCards(card);
        return false;
    }

    public boolean endPlayCardPhase(){
        buyPhase = true;
        playPhase = false;
        return true;
    }

    public boolean buyCard(int buyCardIdx){
        if (!buyPhase) return false;
        if (turn.getBuyDecksSize() <= buyCardIdx) return false;

        BuyDeck buyDeck = turn.getBuyDeck(buyCardIdx);
        if(buyDeck.getCardCount() == 0) return false;

        int tmp = buyDeck.getGameCardType().getCost();
        if(turn.getTurnStatus().coins < tmp) return false;

        turn.getTurnStatus().useCoins(tmp);

        Card card = (Card) buyDeck.buy();
        turn.getDiscardPile().addCard(card);
        turn.getTurnStatus().buys--;

        return true;

    }

    public boolean endTurn(){
        turn.endTurn();
        turn.getCardsForNextMove();
        buyPhase = false;
        playPhase = true;
        EndGameStrategy eds = new EndGameStrategyAnd();

        if (eds.isGameOver(turn)) System.out.println("Hra skončila");

        return true;
    }

    public Turn getTurn(){
        return turn;
    }

    public GameState getGameState(){
        return gameState;
    }
}
