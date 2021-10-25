package sk.uniba.fmph.dcs;

import java.util.*;

public class DiscardPile {
    List<CardInterface> cards;

    public DiscardPile(){
        cards = new ArrayList<>();
    }

    public DiscardPile(List<CardInterface> cards){
        this.cards = cards;
    }

    public Optional<CardInterface> getTopCard() {
    	if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size()-1));
    }
        
    public void addCards(List<CardInterface> _cards) {
        cards.addAll(_cards);
    }

    public void addCard(CardInterface card) {
        cards.add(card);
    }


    public int getSize() {
        return cards.size();
    }
        
    public List<CardInterface> shuffle() {
        Collections.shuffle(cards);
        List<CardInterface> cards_to_send = cards;        
        cards = new ArrayList<CardInterface>();
        return cards_to_send;
    }

    public List<CardInterface> getCards(){
        return cards;
    }
}
        
        
