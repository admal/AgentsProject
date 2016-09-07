package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;
import Common.Abstract.IPosition;
import Common.AgentClasses.TransactionCharger;
import Common.GoogleApiHelper.DurationsClient;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Duration;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeResponse extends Message implements ICarHandable {
    AID aid;
    long waitingTime;
    IPosition chargerPosition;
    public ChargeResponse(AID aid, IPosition chargerPosition, long waitingTime){
        this.aid = aid;
        this.waitingTime = waitingTime;
        this.chargerPosition = chargerPosition;
    }

    public void Handle(CarAgent agent, ACLMessage original) {
        agent.chargingStations.add(new TransactionCharger(aid, waitingTime, chargerPosition)); //charging station
        long time = (long)((100 - agent.getChargedPercentage()) * 30); //each percent requires reservation for 30 sec
        if(agent.chargingStations.size() == agent.getStations().size()){
            //Chooses the best charging station and sends the time reservation
            System.out.println("Looking for the best charging station.");
            TransactionCharger bestStation = findBestChargingStation(agent, agent.chargingStations);
            if(bestStation != null){
                System.out.println("best charging station found, will now reserve.");
                ACLMessage replyMsg = new ACLMessage(ACLMessage.CONFIRM);
                try {
                    time += bestStation.getTimeToReach();
                    replyMsg.setContentObject(new ReservationRequest(time, agent.getAID())); //this car requests a reservatioon
                    replyMsg.addReceiver(bestStation.getAid());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //we also set car from this transaction its  destination
                agent.setDestination(bestStation.getPosition());

                agent.send(replyMsg);
            }else{
                System.out.println("No station found.");
            }
            agent.chargingStations.clear();
        }
    }

    private TransactionCharger findBestChargingStation(CarAgent car, List<TransactionCharger> chargingStations) {
        IPosition carPos  = car.getCurrentPosition();
        TransactionCharger charger;
        GeoApiContext gapiContext = DurationsClient.getGeoApiContext();
        Duration[] reachingDurations = DurationsClient.getChargersReachingDurationsMatrix(gapiContext, carPos, chargingStations);
        Long[] waitingTimes = new Long[chargingStations.size()];

        //getting waiting queue time and a duration to reach the station
        ListIterator it = chargingStations.listIterator();
        int i=0, j=0;
        while(it.hasNext()){
            charger = (TransactionCharger) it.next();
            waitingTimes[j] = charger.getWaitingTime();
            if(reachingDurations[j] == null){
                it.remove();
                System.out.println("removing station (can't be reached) " + charger.getAid().getLocalName());
            }else {
                chargingStations.get(i).setTimeToReach(reachingDurations[j].inSeconds);
                chargingStations.get(i).setTimeToWait(waitingTimes[j]);
                i++;
            }
            j++;
        }
        //sort charging stations by distance to them
        Collections.sort(chargingStations);
        TransactionCharger bestCharger=null;

        for (i = 0; i < chargingStations.size(); i++) {
            System.out.println("ttw: "+ chargingStations.get(i).getTimeToWait() + ", ttr: " + chargingStations.get(i).getTimeToReach());
            if(chargingStations.get(i).getTimeToWait() < chargingStations.get(i).getTimeToReach()){ //the car will register only if it doesn't have to wait in queue
                // reserve from that point in time??
                // (reserve from this moment together with time to arrive as well as loading time)
                // if car will have to wait it looks for the first station that allows for quicker start of loading
                // have to check if a car can reach a station before the battery runs out!!
                // Should add standby mode for car? for now not needed if the battery depends purely on mileage
                    bestCharger = chargingStations.get(i);
                    break;
            }
        }


        return bestCharger;
        /*
            if the best charger returned was null,
            the car should stop in place and repeat the process
            untill the charging station is found?
        */

    }



}
