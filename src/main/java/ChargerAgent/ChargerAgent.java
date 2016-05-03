package ChargerAgent;

import Common.IPosition;
import Common.Position;
import jade.core.Agent;

/**
 * Created by adam on 5/3/16.
 */
public class ChargerAgent extends Agent {
    private Position position;
    /**
     * Waiting time in miliseconds
     */
    private long waitingTime;
    public ChargerAgent(){
        this.waitingTime = 0;
        this.position = new Position(2,1);
    }



}
