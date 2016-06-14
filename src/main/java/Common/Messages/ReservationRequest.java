package Common.Messages;

import ChargerAgent.Behaviours.SendReservationResponseBehaviour;
import ChargerAgent.ChargerAgent;
import Common.Abstract.IChargerHandable;
import Common.AgentClasses.ChargingCar;
import jade.core.AID;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReservationRequest extends Message implements IChargerHandable {
    private long occupationTime;
    private AID agentID;
    public ReservationRequest(long occupationTime, AID agentID){
        this.occupationTime = occupationTime;
        this.agentID = agentID;
    }

    public void Handle(ChargerAgent agent) {
        agent.chargingQueue.add(new ChargingCar(agentID, occupationTime));
        agent.addToWaitingTime(occupationTime);
        agent.addBehaviour(new SendReservationResponseBehaviour(agent,agentID));
    }
}
