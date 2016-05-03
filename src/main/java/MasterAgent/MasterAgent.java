package MasterAgent;

import MasterAgent.behaviours.UpdateBehaviour;
import jade.core.Agent;

import java.util.List;

/**
 * Created by adam on 5/3/16.
 */
public class MasterAgent extends Agent {
    public static List cars; //list of AIDs of cars
    public static List cientsLocations;
    public static List chargers;
    public static List parkings;
    @Override
    protected void setup() {
        addBehaviour(new UpdateBehaviour());


    }
}
