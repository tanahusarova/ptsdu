package sk.uniba.fmph.dcs;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TurnTest {
    private Turn turn;

    @Test
    public void assertUpdateStatus() {
        turn = new Turn(1, 1, 0);
        CardInterface card1 = new Card(GameCardType.GAME_CARD_TYPE_VILLAGE);
        assertTrue(turn.getTurnStatus().actions == 1);
        assertTrue(turn.getHand().getSize() == 5);
        turn.updateStatus((Card) card1);
        assertTrue(turn.getTurnStatus().actions == 3);
        assertTrue(turn.getHand().getSize() == 6);
        CardInterface card2 = new Card(GameCardType.GAME_CARD_TYPE_FESTIVAL);
        turn.updateStatus((Card) card2);
        assertTrue(turn.getTurnStatus().coins == 2);

    }

    @Test
    public void assertGetCardsForNextMove(){
        turn = new Turn(1, 1, 0);
        assertTrue(turn.getHand().getSize() == 5);
        turn.getCardsForNextMove();
        assertTrue(turn.getHand().getSize() == 10);
        turn.getHand().throwAll();
        assertTrue(turn.getHand().getSize() == 0);

    }

    @Test
    public void assertEndTurn(){
        turn = new Turn(1, 1, 0);
        ArrayList<CardInterface> cards1 = new ArrayList<>(){{
            add(new Card(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new Card(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new Card(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new Card(GameCardType.GAME_CARD_TYPE_MARKET));
            add(new Card(GameCardType.GAME_CARD_TYPE_MARKET));
        }};
        ArrayList<CardInterface> cards2 = new ArrayList<>(){{
            add(new Card(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new Card(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new Card(GameCardType.GAME_CARD_TYPE_ESTATE));
        }};
        assertTrue(turn.getHand().getSize() == 5);
        turn.getHand().play(3);
        assertTrue(turn.getHand().getSize() == 4);
        turn.getHand().addCards(cards1);
        assertTrue(turn.getHand().getType(5) == GameCardType.GAME_CARD_TYPE_COPPER);
        turn.getPlay().putTo(cards2);
        assertTrue(turn.getPlay().getSize() == 3);
        assertTrue(turn.getDiscardPile().getSize() == 0);
        turn.endTurn();
        assertTrue(turn.getDiscardPile().getSize() == 12);
        assertTrue(turn.getDeck().getSize() == 5);
        turn.changeDeck();
        assertTrue(turn.getDiscardPile().getSize() == 0);
        assertTrue(turn.getDeck().getSize() == 17);

    }

    @Test
    public void assertChangeDeck(){
        turn = new Turn(1, 1, 0);
        ArrayList<CardInterface> cards1 = new ArrayList<>(){{
            add(new Card(GameCardType.GAME_CARD_TYPE_VILLAGE));
            add(new Card(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new Card(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new Card(GameCardType.GAME_CARD_TYPE_MARKET));
            add(new Card(GameCardType.GAME_CARD_TYPE_MARKET));
        }};

        turn.getDiscardPile().addCards(cards1);
        assertTrue(turn.getDeck().getSize() == 5);
        turn.getDeck().draw(6);
        assertTrue(turn.getDiscardPile().getSize() == 0);
        assertTrue(turn.getDeck().getSize() == 4);

    }

    @Test
    public void assertNumberOfEmptyBuyDecks(){
        turn = new Turn(1, 1, 0);
        turn.getBuyDeck(0).buy(10);
        turn.getBuyDeck(1).buy(10);
        turn.getBuyDeck(2).buy(8);
        turn.getBuyDeck(3).buy(6);
        turn.getBuyDeck(4).buy(1);
        assertTrue(turn.numberOfEmptyBuyDecks() == 2);
        turn.getBuyDeck(2).buy(2);
        turn.getBuyDeck(3).buy(4);
        turn.getBuyDeck(4).buy(1);
        assertTrue(turn.numberOfEmptyBuyDecks() == 4);

    }

}
