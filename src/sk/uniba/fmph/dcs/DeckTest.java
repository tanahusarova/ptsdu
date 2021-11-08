package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {
    private Deck deck;

    class FakeCardD implements CardInterface {
        private GameCardType cardType;

        FakeCardD(GameCardType cardType) { this.cardType = cardType; }

        public void evaluate(TurnStatus t) { }

        public GameCardType cardType() {
            return cardType;
        }
    }

    @Test
    public void assertGetNewMove(){
        deck = new Deck(new DiscardPile());
        FakeCardD card = new FakeCardD(deck.getNewMove().cardType());
        assertFalse(card == null);
        assertEquals(deck.getSize(), 9);
        assertTrue(card.cardType == GameCardType.GAME_CARD_TYPE_COPPER || card.cardType == GameCardType.GAME_CARD_TYPE_ESTATE);
    }

    @Test
    public void assertDraw(){
        deck = new Deck(new DiscardPile());
        deck.draw(4);
        assertEquals(deck.getSize(), 6);
        List<CardInterface> cards = deck.draw(5);
        assertEquals(deck.getSize(), 1);
        assertFalse(cards.isEmpty());
        assertEquals(cards.size(), 5);
    }

    @Test
    public void assertAddCards(){
        deck = new Deck(new DiscardPile());
        List<CardInterface> cards = new ArrayList<>(){{
            add(new FakeCardD(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new FakeCardD(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new FakeCardD(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new FakeCardD(GameCardType.GAME_CARD_TYPE_MARKET));
        }};
        deck.addCards(cards);
        assertTrue(deck.getSize() == 14);
        deck.draw(10);
        FakeCardD card = new FakeCardD(deck.getNewMove().cardType());
        assertTrue(card.cardType == GameCardType.GAME_CARD_TYPE_VILLAGE);

    }
}
