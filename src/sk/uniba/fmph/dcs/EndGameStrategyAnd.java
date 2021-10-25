package sk.uniba.fmph.dcs;

public class EndGameStrategyAnd implements EndGameStrategy{
    @Override
    public boolean isGameOver(Turn turn) {
        return turn.numberOfEmptyBuyDecks() >= 3;
    }
}
