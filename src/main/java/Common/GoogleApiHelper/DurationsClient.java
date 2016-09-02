package Common.GoogleApiHelper;

import Common.Abstract.IPosition;
import Common.AgentClasses.TransactionCar;
import Common.AgentClasses.TransactionCharger;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.Duration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jedrek on 25.08.16.
 */
public class DurationsClient {

    public static GeoApiContext getGeoApiContext(){
        GeoApiContext gapiContext = new GeoApiContext();
        gapiContext.setApiKey("AIzaSyA4BT5qYUsHatN2otn241CFgB9fUUJt9TQ");

        return gapiContext;
    }

    /**
     * get API data based on positions of cars
     *
     * @param gapiContext
     * @param cars
     * @return duration for each car to reach the current client
     */
    public static Duration[] getCarDurationsMatrix(GeoApiContext gapiContext, List<TransactionCar> cars, IPosition destination) {
        int idx=0;
        Iterator<TransactionCar> it = cars.iterator();
        TransactionCar car;
        String[] origins = new String[cars.size()];
        Duration[] durations = new Duration[cars.size()];

        while(it.hasNext()){
            car = it.next();
            origins[idx] = car.getPosition().toString();
            idx++;
        }

        DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(gapiContext, origins,
                new String[]{destination.toString()});

        //synchronous await for request completion
        try {
            DistanceMatrix distanceMatrix = req.await();
            idx = 0;
            for(DistanceMatrixRow row : distanceMatrix.rows){
                durations[idx] = row.elements[0].duration;
                idx++;
            }
        } catch (Exception e) {
            System.out.println("Api connection failed");
        }
        return durations;
    }

    /**
     * get API data based on positions of Chargers to a car
     *
     * @param gapiContext
     * @param chargers
     * @return duration for a car to reach the chargers
     */
    public static Duration[] getChargersReachingDurationsMatrix(GeoApiContext gapiContext, IPosition carPos,  List<TransactionCharger> chargers) {
        int idx=0;
        Iterator<TransactionCharger> it = chargers.iterator();
        TransactionCharger charger;
        String[] destinations = new String[chargers.size()];
        Duration[] durations = new Duration[chargers.size()];

        while(it.hasNext()){
            charger = it.next();
            destinations[idx] = charger.getPosition().toString();
            idx++;
        }

        DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(gapiContext, new String[]{carPos.toString()},
                destinations);

        //synchronous await for request completion
        try {
            DistanceMatrix distanceMatrix = req.await();
            idx = 0;
            for(DistanceMatrixElement road : distanceMatrix.rows[0].elements){ //since we have one origin and many destinations
                durations[idx] = road.duration;
                idx++;
            }
        } catch (Exception e) {
            System.out.println("Api connection failed");
        }
        return durations;
    }


}
