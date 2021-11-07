package sk.uniba.fmph.dcs;

import java.util.*;

import static sk.uniba.fmph.dcs.GameCardType.*;

public class Deck{
    private Queue<CardInterface> cards;

    public Deck(){
        cards = new LinkedList<>(); //zda sa mi ze tu by sa to dalo dat rovno do cards
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

    public List<CardInterface> draw(int count){ //na tahanie kariet asi vyuzivas getNewMove()
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
    } //tie karty ti neprida naspodok ak to tak teda bolo v zadani

    public int getSize(){
        return cards.size();
    }

}
