package MasterAgent;

import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.Parking;
import Common.Position;
import MasterAgent.behaviours.NewClientRequestBehaviour;
import MasterAgent.behaviours.UpdateBehaviour;
import jade.core.Agent;

import java.util.List;

/*
 * Created by adam on 5/3/16.
 */
public class MasterAgent extends Agent {
    public static List<Car> cars; //list of AIDs of cars
    public static List<Position> cientsLocations;
    public static List<ChargingStation> chargingStations;
    public static List<Parking> parkings;
    @Override
    protected void setup() {
        addBehaviour(new UpdateBehaviour());
        addBehaviour(new NewClientRequestBehaviour(this, 5000));

    }
}
