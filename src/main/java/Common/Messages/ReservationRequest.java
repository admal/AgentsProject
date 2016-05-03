package Common.Messages;

import ChargerAgent.Behaviours.SendReservationResponseBehaviour;
import ChargerAgent.ChargerAgent;
import Common.Abstract.IChargerHandable;
import jade.core.AID;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReservationRequest extends Message implements IChargerHandable {
    int occupationTime;
    AID agentID;
    public ReservationRequest(int occupationTime, AID agentID){
        this.occupationTime = occupationTime;
        this.agentID = agentID;
    }

    public void Handle(ChargerAgent agent) {
        agent.addToWaitingTime(occupationTime);
        agent.addBehaviour(new SendReservationResponseBehaviour(agent,agentID));
    }
}
