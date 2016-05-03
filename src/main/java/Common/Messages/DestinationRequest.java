package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import Common.Position;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationRequest extends Message implements ICarHandable {
    IPosition clientPosition; //nie wiem czy to jest potrzebne

    public DestinationRequest(Position clientPosition){
        this.clientPosition = clientPosition;
    }

    public void Handle(CarAgent agent, ACLMessage original) {
        if(agent.getDestination() == null) //it means that car is free
        {
            ACLMessage acceptResponse = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            acceptResponse.addReceiver(original.getSender());
            try {
                acceptResponse.setContentObject(new DestinationResponse(agent.getCurrentPosition()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            acceptResponse.setOntology("DestinationResponse");
            agent.send(acceptResponse);
        }
        else
        {
            ACLMessage rejectResponse = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
            rejectResponse.setOntology("DestinationResponse");
            try {
                rejectResponse.setContentObject(new DestinationResponse(null));
            } catch (IOException e) {
                e.printStackTrace();
            }
            agent.send(rejectResponse);
        }
    }
}
