package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Optional;

public class Play{
    private ArrayList<CardInterface> cards;
    public Play(){
        cards = new ArrayList<>();
    }

    public void putTo(Card card){
        cards.add(card);
    }

    public ArrayList<CardInterface> throwAll(){
        ArrayList<CardInterface> tmp = new ArrayList<>();
        tmp.addAll(cards);
        cards.clear();
        return tmp;
    }
}
