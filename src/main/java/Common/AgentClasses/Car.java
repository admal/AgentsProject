package Common.AgentClasses;

import Common.Abstract.IPosition;
import jade.core.AID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jedrek on 03.05.16.
 */
public class Car implements Serializable {
    private AID aid;
    private IPosition position;
    private IPosition destination;
    private IPosition chargerPosition;
    private float charge;
    private int speed;

    public int getFuelBurning() {
        return fuelBurning;
    }

    public void setFuelBurning(int fuelBurning) {
        this.fuelBurning = fuelBurning;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int fuelBurning;

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

    public IPosition getChargerPosition() {
        return chargerPosition;
    }

    public void setChargerPosition(IPosition chargerPosition) {
        this.chargerPosition = chargerPosition;
    }
}
