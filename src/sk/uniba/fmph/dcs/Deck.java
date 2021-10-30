package sk.uniba.fmph.dcs;

import java.util.*;

import static sk.uniba.fmph.dcs.GameCardType.*;

public class Deck{
    private Queue<CardInterface> cards;

    public Deck(){
        cards = new LinkedList<>();
        List<CardInterface> tmp = new ArrayList<>();

        for (int i = 0; i < 3; i++)
            tmp.add(new Card(GAME_CARD_TYPE_ESTATE));

        for (int i = 0; i < 7; i++)
            tmp.add(new Card(GAME_CARD_TYPE_COPPER));

        Collections.shuffle(tmp);
        cards.addAll(tmp);

    }

    public CardInterface getNewMove(){
        return cards.poll();
    }

    public List<CardInterface> draw(int count){
        Card card;
        List<CardInterface> tmp = new ArrayList<>();

        for (int i = 0; i < count; i++){
            card = (Card) cards.poll();
            if (card == null) return tmp;
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
