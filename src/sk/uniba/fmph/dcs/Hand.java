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

    public void addCards(List<CardInterface> cards){
        this.cards.addAll(cards);
    }

    public int getSize(){ return cards.size(); }

    public GameCardType getType(int i){
        return cards.get(i).cardType();
    }

    public CardInterface play(int idx){
        if (cards.get(idx) == null) return null;

        CardInterface tmp = cards.get(idx);
        cards.remove(idx);

        return tmp;
    }

    public boolean removeFrom(List<CardInterface> cards){
        return cards.removeAll(cards);
    }

    public CardInterface getCard(int i){
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
