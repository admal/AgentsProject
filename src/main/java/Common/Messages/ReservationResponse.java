package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReservationResponse extends Message implements ICarHandable{
    AID carID;
    boolean success;
    IPosition chargerPosition;

    public ReservationResponse(IPosition chargerPosition, AID carID, boolean success){
        this.carID = carID;
        this.chargerPosition = chargerPosition;
        this.success = success;
    }

    @Override
    public void Handle(CarAgent agent, ACLMessage original) {
        if(success) {
            System.out.println(agent.getLocalName() + ": Charger destination set.");
            agent.setDestination(chargerPosition);
        }else{
            System.out.println(agent.getLocalName() + ": Charger reservation failed.");
        }
    }
}
