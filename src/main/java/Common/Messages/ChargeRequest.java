package Common.Messages;

import ChargerAgent.Behaviours.SendChargeResponseBehaviour;
import ChargerAgent.ChargerAgent;
import Common.Abstract.IChargerHandable;
import jade.core.AID;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeRequest extends Message implements IChargerHandable{
    public AID agentId;
    public ChargeRequest(AID agentId){
        this.agentId = agentId;
    }
    public void Handle(ChargerAgent agent) {
        agent.addBehaviour(new SendChargeResponseBehaviour(agent,this.agentId));
        System.out.println(agent.getLocalName() + ": new charge request");
    }
}
