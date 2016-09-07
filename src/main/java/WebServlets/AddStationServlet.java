package WebServlets;

import Common.Position;
import Common.Starter;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by adam on 6/9/16.
 */
public class AddStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float x = Float.parseFloat(request.getParameter("x"));
        float y = Float.parseFloat(request.getParameter("y"));
        //int id = Integer.parseInt(request.getParameter("id"));
        int id = WebGlobals.getInstance().masterAgent.getChargingStations().size() + 1;
        //creating agent
        try {
            Object[] args = new Object[1];
            args[0] = new Position(x,y);
            AgentController stationAgent = Starter.mainContainer.createNewAgent("charger"+id , "ChargerAgent.ChargerAgent", args );
            stationAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
