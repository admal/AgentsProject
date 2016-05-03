package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import jade.lang.acl.ACLMessage;

/**
 * Created by adam on 5/3/16.
 */
public class RegisterResponse extends Message implements ICarHandable
{
    public boolean registered;
    public void Handle(CarAgent agent, ACLMessage original) {
        if (!registered) {
            agent.doDelete();
            return;
        }

        System.out.println( agent.getLocalName() + ": Registered");
    }
}
