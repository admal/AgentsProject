package WebServlets;

import Common.AgentClasses.Car;
import Common.AgentStartModel;
import Common.Position;
import Common.Starter;

import MasterAgent.IMasterAgent;
import jade.core.Agent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by janbaraniewski on 04/06/16.
 */
public class StartSimulationServlet extends javax.servlet.http.HttpServlet {
    IMasterAgent masterAgent = null;
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
        stations.add(new AgentStartModel(new Position(1,2),1));
        stations.add(new AgentStartModel(new Position(3,5),2));
        stations.add(new AgentStartModel(new Position(4,2),3));

        ArrayList<AgentStartModel> cars = new ArrayList<AgentStartModel>();
        cars.add(new AgentStartModel(new Position(0,0),1));
        masterAgent =  Starter.Start(cars, stations);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("[");
        for(Car car :masterAgent.getCars()){
            response.getWriter().write("car"+car.getAid()+": [");
            response.getWriter().write("positionX: '"+car.getPosition().GetX()+"',positionY: '"+car.getPosition().GetY()+"'");
            response.getWriter().write("], ");
        }
        response.getWriter().write("]");
    }
}
