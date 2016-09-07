package Common;

import MasterAgent.IMasterAgent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 6/4/16.
 */
/*
-gui master:MasterAgent.MasterAgent;charger1:ChargerAgent.ChargerAgent;auto1:CarAgent.CarAgent
 */
public class Starter
{
    public static AgentContainer mainContainer = null;
    public static IMasterAgent Start(List<AgentStartModel> cars, List<AgentStartModel> stations )
    {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);
        //create main container
         mainContainer = runtime.createMainContainer(new ProfileImpl(null, 8888, null));

        //AgentContainer container = runtime.createAgentContainer(profile);
        //create master agent
        AgentController masterAgent = null;
        try {
//            masterAgent = mainContainer.createNewAgent("master","MasterAgent.MasterAgent", null);
//            masterAgent.start();
            AgentController rmaAgent = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
            rmaAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        //create stations
        for (int i = 0;  i < stations.size(); i++)
        {
            try {
                Object[] args = new Object[1];
                args[0] = stations.get(i).position;
                AgentController stationAgent = mainContainer.createNewAgent("charger"+stations.get(i).id , "ChargerAgent.ChargerAgent", args );
                stationAgent.start();
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0;  i < cars.size(); i++)
        {
            try {
                Object[] args = new Object[1];
                args[0] = cars.get(i).position;
                AgentController carAgent = mainContainer.createNewAgent("auto"+cars.get(i).id , "CarAgent.CarAgent", args);
                carAgent.start();
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }
        

        try {
            //IMasterAgent agent = mainContainer.getAgent("master").getO2AInterface(IMasterAgent.class);
            return masterAgent.getO2AInterface(IMasterAgent.class);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String [] args)
    {
        ArrayList<AgentStartModel> stations = new ArrayList<AgentStartModel>();
        stations.add(new AgentStartModel(new Position(1,2),1));
        stations.add(new AgentStartModel(new Position(3,5),2));
        stations.add(new AgentStartModel(new Position(4,2),3));

        ArrayList<AgentStartModel> cars = new ArrayList<AgentStartModel>();
        cars.add(new AgentStartModel(new Position(52.26881f, 21.04666f),1));
        IMasterAgent a = Start(cars, stations);
        System.out.println(a.GetCarSize());
    }
}
