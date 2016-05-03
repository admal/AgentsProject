package ChargerAgent;

import ChargerAgent.Behaviours.ReceiveMessageBehaviour;
import ChargerAgent.Behaviours.UpdateWaitingTimeBehaviour;
import Common.AgentType;
import Common.Behaviours.RegisterBehaviour;
import Common.Position;
import jade.core.Agent;

/**
 * Created by adam on 5/3/16.
 */
public class ChargerAgent extends Agent {
    private Position position;
    /**
     * Waiting time in seconds
     */
    private long waitingTime;
    public ChargerAgent(){
        this.waitingTime = 0;
        this.position = new Position(2,1);
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
    public void onTick(){
        if(waitingTime-1>=0) waitingTime-=1;
    }
}
