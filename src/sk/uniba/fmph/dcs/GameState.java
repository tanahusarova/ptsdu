package sk.uniba.fmph.dcs;

import java.security.Key;
import java.util.*;

import static sk.uniba.fmph.dcs.GameCardType.GAME_CARD_TYPE_ESTATE;
import static sk.uniba.fmph.dcs.GameCardType.*;

//import org.apache.commons.lang3.tuple.Pair;

public class GameState {
    private List<Card> handCards;
    private HashMap<GameCardType, Integer> buyCards;
    private int deckSize;
    private int discardPileSize;
    private Optional<Card> discardPileTop;
    private int points;
    private int turn;
    private DiscardPile discardPile;
    private Deck deck;

    public GameState(){
        deck = new Deck();
        discardPile = new DiscardPile();
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

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public boolean removeCard(GameCardType type){
        int tmp = buyCards.get(type);
        if (tmp == 0) return false;
        else buyCards.put(type, tmp - 1);
        return true;
    }
/*
    public boolean threePilesOnEmpty(){
        var ref = new Object() {
            int p = 0;
        };
        buyCards.forEach((k, v) -> {if (v == 0) ref.p++;});
        int tmp = ref.p;
        return tmp >= 3;
    }

 */
}
