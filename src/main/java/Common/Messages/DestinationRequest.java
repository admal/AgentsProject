package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import Common.GoogleApiHelper.DirectionsClient;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationRequest extends Message implements ICarHandable {
    IPosition clientPosition; //nie wiem czy to jest potrzebne

    public DestinationRequest(IPosition clientPosition){
        this.clientPosition = clientPosition;
    }

    public void Handle(CarAgent agent, ACLMessage original) {
        System.out.println(agent.getLocalName() + ": Destination request handled");
        if(!agent.isInMove()) //it means that car is free
        {
            ArrayList<IPosition> directions = DirectionsClient.get_directions_from_car_to_target(agent, this.clientPosition);

            ACLMessage acceptResponse = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            acceptResponse.addReceiver(original.getSender());
            try {
                acceptResponse.setContentObject(new DestinationResponse(agent.getCurrentPosition(), agent.getAID(), true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            acceptResponse.setOntology("DestinationResponse");
            agent.send(acceptResponse);
        }
        else
        {
            handle_car_in_move(agent);
        }
    }

    private void handle_car_in_move(CarAgent agent) {
        ACLMessage rejectResponse = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
        rejectResponse.setOntology("DestinationResponse");
        try {
            rejectResponse.setContentObject(new DestinationResponse(agent.getCurrentPosition(), agent.getAID(), agent.getDestination()!=null ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        agent.send(rejectResponse);
    }
}
