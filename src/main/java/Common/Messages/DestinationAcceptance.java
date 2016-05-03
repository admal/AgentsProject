package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import jade.lang.acl.ACLMessage;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationAcceptance extends Message implements ICarHandable {
    IPosition clientPosition;

    public DestinationAcceptance(IPosition clientPosition) {
        this.clientPosition = clientPosition;
    }

    public void Handle(CarAgent agent, ACLMessage original) {
        System.out.println( agent.getLocalName() + ": Destination set");
        agent.setDestination(clientPosition);
    }
}
