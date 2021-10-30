package sk.uniba.fmph.dcs;

import java.security.Key;
import java.util.*;

import static sk.uniba.fmph.dcs.GameCardType.GAME_CARD_TYPE_ESTATE;
import static sk.uniba.fmph.dcs.GameCardType.*;

//import org.apache.commons.lang3.tuple.Pair;

public class GameState {
    private List<Card> handCards;
    private Hand hand;
    private HashMap<GameCardType, Integer> buyCards;
    private int deckSize;
    private int discardPileSize;
    private Optional<Card> discardPileTop;
    private int points;
    private int turn;
    private DiscardPile discardPile;
    private Deck deck;

    public GameState(Deck deck, DiscardPile discardPile){
        this.deck = deck;
        this.discardPile = discardPile;
        buyCards = new HashMap<>();
        buyCards.put(GAME_CARD_TYPE_MARKET, 10);
        buyCards.put(GAME_CARD_TYPE_SMITHY, 10);
        buyCards.put(GAME_CARD_TYPE_VILLAGE, 10);
        buyCards.put(GAME_CARD_TYPE_FESTIVAL, 10);
        buyCards.put(GAME_CARD_TYPE_LABORATORY, 10);
    }

    public HashMap<GameCardType, Integer> getBuyCards() {
        return buyCards;
    }

    public int getDiscardPileSize() {
        return discardPile.getSize();
    }

    public int getDeckSize(){
        return deck.getSize();
    }

    public Optional<CardInterface> discardPileTop(){
        return discardPile.getTopCard();
    }

}
