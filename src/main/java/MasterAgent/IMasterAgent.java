package MasterAgent;

import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.Parking;

import java.util.List;

/**
 * Created by adam on 6/4/16.
 */
public interface IMasterAgent {
    int GetCarSize();
    List<Car> getCars();
    List<ChargingStation> getChargingStations();
    List<Parking> getParkings();
}
