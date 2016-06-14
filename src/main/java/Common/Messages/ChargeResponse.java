package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.TransactionCharger;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeResponse extends Message implements ICarHandable {
    long waitingTime;
    IPosition chargerPosition;
    public ChargeResponse(IPosition chargerPosition, long waitingTime){
        this.waitingTime = waitingTime;
        this.chargerPosition = chargerPosition;
    }

    public void Handle(CarAgent agent, ACLMessage original) {

        agent.chargingStations.add(new TransactionCharger(waitingTime, chargerPosition)); //charging station
        long time = (long)((100 - agent.getChargedLevel()) * 30); //each percent requires reservation for 30 sec
        if(agent.chargingStations.size() == agent.getStations().size()){
            //Chooses the best charging station and sends the time reservation
            ChargingStation bestStation = findBestChargingStation(agent.chargingStations);
            System.out.println("best charging station found, will now reserve.");
            if(bestStation != null){
                ACLMessage replyMsg = new ACLMessage(ACLMessage.CONFIRM);
                try {
                    replyMsg.setContentObject(new ReservationRequest(time, agent.getAID())); //this car requests a reservatioon
                    replyMsg.addReceiver(bestStation.getAid());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //we also set car from this transaction its  destination
                //TODO send the client request to the queue of a server
                agent.setDestination(bestStation.getPosition());

                agent.send(replyMsg);
            }
            agent.chargingStations.clear();
        }
    }

    //TODO write the algorithm
    private ChargingStation findBestChargingStation(List<TransactionCharger> chargingStations) {
        return null;
    }
}
