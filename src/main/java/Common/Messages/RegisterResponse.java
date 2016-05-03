package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import jade.lang.acl.ACLMessage;

import Common.AgentClasses.ChargingStation;

import java.util.List;

/**
 * Created by adam on 5/3/16.
 */
public class RegisterResponse extends Message implements ICarHandable
{
    public boolean registered;
    public void Handle(CarAgent agent, ACLMessage original) {
        if (!registered) {
            agent.doDelete();
            return;
        }

        System.out.println( agent.getLocalName() + ": Registered");
    }
    public List<ChargingStation> chargingStations;

    public RegisterResponse(boolean registered, List<ChargingStation> chargingStations) {
        this.registered = registered;
        this.chargingStations = chargingStations;
    }
}
