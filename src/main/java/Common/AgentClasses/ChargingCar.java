package Common.AgentClasses;

import jade.core.AID;

/**
 * Created by jedrek on 14.06.16.
 */
public class ChargingCar {
    private AID aid;
    private long occupationTime;

    public ChargingCar(AID aid, long occupationTime) {
        this.aid = aid;
        this.occupationTime = occupationTime;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    public long getOccupationTime() {
        return occupationTime;
    }

    public void setOccupationTime(long occupationTime) {
        this.occupationTime = occupationTime;
    }
}
