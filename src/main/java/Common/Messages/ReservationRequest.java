package Common.Messages;

import jade.core.AID;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReservationRequest extends Message {
    int occupationTime;
    AID agentID;
    public ReservationRequest(int occupationTime, AID agentID){
        this.occupationTime = occupationTime;
        this.agentID = agentID;
    }
}
