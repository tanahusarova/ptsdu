package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public boolean isActionCard(int idx){
        return cards.get(idx).cardType().isAction;
    }

    public CardInterface play(int idx){
        if (cards.get(idx) == null) return null;
        CardInterface tmp = cards.get(idx);
        cards.remove(idx);
        return tmp;
    }

    public ArrayList<CardInterface> throwAll(){
        ArrayList<CardInterface> tmp = new ArrayList<>();
        tmp.addAll(cards);
        cards.clear();
        return tmp;
    }

}
