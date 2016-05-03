package MasterAgent.behaviours;

import Common.Messages.*;
import Common.Position;
import MasterAgent.MasterAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import Common.Globals;

import static Common.AgentType.Car;


/**
 * Created by jedrek on 03.05.16.
 * Sends each 5 seconds a random Postion (from within bounds) to all cars
 */
public class NewClientRequestBehaviour extends TickerBehaviour {
    public NewClientRequestBehaviour(Agent a, long period) {
        super(a, period);
    }

    protected void onTick() {
        MasterAgent master = (MasterAgent )myAgent;
        if(master.cars.size()==0)
            return;
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        try {

            for(int i=0; i<master.cars.size(); i++){
                msg.addReceiver(master.cars.get(i).getAid());
            }
            Random r = new Random();
            Position pos = new Position(r.nextInt()%Globals.getInstance().MAX_WIDTH+1, r.nextInt()%Globals.getInstance().MAX_HEIGHT+1);

            msg.setContentObject(new DestinationRequest(pos));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sending position of a new client to all cars");
        myAgent.send(msg);
    }

}
