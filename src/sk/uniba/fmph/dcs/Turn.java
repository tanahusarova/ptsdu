package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Turn{
    private TurnStatus turnStatus;
    private Hand hand;
    private Deck deck;
    private Play play;
    private DiscardPile discardPile;
    private List<BuyDeck> buyDecks;

    public Turn(int actions, int buys, int coins){
        this.turnStatus = new TurnStatus(actions, buys, coins);
        hand = new Hand();
        deck = new Deck();
        play = new Play();
        discardPile = new DiscardPile();
        buyDecks = new ArrayList<>();
    }

    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    public Hand getHand() {
        return hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public Play getPlay() {
        return play;
    }

    public BuyDeck getBuyDeck(int i){
        return buyDecks.get(i);
    }

    public void updateStatus(Card card){
        card.evaluate(turnStatus);
     //   if (card.cardType().isAction()) turnStatus.actions--;

        int plusCards = card.cardType().getPlusCards();
        if (plusCards > 0) {
            for (int i = 0; i < plusCards; i++) {
                CardInterface tmp = deck.getNewMove();
                if (tmp == null) {
                    changeDeck();
                    tmp = deck.getNewMove();
                }
                hand.addCards(tmp);
            }
        }


    }

    public int numberOfEmptyBuyDecks(){
        int tmp = 0;
        for (BuyDeck bd : buyDecks){
            if (bd.getCardCount() == 0) tmp++;
        }
        return tmp;
    }

    public void changeDeck(){
        deck.addCards(discardPile.shuffle());
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }
}
