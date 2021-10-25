package sk.uniba.fmph.dcs;

import java.util.HashMap;
import java.util.Optional;

public class BuyDeck {
    private int cardCount;
    private GameCardType gameCardType;

    public BuyDeck(GameCardType cardType, int count){
        cardCount = count;
        gameCardType = cardType;
    }

    public CardInterface buy(){
        if (cardCount > 0){
            cardCount--;
            return new Card(gameCardType);
        }
        return null;
    }

    public int getCardCount(){
        return cardCount;
    }

    public CardInterface sellCard(){
        if(cardCount <= 0) return null;
        cardCount--;
        return new Card(gameCardType);
    }

    public GameCardType getGameCardType() {
        return gameCardType;
    }
}
