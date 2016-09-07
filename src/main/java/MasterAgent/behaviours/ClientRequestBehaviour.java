package MasterAgent.behaviours;

import Common.Abstract.IPosition;
import Common.Globals;
import Common.Messages.DestinationRequest;
import Common.Position;
import MasterAgent.MasterAgent;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Random;

/**
 * Created by jedrek on 08.06.16.
 */
public class ClientRequestBehaviour extends TickerBehaviour {
    public ClientRequestBehaviour(Agent a, int delay ) {
        super(a, delay);
    }

    protected void onTick() {
        MasterAgent master = (MasterAgent )myAgent;
        if(master.currentClientPosition != null)
            System.out.println(master.currentClientPosition.toString());

        for( IPosition x : master.clientsLocations){
            System.out.println(x.toString() + " ; ");
        }

        if(!master.clientsLocations.isEmpty() || master.currentClientPosition != null) { //check each iteration if queue is empty
           if(master.currentClientPosition == null && !master.clientsLocations.isEmpty()) {
               master.currentClientPosition = master.clientsLocations.remove(0);
           }
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            try {
                for (int i = 0; i < master.cars.size(); i++) {
                    msg.addReceiver(master.cars.get(i).getAid());
                }

                msg.setContentObject(new DestinationRequest( master.currentClientPosition));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Sending position of a new client to all cars");
            myAgent.send(msg);
        }
    }

}
