package Common.AgentClasses;

import Common.Abstract.IPosition;
import Common.Route;
import jade.core.AID;

/**
 * Created by jedrek on 03.05.16.
 */
public class TransactionCar {
    private AID aid;
    private IPosition position;
    private Route route;
    private boolean participates;

    public IPosition getPosition() {
        return position;
    }

    public boolean isParticipates() {
        return participates;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public TransactionCar(AID aid, IPosition position, Route route, boolean participates) {
        this.aid = aid;
        this.position = position;
        this.participates = participates;
        this.route = route;
    }
}
