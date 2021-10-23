package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Game{

    private GameState gameState;
    boolean playPhase;
    boolean buyPhase;
    private Turn turn;

    public Game(){
        gameState = new GameState();
        playPhase = true;
        buyPhase = false;
    }

    public void getCardsForNextMove(){
        CardInterface card;
        for (int i = 0; i < 5; i++){
            card = turn.getDeck().getNewMove();
            if (card == null){
               // turn.getDeck().addCards(turn.getDiscardPile().shuffle());
                turn.changeDeck();
                card = turn.getDeck().getNewMove();
            }
            turn.getHand().addCards(card);
        }
    }

    public boolean playCard(int handIdx){
        Card card = (Card) turn.getHand().play(handIdx);
        if (card != null){
            turn.getPlay().putTo(card);
            return true;
        }
        return false;
    }

    public boolean endPlayCardPhase(){
        //ci som skoncila hru
        return false;
    }

    public boolean buyCard(int buyCardIdx){
        return false;
    }

    public boolean endTurn(){
        playPhase = true;
        buyPhase = false;
        return false;
    }

    public GameState getGameState(){
        return gameState;
    }
}
