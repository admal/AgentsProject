package ChargerAgent.Behaviours;

import ChargerAgent.ChargerAgent;
import Common.Messages.ChargeResponse;
import Common.Messages.ReservationResponse;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class SendReservationResponseBehaviour extends OneShotBehaviour {
    private ChargerAgent agent;
    private AID receiver;
    public SendReservationResponseBehaviour(Agent a, AID receiver) {
        super(a);
        this.agent = (ChargerAgent) a;
        this.receiver = receiver;
    }
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ReservationResponse response = new ReservationResponse(agent.getPosition(), myAgent.getAID(),true);
        System.out.println("reservation response");
        msg.setOntology("reservationResponse");
        try {
            msg.setContentObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.addReceiver(receiver);
        myAgent.send(msg);
    }
}
