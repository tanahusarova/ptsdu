package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

class FakeCardT implements CardInterface {
    private GameCardType cardType;

    FakeCardT(GameCardType cardType) { this.cardType = cardType; }

    public void evaluate(TurnStatus t) { }

    public GameCardType cardType() {
        return cardType;
    }
}

public class HandTest {
    private Hand hand1;

    @Test
    public void assertAddCards(){
        hand1 = new Hand();
        ArrayList<CardInterface> cards = new ArrayList<>(){{
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_MARKET));
        }};

        hand1.addCards(cards);
        assertFalse(hand1.isEmpty());
        List <CardInterface> tmp = hand1.throwAll();
        assertEquals(tmp, cards);
    }

    @Test
    public void assertGetCard(){
        hand1 = new Hand();
        LinkedList<FakeCardT> cards = new LinkedList<>();
        cards.add(new FakeCardT(GameCardType.GAME_CARD_TYPE_ESTATE));
        cards.add(new FakeCardT(GameCardType.GAME_CARD_TYPE_VILLAGE));
        cards.add(new FakeCardT(GameCardType.GAME_CARD_TYPE_COPPER));

        hand1.addCards((CardInterface) cards);
        FakeCardT card = (FakeCardT) hand1.getCard(2);
        assertEquals(card.cardType(), cards.get(2).cardType());
        assertEquals(hand1.play(3).cardType(), cards.get(3).cardType());
        assertTrue(hand1.getSize() == 2);

    }

    @Test
    public void assertAddCard(){
        hand1 = new Hand();
        int size = hand1.getSize();
       // FakeCardT card = new FakeCardT(GameCardType.GAME_CARD_TYPE_COPPER);
        CardInterface card = new FakeCardT(GameCardType.GAME_CARD_TYPE_VILLAGE);

        hand1.addCards(card);
        assertTrue(hand1.getSize() == size + 1);
        hand1.addCards(new ArrayList<>(){{
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_MARKET));
        }});
        assertEquals(size + 5, hand1.getSize());
        assertEquals(card, hand1.getCard(size));
    }

    @Test
    public void assertRemovingCards(){
        hand1 = new Hand();
        ArrayList<CardInterface> cards = new ArrayList<>(){{
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new FakeCardT(GameCardType.GAME_CARD_TYPE_MARKET));
        }};
        int size = hand1.getSize();
        boolean removed = hand1.removeFrom(cards);

        if (removed) assertTrue(size > hand1.getSize());
        else assertEquals(size, hand1.getSize());
    }

}
