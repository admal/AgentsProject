package ChargerAgent.Behaviours;

import ChargerAgent.ChargerAgent;
import Common.Abstract.IChargerHandable;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReceiveMessageBehaviour extends CyclicBehaviour{
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg!=null){
            try {
                IChargerHandable handable = (IChargerHandable) msg.getContentObject();
                if (handable != null)
                    handable.Handle((ChargerAgent)myAgent);
                else
                    System.out.println("Unknown message!");
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
    }
}
