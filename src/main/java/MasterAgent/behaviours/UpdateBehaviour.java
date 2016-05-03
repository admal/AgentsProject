package MasterAgent.behaviours;

import Common.Abstract.IMasterHandable;
import jade.core.behaviours.CyclicBehaviour;
import MasterAgent.MasterAgent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/**
 * Created by jedrek on 03.05.16.
 */
public class UpdateBehaviour extends CyclicBehaviour{
    public void action() {

        ACLMessage msg = myAgent.receive();
        if(msg != null){
            try {
                IMasterHandable msgContent = (IMasterHandable)msg.getContentObject();
                msgContent.Handle((MasterAgent)myAgent, msg);
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }else{
           // block();
        }

    }
}
