package sk.uniba.fmph.dcs;

public class EndGameStrategyAnd implements EndGameStrategy{
    @Override
    public boolean isGameOver(Turn turn) {
        if (turn.getTurnStatus().points == 6) {
            System.out.println("Vyhral si!");
            return true;
        }
        return turn.numberOfEmptyBuyDecks() >= 3 || !turn.remainEstates();
    }
}
