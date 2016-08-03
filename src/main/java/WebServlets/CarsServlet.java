package WebServlets;

import Common.AgentClasses.Car;
import Common.AgentType;
import Common.Position;
import Common.WebModels.CarAgentWebModel;
import MasterAgent.IMasterAgent;
import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DistanceMatrix;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janbaraniewski on 04/06/16.
 */
@WebServlet(name = "CarsServlet")
public class CarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        IMasterAgent master = WebGlobals.getInstance().masterAgent;
        if (master == null) {
            response.getWriter().write("null");
            return;
        }

        List<Car> cars = master.getCars();
        List<CarAgentWebModel> viewModels = new ArrayList<CarAgentWebModel>();
        for (Car car :
                cars) {
            viewModels.add(new CarAgentWebModel(AgentType.Car, car.getPosition(), car.getDestination(), 100, car.getAid().getLocalName()));
        }

        GeoApiContext gapiContext = new GeoApiContext();
        gapiContext.setApiKey("AIzaSyBUuzxbG2yS8ghz0lMFDF9Wr8A9ZKu8XXI");

        cars.get(0).setDestination(new Position(52.25469f, 21.03508f)); //set hardcoded charger loaction as destination TODO get/set destinations
        String destination = cars.get(0).getDestination().toString();
        String origin = cars.get(0).getPosition().toString();

        DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(gapiContext, new String[]{origin},
                new String[]{destination});

        req.setCallback(new PendingResult.Callback<DistanceMatrix>() {
            public void onResult(DistanceMatrix distanceMatrix) {
                System.out.println("shit works");
                System.out.println("distance: " + distanceMatrix.rows[0].elements[0].distance);
                System.out.println("duration: " + distanceMatrix.rows[0].elements[0].duration);
            }

            public void onFailure(Throwable throwable) {
                System.out.println("shit's shit");
            }
        });



        String retJson = new Gson().toJson(viewModels);
        response.getWriter().write(retJson);
    }
}
