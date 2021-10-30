package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Play{
    private ArrayList<CardInterface> cards;
    public Play(){
        cards = new ArrayList<>();
    }

    public void putTo(CardInterface card){
        cards.add(card);
    }

    public void putTo(List<CardInterface> card){
        cards.addAll(card);
    }

    public ArrayList<CardInterface> throwAll(){
        ArrayList<CardInterface> tmp = new ArrayList<>();

        tmp.addAll(cards);
        cards.clear();

        return tmp;
    }

    public int getSize(){
        return cards.size();
    }

}
