package Common.Messages;

import Common.Abstract.IMasterHandable;
import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentType;
import MasterAgent.MasterAgent;
import jade.lang.acl.ACLMessage;

/**
 * Created by adam on 9/2/16.
 */
public class Status extends Message implements IMasterHandable {

    public AgentType type;
    public Object agentInformation;
    @Override
    public void Handle(MasterAgent agent, ACLMessage msg) {
        switch (type) {
            case Car:
                Car carInfo = (Car) agentInformation;
                for (Car car : agent.getCars()) {
                    if (carInfo.getAid().equals(car.getAid())) {
                        car.setDestination(carInfo.getDestination());
                        car.setCharge(carInfo.getCharge());
                        car.setChargerPosition(carInfo.getChargerPosition());
                        car.setPosition(carInfo.getPosition());
                        car.setSpeed(carInfo.getSpeed());
                        car.setFuelBurning(carInfo.getFuelBurning());
                    }
                }
                break;
            case ChargingStation:
                ChargingStation stationInfo = (ChargingStation) agentInformation;
                break;
            case Parking:
                break;
        }
    }
}
