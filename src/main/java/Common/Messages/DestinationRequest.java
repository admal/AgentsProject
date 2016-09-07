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
            rejectRequest(agent, original);
        }
    }

    private void handleCarAvailable(CarAgent agent, ACLMessage original) {
        Route route = DirectionsClient.getDirectionsToTarget(agent, this.clientPosition);
        if(agent.hasEnoughFuelForTrip(route) && agent.getChargedPercentage() >= 20f)
            acceptRequest(agent, original, route);
        else{
            rejectRequest(agent, original);
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
        System.out.println("car takes part in transaction " + agent.getLocalName());
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

    private void rejectRequest(CarAgent agent, ACLMessage original) {
        System.out.println("car desn't take part in transaction " + agent.getLocalName());
        ACLMessage rejectResponse = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
        rejectResponse.setOntology("DestinationResponse");
        rejectResponse.addReceiver(original.getSender());
        try {
            rejectResponse.setContentObject(new DestinationResponse(agent.getCurrentPosition(), agent.getAID(), false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        agent.send(rejectResponse);
    }
}
