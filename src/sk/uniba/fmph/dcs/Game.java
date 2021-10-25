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
        gameState = new GameState();
        playPhase = true;
        buyPhase = false;
        turn = new Turn(1, 1, 0);
    }

    private void getCardsForNextMove(){
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
        if (!playPhase) return false;
        Card card = (Card) turn.getHand().play(handIdx);
        if (card != null && card.cardType().isAction()){
            turn.getPlay().putTo(card);
            turn.updateStatus(card);
            return true;
        }
        turn.getHand().addCards(card);
        return false;
    }

    public boolean endPlayCardPhase(){
        turn.getTurnStatus().actions--;
        buyPhase = true;
        playPhase = false;
        return true;
    }

    public boolean buyCard(int buyCardIdx){
        if (!buyPhase) return false;
        BuyDeck buyDeck = turn.getBuyDeck(buyCardIdx);
        if(buyDeck.getCardCount() == 0) return false;
        int tmp = buyDeck.getGameCardType().getCost();
        //prejst aj peniaze co mam na ruke, odcitat z ruky, hodit do discard
        int moneyOnHandAmount = 0;
        ArrayList<CardInterface> moneyOnHand = new ArrayList<>();
        GameCardType gct;
        boolean paidForCard = false;

        for (int i = 0; i < turn.getHand().getSize(); i++)
        {
            gct = turn.getHand().getType(i);
            if (gct == GAME_CARD_TYPE_COPPER){
                moneyOnHandAmount++;
                moneyOnHand.add(turn.getHand().getCard(i));
            }
            if(moneyOnHandAmount == tmp){
                turn.getHand().removeFrom(moneyOnHand);
                turn.getPlay().putTo(moneyOnHand);
                paidForCard = true;
                break;

            }
        }

        if(turn.getTurnStatus().coins + moneyOnHandAmount < tmp) return false;
        //vyberanie kariet z ruky

        Card card = (Card) buyDeck.sellCard();

        if (!paidForCard){
            turn.getTurnStatus().useCoins(tmp - moneyOnHandAmount);
            turn.getHand().removeFrom(moneyOnHand);
            turn.getPlay().putTo(moneyOnHand);
        }

        turn.getDiscardPile().addCard(card);
        turn.getTurnStatus().buys--;
        return true;

    }

    public boolean endTurn(){
        List<CardInterface> tmp = turn.getPlay().throwAll();
        turn.getDiscardPile().addCards(tmp);
        getCardsForNextMove();
        buyPhase = false;
        playPhase = true;
        EndGameStrategy eds = new EndGameStrategyAnd();

        return true;
    }

    public GameState getGameState(){
        return gameState;
    }
}
