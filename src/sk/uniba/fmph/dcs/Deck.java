package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import static sk.uniba.fmph.dcs.GameCardType.*;

public class Deck extends Game{
    private Queue<Card> cards;

    public Deck(){
        List<CardInterface> tmp = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            tmp.add(new Card(GAME_CARD_TYPE_ESTATE));
        }
        for (int i = 0; i < 7; i++){
            tmp.add(new Card(GAME_CARD_TYPE_COPPER));
        }
        DiscardPile tmpDisPile = new DiscardPile(tmp);
        tmpDisPile.shuffle();

        cards.addAll((Collection<? extends Card>) tmpDisPile.getCards());

    }

    public CardInterface getNewMove(){
        return cards.poll();
    }

    public void addCards(List<CardInterface> cards){
        this.cards.addAll((Collection<? extends Card>) cards);
    }

}
