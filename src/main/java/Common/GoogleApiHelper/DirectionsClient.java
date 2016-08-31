package Common.GoogleApiHelper;

import CarAgent.CarAgent;
import Common.Abstract.IPosition;
import Common.Position;
import Common.Route;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.List;

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

    public static Route get_directions_to_target(CarAgent car, IPosition destination){
        DirectionsApiRequest api_request = construct_directions_request(car, destination);
        DirectionsResult google_api_response = await_response(api_request);
        assert google_api_response != null;
        return parse_google_api_response(google_api_response);
    }

    public static Route get_directions_to_target_through_point(CarAgent car, IPosition destination, IPosition _point){
        DirectionsApiRequest api_request = construct_directions_request(car, destination);
        api_request.waypoints(_point.toString());
        DirectionsResult google_api_response = await_response(api_request);
        assert google_api_response != null;
        return parse_google_api_response(google_api_response);
    }

    private static DirectionsResult await_response(DirectionsApiRequest api_request) {
        DirectionsResult google_api_response = null;
        try{
            google_api_response = api_request.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return google_api_response;
    }

    private static DirectionsApiRequest construct_directions_request(CarAgent car, IPosition destination) {
        DirectionsApiRequest api_request = DirectionsApi.getDirections(
                    get_api_context(),
                    car.getCurrentPosition().toString(),
                    destination.toString()
                );
        api_request.mode(TravelMode.DRIVING);
        return api_request;
    }

    private static Route parse_google_api_response(DirectionsResult google_api_response) {
        List<IPosition> points = new ArrayList<IPosition>();
        points.add(latlng_to_position(google_api_response.routes[0].legs[0].steps[0].startLocation));
        for(DirectionsStep step : google_api_response.routes[0].legs[0].steps){
            points.add(latlng_to_position(step.endLocation));
        }
        long distance = google_api_response.routes[0].legs[0].distance.inMeters;
        long time = google_api_response.routes[0].legs[0].duration.inSeconds;
        Route route = new Route(points,distance,time);
        return route;
    }

    private static Position latlng_to_position(LatLng lng){
        return new Position((float)lng.lat, (float)lng.lng);
    }
}
