package sk.uniba.fmph.dcs;

public class TurnStatus {
    public int actions;
    public int buys;
    public int coins;
    public int points;

    public TurnStatus(int actions, int buys, int coins){
        this.actions = actions;
        this.buys = buys;
        this.coins = coins;
        points = 0;

    }

    public void useCoins(int i) {
        this.coins = this.coins - i;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + actions;
        result = prime * result + buys;
        result = prime * result + coins;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TurnStatus other = (TurnStatus) obj;
        if (actions != other.actions)
            return false;
        if (buys != other.buys)
            return false;
        if (coins != other.coins)
            return false;
        return true;
    }
}
