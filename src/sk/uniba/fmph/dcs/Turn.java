package sk.uniba.fmph.dcs;

import java.util.Optional;

public class Turn{
    private TurnStatus turnStatus;
    private Hand hand;
    private Deck deck;
    private Play play;
    private DiscardPile discardPile;

    public Turn(int actions, int buys, int coins){
        this.turnStatus = new TurnStatus(actions, buys, coins);
        hand = new Hand();
        deck = new Deck();
        play = new Play();
        discardPile = new DiscardPile();
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

    public void updateStatus(Card card){
        turnStatus.actions += card.cardType().getPlusActions();
        turnStatus.buys += card.cardType().getPlusBuys();
        turnStatus.coins += card.cardType().getPlusCoins();
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

    public void changeDeck(){
        deck.addCards(discardPile.shuffle());
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }
}
