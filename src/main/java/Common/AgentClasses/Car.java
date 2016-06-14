package Common.AgentClasses;

import Common.Abstract.IPosition;
import jade.core.AID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jedrek on 03.05.16.
 */
public class Car {
    private AID aid;
    private IPosition position;
    private IPosition destination;
    private float charge;

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

    public IPosition getDestination() {
        return destination;
    }

    public void setDestination(IPosition destination) {
        this.destination = destination;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }
}
