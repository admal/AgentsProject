package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import jade.core.AID;
import jade.core.event.MessageAdapter;
import jade.lang.acl.ACLMessage;

/**
 * Created by jedrek on 02.09.16.
 */
public class ChargedInformation extends Message implements ICarHandable{

    float batteryPercentage;

    public ChargedInformation(float batteryPercentage){
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public void Handle(CarAgent agent, ACLMessage original) {
        agent.setChargingPosition(null);
        agent.setChargedPercentage(this.batteryPercentage);
        System.out.println(agent.getLocalName() + ": is fully loaded.");
    }
}
