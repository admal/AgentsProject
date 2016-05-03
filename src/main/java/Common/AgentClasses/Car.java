package Common.AgentClasses;

import jade.core.AID;

/**
 * Created by jedrek on 03.05.16.
 */
public class Car {
    private AID aid;

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public Car(AID aid) {
        this.aid = aid;
    }
}
