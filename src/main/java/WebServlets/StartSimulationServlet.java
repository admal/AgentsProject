package WebServlets;

import Common.AgentStartModel;
import Common.Position;
import Common.Starter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by janbaraniewski on 04/06/16.
 */
public class StartSimulationServlet extends javax.servlet.http.HttpServlet {
    //IMasterAgent masterAgent = null;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        ArrayList<AgentStartModel> stations = new ArrayList<AgentStartModel>();
//        stations.add(new AgentStartModel(new Position(1,2),1));
//        stations.add(new AgentStartModel(new Position(3,5),2));
//        stations.add(new AgentStartModel(new Position(4,2),3));
//
//        ArrayList<AgentStartModel> cars = new ArrayList<AgentStartModel>();
//        cars.add(new AgentStartModel(new Position(0,0),1));
//        masterAgent =  Starter.Start(cars, stations);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write("{agent: agent1}");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ArrayList<AgentStartModel> stations = new ArrayList<AgentStartModel>();
        stations.add(new AgentStartModel(new Position(52.25469f,21.03508f),1));
        stations.add(new AgentStartModel(new Position(52.23917f, 21.05124f),2));
        stations.add(new AgentStartModel(new Position( 52.25491f, 21.03487f),3));

        ArrayList<AgentStartModel> cars = new ArrayList<AgentStartModel>();
        cars.add(new AgentStartModel(new Position(52.26881f, 21.04666f),5));
        WebGlobals.getInstance().masterAgent =  Starter.Start(cars, stations);

        // ADDING A RANDOM CLIENT TO SEND A CAR TO
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(20,40));
        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(30,40));
        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(40,20));
    }
}
