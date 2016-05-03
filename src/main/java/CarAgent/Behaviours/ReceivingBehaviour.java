package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/**
 * Created by adam on 5/3/16.
 */
public class ReceivingBehaviour extends CyclicBehaviour {

    public ReceivingBehaviour(CarAgent a) {
        super(a);
    }

    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null)
        {
            try {
                ICarHandable handable = (ICarHandable) msg.getContentObject();
                if (handable != null)
                    handable.Handle((CarAgent)myAgent);
                else
                    System.out.println("Unknown message!");

            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
    }
}
