package sk.uniba.fmph.dcs;

public class GameCard implements CardInterface{ //tato trieda robi to iste co Card nie?
                                                // ja som ju u seba vymazal ale to je jedno :D
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

    @Override
    public GameCardType cardType() {
        return null;
    }
}
