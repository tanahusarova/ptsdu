package sk.uniba.fmph.dcs;

import java.util.*;

import static sk.uniba.fmph.dcs.GameCardType.*;

public class Deck{
    private Queue<CardInterface> cards;
    DiscardPile ds;

    public Deck(DiscardPile ds){
        cards = new LinkedList<>();
        this.ds = ds;
        List<CardInterface> tmp = new ArrayList<>();

        for (int i = 0; i < 3; i++)
            tmp.add(new Card(GAME_CARD_TYPE_ESTATE));

        for (int i = 0; i < 7; i++)
            tmp.add(new Card(GAME_CARD_TYPE_COPPER));

        Collections.shuffle(tmp);
        cards.addAll(tmp);

    }

    public CardInterface getNewMove(){
        CardInterface card = cards.poll();
        if (card == null) {
            cards.addAll(ds.shuffle());
            card = cards.poll();
        }
        return card;
    }

    public List<CardInterface> draw(int count){
        CardInterface card;
        List<CardInterface> tmp = new ArrayList<>();

        for (int i = 0; i < count; i++){
            card = cards.poll();
            if (card == null) {
                cards.addAll(ds.shuffle());
                card = cards.poll();
            }
            tmp.add(card);
        }

        return tmp;
    }

    public void addCards(List<CardInterface> cards){
        this.cards.addAll(cards);
    }

    public int getSize(){
        return cards.size();
    }

}
