package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.Messages.ChargeRequest;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by adam on 5/3/16.
 */
public class CheckingFuelBehaviour extends TickerBehaviour {
    public CheckingFuelBehaviour(Agent a, long period) {
        super(a, period);
    }

    protected void onTick() {
        CarAgent carAgent = (CarAgent)myAgent;
        if(carAgent.isInMove())
        {
            carAgent.setChargedLevel(carAgent.getChargedLevel()-1 );
            if (carAgent.getChargedLevel() < 20)
            {
                ChargeRequest request = new ChargeRequest(carAgent.getAID());
                ACLMessage requestMessage = new ACLMessage(ACLMessage.REQUEST);

                for (ChargingStation station : carAgent.getStations()) {
                    requestMessage.addReceiver(station.getAid());
                }
                
                try {
                    requestMessage.setContentObject(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                carAgent.send(requestMessage);
                System.out.println(carAgent.getLocalName() + ": sent charging request");
            }
        }
    }
}
