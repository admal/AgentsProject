package ChargerAgent;
import ChargerAgent.Behaviours.ReceiveMessageBehaviour;
import ChargerAgent.Behaviours.SendChargedInformationBehaviour;
import ChargerAgent.Behaviours.UpdateWaitingTimeBehaviour;
import Common.AgentClasses.ChargingCar;
import Common.AgentClasses.ChargingStation;
import Common.AgentType;
import Common.Behaviours.RegisterBehaviour;
import Common.Position;
import jade.core.AID;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by adam on 5/3/16.
 */
public class ChargerAgent extends Agent {
    private Position position;
    /**
     * Waiting time in seconds
     */
    private long waitingTime;
    public List<ChargingCar> chargingQueue;
    public ChargerAgent(){

    }

    public Position getPosition() {
        return position;
    }

    @Override
    protected void setup() {
        super.setup();
        Object[] args = getArguments();

        chargingQueue = new ArrayList<ChargingCar>();
        this.waitingTime = 0;
        this.position = (Position)args[0];
        this.addBehaviour(new RegisterBehaviour(this, AgentType.ChargingStation,position));
        this.addBehaviour(new ReceiveMessageBehaviour(this));
        this.addBehaviour(new UpdateWaitingTimeBehaviour(this,1000));
    }

    public long getWaitingTime(){
        return this.waitingTime;
    }
    public void addToWaitingTime(long additionalTime){
        this.waitingTime+=additionalTime;
        System.out.println("Waiting time changed to "+waitingTime);
    }
    public void cancelReservation(AID aid){
        ChargingCar car;
        ListIterator it = chargingQueue.listIterator();
        while(it.hasNext()){
            car = (ChargingCar) it.next();
            if(car.getAid() == aid) {
                System.out.println("reservation canceled");
                waitingTime -= car.getOccupationTime();
                chargingQueue.remove(car);
            }
        }
    }
    public void onTick(){
        if(waitingTime-1>=0) {
            waitingTime -= 1;
            chargingQueue.get(0).setOccupationTime(chargingQueue.get(0).getOccupationTime() - 1);
        }
        if(chargingQueue.size() != 0) {
            if (chargingQueue.get(0).getOccupationTime() <= 0) {
                System.out.println("car " + chargingQueue.get(0).getAid() + " finished charging, removing from queue");
                ChargingCar chargedCar = chargingQueue.remove(0);
                this.addBehaviour(new SendChargedInformationBehaviour(this, chargedCar.getAid()));
            }
        }
    }
}
