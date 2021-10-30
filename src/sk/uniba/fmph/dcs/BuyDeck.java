package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BuyDeck {
    private int cardCount;
    private GameCardType gameCardType;

    public BuyDeck(GameCardType cardType, int count){
        cardCount = count;
        gameCardType = cardType;
    }

    public int getCardCount(){
        return cardCount;
    }

    public CardInterface buy(){
        if(cardCount <= 0) return null;

        cardCount--;

        return new Card(gameCardType);
    }

    public List<CardInterface> buy(int i){
        if(cardCount < i) return null;

        cardCount -= i;
        List<CardInterface> cards = new ArrayList<>();
        for (int k = 0; k < i; k++)
            cards.add(new Card(gameCardType));

        return cards;
    }

    public GameCardType getGameCardType() {
        return gameCardType;
    }
}
