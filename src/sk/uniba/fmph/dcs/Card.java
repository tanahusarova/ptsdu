package sk.uniba.fmph.dcs;

public class Card implements CardInterface{

    private String name;
    private String description;
    private int cost;

    private GameCardType typ;

    public Card(GameCardType typ) {
        this.typ = typ;
        name = typ.getName();
        description = typ.getDescription();
        cost = typ.getCost();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cost;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Card other = (Card) obj;
        if (cost != other.cost)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public void evaluate(TurnStatus ts) {

    }

    @Override
    public GameCardType cardType() {
        return this.typ;
    }
}

