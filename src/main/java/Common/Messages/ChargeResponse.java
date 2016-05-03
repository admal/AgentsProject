package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import jade.lang.acl.ACLMessage;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeResponse extends Message implements ICarHandable {
    long waitingTime;
    public ChargeResponse(long waitingTime){
        this.waitingTime = waitingTime;
    }

    public void Handle(CarAgent agent, ACLMessage original) {
        System.out.println("Charge response received");

    }
}
