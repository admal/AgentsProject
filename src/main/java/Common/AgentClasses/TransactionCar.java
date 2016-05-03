package Common.AgentClasses;

import Common.Abstract.IPosition;
import jade.core.AID;

/**
 * Created by jedrek on 03.05.16.
 */
public class TransactionCar {
    private AID aid;
    private IPosition position;
    private boolean participates;

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public TransactionCar(AID aid, IPosition position, boolean participates) {
        this.aid = aid;
        this.position = position;
        this.participates = participates;
    }
}