package WebServlets;

import Common.AgentClasses.Car;
import Common.AgentType;
import Common.Position;
import Common.Starter;
import Common.WebModels.CarAgentWebModel;
import MasterAgent.IMasterAgent;
import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Duration;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

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
        try {
            Object[] args = new Object[1];
            args[0] = new Position((float)52.26881, (float)21.04666); //TMP!!!
            AgentController carAgent = Starter.mainContainer.createNewAgent("auto" + (WebGlobals.getInstance().masterAgent.getCars().size()+1) ,"CarAgent.CarAgent", args);
            carAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
            response.sendError(500, "Could not create car agent!");
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        IMasterAgent master = WebGlobals.getInstance().masterAgent;
        if (master == null) {
            response.getWriter().write(new Gson().toJson(new Object[0]));
            return;
        }

        List<Car> cars = master.getCars();
        List<CarAgentWebModel> viewModels = new ArrayList<CarAgentWebModel>();
        for (Car car :
                cars) {
            viewModels.add(new CarAgentWebModel(AgentType.Car,
                    car.getPosition(),
                    car.getDestination(),
                    car.getCharge(),
                    car.getAid().getLocalName(),
                    car.getSpeed(),
                    car.getFuelBurning()));
        }

        String retJson = new Gson().toJson(viewModels);
        response.getWriter().write(retJson);
    }
}
