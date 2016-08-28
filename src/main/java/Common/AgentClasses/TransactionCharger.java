package Common.AgentClasses;

import Common.Abstract.IPosition;
import jade.core.AID;

/**
 * Created by jedrek on 14.06.16.
 */
public class TransactionCharger implements Comparable{
    private long timeToReach;

    public long getTimeToWait() {
        return timeToWait;
    }

    public void setTimeToWait(long timeToWait) {
        this.timeToWait = timeToWait;
    }

    private long timeToWait;
    private AID aid;
    private long waitingTime;
    private IPosition position;

    public TransactionCharger(AID aid,long waitingTime, IPosition position) {
        this.aid = aid;
        this.waitingTime = waitingTime;
        this.position = position;
    }
    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }
    public long getWaitingTime() {
        return waitingTime;
    }

    public IPosition getPosition() {
        return position;
    }

    public long getTimeToReach() {
        return timeToReach;
    }

    public void setTimeToReach(long timeToReach) {
        this.timeToReach = timeToReach;
    }

    public int compareTo(Object o) {
        TransactionCharger charger = (TransactionCharger) o;

        return (int)(this.timeToReach - charger.timeToReach);
    }
}
