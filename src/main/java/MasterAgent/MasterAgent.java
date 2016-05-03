package MasterAgent;

import Common.Abstract.IPosition;
import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.Parking;
import Common.AgentClasses.TransactionCar;
import Common.Position;
import MasterAgent.behaviours.NewClientRequestBehaviour;
import MasterAgent.behaviours.UpdateBehaviour;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Created by adam on 5/3/16.
 */
public class MasterAgent extends Agent {

    public List<Car> cars; //list of AIDs of cars
    public List<IPosition> clientsLocations;
    public List<ChargingStation> chargingStations;
    public List<Parking> parkings;
    public Position currentClientPosition;
    public List<TransactionCar> carsInCurrentTransaction;

    @Override
    protected void setup() {
        cars = new ArrayList<Car>();
        clientsLocations = new ArrayList<IPosition>();
        chargingStations = new ArrayList<ChargingStation>();
        parkings = new ArrayList<Parking>();
        carsInCurrentTransaction = new ArrayList<TransactionCar>();

        addBehaviour(new UpdateBehaviour());
        addBehaviour(new NewClientRequestBehaviour(this)); //send a new client location to all cars

    }
}
