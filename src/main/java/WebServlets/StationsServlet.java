package WebServlets;


import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.Parking;
import Common.AgentType;
import Common.WebModels.StationaryAgentWebModel;
import MasterAgent.IMasterAgent;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StationsServlet")
public class StationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        IMasterAgent master = WebGlobals.getInstance().masterAgent;
        if (master == null) {
            response.getWriter().write(new Gson().toJson(new Object[0]));
            return;
        }

        List<ChargingStation> stations =master.getChargingStations();
        List<Parking> parkings = master.getParkings();
        List<StationaryAgentWebModel> viewModels = new ArrayList<StationaryAgentWebModel>();
        for (ChargingStation station : stations)
        {
            viewModels.add(new StationaryAgentWebModel(station.getAid().getLocalName(),AgentType.ChargingStation, station.getPosition()));
        }
        for (Parking parking : parkings)
        {
            viewModels.add(new StationaryAgentWebModel("parking", AgentType.Parking, parking.getPosition()));
        }

        String retJson = new Gson().toJson(viewModels);
        response.getWriter().write(retJson);
    }
}
