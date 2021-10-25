package sk.uniba.fmph.dcs;

public class GameCard implements CardInterface{
    private CardInterface card;
    private GameCardType gameCardType;

    public GameCard(CardInterface card){
        this.card = card;
        gameCardType = card.cardType();
    }

    @Override
    public void evaluate(TurnStatus ts) {
        ts.actions += card.cardType().getPlusActions();
        ts.buys += card.cardType().getPlusBuys();
        ts.coins += card.cardType().getPlusCoins();

    }
    /*
    public void evaluateDeck(Hand hand, Deck deck){
        int plusCards = card.cardType().getPlusCards();
        if (plusCards > 0) {
            for (int i = 0; i < plusCards; i++) {
                CardInterface tmp = deck.getNewMove();
                if (tmp == null) {
                    changeDeck();
                    tmp = deck.getNewMove();
                }
                hand.addCards(tmp);
            }
        }
    }

     */

    @Override
    public GameCardType cardType() {
        return null;
    }
}
