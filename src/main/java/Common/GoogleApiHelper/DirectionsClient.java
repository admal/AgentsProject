package Common.GoogleApiHelper;

import CarAgent.CarAgent;
import Common.Abstract.IPosition;
import Common.Position;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;

/**
 * Created by jan on 30/08/16.
 */
public class DirectionsClient {
    private DirectionsClient(){}

    private static GeoApiContext get_api_context(){
        GeoApiContext context = new GeoApiContext();
        context.setApiKey("AIzaSyA4BT5qYUsHatN2otn241CFgB9fUUJt9TQ");
        return context;
    }

    public static ArrayList<IPosition> get_directions_from_car_to_target(CarAgent car, IPosition destination){
        DirectionsResult google_api_response = null;
        DirectionsApiRequest api_request = DirectionsApi.getDirections(
                    get_api_context(),
                    car.getCurrentPosition().toString(),
                    destination.toString()
                );
        api_request.mode(TravelMode.DRIVING);
        try{
            google_api_response = api_request.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert google_api_response != null;
        return parse_google_api_response(google_api_response);
    }

    private static ArrayList<IPosition> parse_google_api_response(DirectionsResult google_api_response) {
        ArrayList<IPosition> result = new ArrayList<IPosition>();
        result.add(latlng_to_position(google_api_response.routes[0].legs[0].steps[0].startLocation));
        for(DirectionsStep step : google_api_response.routes[0].legs[0].steps){
            result.add(latlng_to_position(step.endLocation));
        }
        return result;
    }

    private static Position latlng_to_position(LatLng lng){
        return new Position((float)lng.lat, (float)lng.lng);
    }
}
