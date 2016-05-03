package Common.Messages;

import CarAgent.CarAgent;
import ChargerAgent.ChargerAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IChargerHandable;
import jade.lang.acl.ACLMessage;

import Common.AgentClasses.ChargingStation;

import java.util.List;

/**
 * Created by adam on 5/3/16.
 */
public class RegisterResponse extends Message implements ICarHandable, IChargerHandable
{
    public boolean registered;
    public void Handle(CarAgent agent, ACLMessage original) {
        if (!registered) {
            agent.doDelete();
            return;
        }
        agent.UpdateStations(chargingStations);
        System.out.println( agent.getLocalName() + ": Registered");
    }
    public List<ChargingStation> chargingStations;

    public RegisterResponse(boolean registered, List<ChargingStation> chargingStations) {
        this.registered = registered;
        this.chargingStations = chargingStations;
    }

    public void Handle(ChargerAgent agent) {
        System.out.println(agent.getLocalName()+": Registered");
    }
}
