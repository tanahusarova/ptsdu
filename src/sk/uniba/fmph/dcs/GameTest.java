package sk.uniba.fmph.dcs;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Test
    public void assertPlayCard(){
        game = new Game();
        assertFalse(game.playCard(4));
        assertFalse(game.playCard(3));
        game.getTurn().getHand().addCards(new Card(GameCardType.GAME_CARD_TYPE_VILLAGE));
        assertTrue(game.playCard(5));
    }

    @Test
    public void assertBuyCard(){
        game = new Game();
        int money = game.getTurn().getMoneyOnHand();
        if (money >= 5) assertTrue(game.buyCard(3));
        else assertFalse(game.buyCard(3));
        game.getTurn().getBuyDeck(0).buy(10);
        assertFalse(game.buyCard(0));
    }

    @Test
    public void assertEndTurn(){
        game = new Game();
        game.getTurn().getBuyDeck(0).buy(10);
        game.getTurn().getBuyDeck(2).buy(10);
        game.getTurn().getBuyDeck(4).buy(10);
        assertFalse(game.buyCard(4));
        game.endTurn();
    }

}
