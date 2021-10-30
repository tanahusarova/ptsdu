package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static sk.uniba.fmph.dcs.GameCardType.*;

public class Turn{
    private TurnStatus turnStatus;
    private Hand hand;
    private Deck deck;
    private Play play;
    private DiscardPile discardPile;
    private List<BuyDeck> buyDecks;

    public Turn(int actions, int buys, int coins){
        this.turnStatus = new TurnStatus(actions, buys, coins);
        this.hand = new Hand();
        this.deck = new Deck();
        this.play = new Play();
        this.discardPile = new DiscardPile();
        this.buyDecks = new ArrayList<>();

        buyDecks.add(new BuyDeck(GAME_CARD_TYPE_MARKET, 10));
        buyDecks.add(new BuyDeck(GAME_CARD_TYPE_SMITHY, 10));
        buyDecks.add(new BuyDeck(GAME_CARD_TYPE_VILLAGE, 10));
        buyDecks.add(new BuyDeck(GAME_CARD_TYPE_FESTIVAL, 10));
        buyDecks.add(new BuyDeck(GAME_CARD_TYPE_LABORATORY, 10));

        getCardsForNextMove();

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

    public void endTurn(){
        List<CardInterface> tmp = play.throwAll();
        tmp.addAll(hand.throwAll());
        discardPile.addCards(tmp);
    }

    public void changeDeck(){
        deck.addCards(discardPile.shuffle());
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void getCardsForNextMove(){
        CardInterface card;

        for (int i = 0; i < 5; i++){
            card = deck.getNewMove();
            if (card == null){
                // turn.getDeck().addCards(turn.getDiscardPile().shuffle());
                changeDeck();
                card = deck.getNewMove();
            }
            hand.addCards(card);
        }
    }

    public int getMoneyOnHand(){
        int moneyOnHandAmount = 0;
        GameCardType gct;

        for (int i = 0; i < hand.getSize(); i++) {
            gct = hand.getType(i);

            if (gct == GAME_CARD_TYPE_COPPER) {
                moneyOnHandAmount++;
            }
        }
        return moneyOnHandAmount;
    }

    public List<CardInterface> payWithMoneyOnHand(int i){
        if (getMoneyOnHand() < i) return null;

        List<CardInterface> money = new ArrayList<>();

        for (int k = 0; k < hand.getSize(); k++){
            if (hand.getCard(k).equals(new Card(GAME_CARD_TYPE_COPPER)))
                money.add(hand.getCard(k));
            if (money.size() == i) break;
        }

        hand.removeFrom(money);
        return money;
    }

    public int getBuyDecksSize(){
        return buyDecks.size();
    }

}
