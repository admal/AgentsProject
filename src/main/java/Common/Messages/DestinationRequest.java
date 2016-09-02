package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import Common.AgentClasses.ChargingStation;
import Common.GoogleApiHelper.DirectionsClient;
import Common.Route;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationRequest extends Message implements ICarHandable {
    private IPosition clientPosition;

    public DestinationRequest(IPosition clientPosition){
        this.clientPosition = clientPosition;
    }

    public void Handle(CarAgent agent, ACLMessage original) {
        System.out.println(agent.getLocalName() + ": Destination request handled");
        if(!agent.isInMove())
        {
            handleCarAvailable(agent, original);
        }
        else
        {
            rejectRequest(agent);
        }
    }

    private void handleCarAvailable(CarAgent agent, ACLMessage original) {
        Route route = DirectionsClient.getDirectionsToTarget(agent, this.clientPosition);
        if(agent.hasEnoughFuelForTrip(route))
            acceptRequest(agent, original, route);
        else{
            rejectRequest(agent);
            makeChargeRequest(agent);
        }
    }

    private void makeChargeRequest(CarAgent agent) {
        ChargeRequest request = new ChargeRequest(agent.getAID());
        ACLMessage requestMessage = new ACLMessage(ACLMessage.REQUEST);

        for (ChargingStation station : agent.getStations()) {
            requestMessage.addReceiver(station.getAid());
        }

        try {
            requestMessage.setContentObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        agent.send(requestMessage);
    }

    private void acceptRequest(CarAgent agent, ACLMessage original, Route route) {
        ACLMessage acceptResponse = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
        acceptResponse.addReceiver(original.getSender());
        try {
            acceptResponse.setContentObject(new DestinationResponse(agent.getCurrentPosition(), route, agent.getAID(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptResponse.setOntology("DestinationResponse");
        agent.send(acceptResponse);
    }

    private void rejectRequest(CarAgent agent) {
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
