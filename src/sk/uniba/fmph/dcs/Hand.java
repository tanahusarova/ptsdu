package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Hand{
    private ArrayList<CardInterface> cards;

    public Hand(){
        cards = new ArrayList<>();
    }

    public void addCards(CardInterface card) {
        cards.add(card);
    }

    public void addCards(List<CardInterface> card){
        cards.addAll(card);
    }

    public int getSize(){ return cards.size(); }

    public GameCardType getType(int i){
        return cards.get(i).cardType();
    }

    public CardInterface play(int idx){
        if (idx >= cards.size() || idx < 0) return null;

        CardInterface tmp = cards.get(idx);
        cards.remove(idx);

        return tmp;
    }

    public boolean removeFrom(List<CardInterface> cards){
        return (this.cards).removeAll(cards);
    }

    public CardInterface getCard(int i){
        if (i >= cards.size() || i < 0) return null;
        CardInterface card = cards.get(i);

        return card;
    }

    public ArrayList<CardInterface> throwAll(){
        ArrayList<CardInterface> tmp = new ArrayList<>();

        tmp.addAll(cards);
        cards.clear();

        return tmp;
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

}
