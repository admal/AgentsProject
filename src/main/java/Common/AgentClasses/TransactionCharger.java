package Common.AgentClasses;

import Common.Abstract.IPosition;
import jade.core.AID;

/**
 * Created by jedrek on 14.06.16.
 */
public class TransactionCharger {
    private long waitingTime;
    private IPosition position;

    public TransactionCharger(long waitingTime, IPosition position) {
        this.waitingTime = waitingTime;
        this.position = position;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public IPosition getPosition() {
        return position;
    }
}
