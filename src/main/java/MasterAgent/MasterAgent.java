package MasterAgent;

import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.Parking;
import Common.Position;
import MasterAgent.behaviours.NewClientRequestBehaviour;
import MasterAgent.behaviours.UpdateBehaviour;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by adam on 5/3/16.
 */
public class MasterAgent extends Agent {

    public static List<Car> cars; //list of AIDs of cars
    public static List<Position> clientsLocations;
    public static List<ChargingStation> chargingStations;
    public static List<Parking> parkings;
    public static Position currentClientPosition;


    @Override
    protected void setup() {
        cars = new ArrayList<Car>();
        clientsLocations = new ArrayList<Position>();
        chargingStations = new ArrayList<ChargingStation>();
        parkings = new ArrayList<Parking>();

        addBehaviour(new UpdateBehaviour());
        addBehaviour(new NewClientRequestBehaviour(this)); //send a new client location to all cars

    }
}
