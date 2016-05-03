package ChargerAgent;

import Common.IPosition;
import jade.core.Agent;

/**
 * Created by adam on 5/3/16.
 */
public class ChargerAgent extends Agent {
    private IPosition position;
    /**
     * Waiting time in miliseconds
     */
    private long waitingTime;
    public ChargerAgent(){
        this.waitingTime = 0;

    }
}
