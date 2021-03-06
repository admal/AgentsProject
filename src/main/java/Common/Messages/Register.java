package Common.Messages;

import Common.Abstract.IMasterHandable;
import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.Parking;
import Common.AgentType;
import Common.Abstract.IPosition;
import MasterAgent.MasterAgent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by adam on 5/3/16.
 */
public class Register extends Message implements IMasterHandable
{
    public AgentType type;
    public AID agentId;
    public IPosition currPosition;

    public Register(AgentType type, AID agentId, IPosition currPosition) {
        this.type = type;
        this.agentId = agentId;
        this.currPosition = currPosition;
    }

    public void Handle(MasterAgent agent, ACLMessage msg) {
        System.out.println("received REGISTER message on MASTER");

        switch(type){
            case Car:
                agent.cars.add(new Car(agentId, currPosition));
                System.out.println("Car registered.");
                break;
            case ChargingStation:
                agent.chargingStations.add(new ChargingStation(agentId, currPosition));
                System.out.println("CharingStation registered.");
                break;
            case Parking:
                agent.parkings.add(new Parking(currPosition));
                System.out.println("Parking registered.");
                break;
            default:
                System.out.println("Unknown type of agent");
        }
        //respond with RegisterResponse
        ACLMessage responseMsg = msg.createReply();
        try {
//            System.out.println("Stations:");
//            for (ChargingStation station :
//                    agent.chargingStations) {
//                System.out.println(station.getAid().getLocalName());
//            }
            for (Car car :
                    agent.cars) {
                responseMsg.addReceiver(car.getAid());
            }


            responseMsg.setContentObject(new RegisterResponse(true, agent.chargingStations));
        } catch (IOException e) {
            e.printStackTrace();
        }
        agent.send(responseMsg);
        System.out.println("Master sent back RegisterResponse");


    }
}

