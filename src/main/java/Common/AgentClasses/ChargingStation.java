package Common.AgentClasses;

import Common.Abstract.IPosition;
import Common.Position;
import jade.core.AID;

import java.io.Serializable;

/**
 * Created by jedrek on 03.05.16.
 */
public class ChargingStation implements Serializable{
    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    private AID aid;

    public IPosition getPosition() {
        return position;
    }

    public void setPosition(IPosition position) {
        this.position = position;
    }

    private IPosition position;

    public ChargingStation(AID aid, IPosition position) {
        this.aid = aid;
        this.position = position;
    }
}
