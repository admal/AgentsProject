package Common.AgentClasses;

import Common.Abstract.IPosition;
import jade.core.AID;

/**
 * Created by jedrek on 03.05.16.
 */
public class Car {
    private AID aid;
    private IPosition position;

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public Car(AID aid, IPosition position) {
        this.aid = aid;
        this.position = position;
    }

    public IPosition getPosition() {
        return position;
    }

    public void setPosition(IPosition position) {
        this.position = position;
    }
}
