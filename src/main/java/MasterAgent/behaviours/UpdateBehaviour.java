package MasterAgent.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import MasterAgent.MasterAgent;
import jade.lang.acl.ACLMessage;

/**
 * Created by jedrek on 03.05.16.
 */
public class UpdateBehaviour extends CyclicBehaviour{
    public void action() {

        ACLMessage msg = myAgent.receive();
        if(msg != null){

        }else{
            block();
        }

    }
}
