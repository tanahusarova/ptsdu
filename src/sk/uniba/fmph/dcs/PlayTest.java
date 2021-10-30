package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;


public class PlayTest {
    private Play play;

    class FakeCardP implements CardInterface {
        private GameCardType cardType;

        FakeCardP(GameCardType cardType) {
            this.cardType = cardType;
        }

        public void evaluate(TurnStatus t) {}

        public GameCardType cardType() {
            return cardType;
        }
    }
    
    @Test
    public void assertPutTo(){
        play = new Play();
        List<CardInterface> cards;
        FakeCardP card1 = new FakeCardP(GameCardType.GAME_CARD_TYPE_VILLAGE);
        FakeCardP card2 = new FakeCardP(GameCardType.GAME_CARD_TYPE_COPPER);
        FakeCardP card3 = new FakeCardP(GameCardType.GAME_CARD_TYPE_ESTATE);
        play.putTo(card1);
        play.putTo(card2);
        assertTrue(play.getSize() == 2);
        cards = play.throwAll();
        assertFalse(cards.isEmpty());
        assertEquals(cards.get(0).cardType(), GameCardType.GAME_CARD_TYPE_VILLAGE);
        play.putTo(card3);
        assertEquals(play.getSize(), 1);
    }

    @Test
    public void assertThrowAll(){
        play = new Play();
        List<CardInterface> cards;
        FakeCardP card1 = new FakeCardP(GameCardType.GAME_CARD_TYPE_VILLAGE);
        FakeCardP card2 = new FakeCardP(GameCardType.GAME_CARD_TYPE_ESTATE);
        cards = play.throwAll();
        assertTrue(cards.isEmpty());
        play.putTo(card1);
        play.putTo(card2);
        assertTrue(play.getSize() == 2);
        cards = play.throwAll();
        assertFalse(cards.isEmpty());
        assertTrue(play.getSize() == 0);
    }
}
