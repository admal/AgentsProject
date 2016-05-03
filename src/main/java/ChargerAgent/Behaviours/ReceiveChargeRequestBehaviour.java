package ChargerAgent.Behaviours;

import Common.Messages.ChargeRequest;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReceiveChargeRequestBehaviour extends CyclicBehaviour{


    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg!=null){
            if(msg.getOntology().equals("chargeRequest")){
                try {
                    ChargeRequest request = (ChargeRequest) msg.getContentObject();
                    AID id = request.agentId;
                    myAgent.addBehaviour(new SendChargeResponseBehaviour(myAgent,id));
                } catch (UnreadableException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
