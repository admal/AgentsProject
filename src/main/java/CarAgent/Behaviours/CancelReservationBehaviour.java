package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.TransactionCharger;
import Common.Messages.ReservationCancel;
import Common.Messages.ReservationResponse;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by jedrek on 02.09.16.
 */
public class CancelReservationBehaviour extends OneShotBehaviour{

    private AID receiver; //AID of charger agent in which we cancel
    private CarAgent car;

    public CancelReservationBehaviour(Agent a, AID receiver) {
        super(a);
        car = (CarAgent) a;
        Iterator<TransactionCharger> it = car.chargingStations.listIterator();
        TransactionCharger charger = null;
        while(it.hasNext()){
            charger = it.next();
            if(charger.getPosition() == car.getChargingPosition() && car.getChargingPosition() != null) {
                this.receiver = charger.getAid();
                break;
            }
        }
    }

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ReservationCancel response = new ReservationCancel(myAgent.getAID());
        System.out.println("reservation cancel");
        msg.setOntology("reservationCancel");
        try {
            msg.setContentObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.addReceiver(receiver);
        myAgent.send(msg);
    }
}
