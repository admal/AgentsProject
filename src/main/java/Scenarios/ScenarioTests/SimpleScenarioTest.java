package Scenarios.ScenarioTests;

import Scenarios.Exceptions.ScenarioException;
import Scenarios.Exceptions.ScenarioOnExectionException;
import Scenarios.SimpleSceneScenario;
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import static Common.Starter.mainContainer;

/**
 * Created by adam on 9/7/16.
 */
public class SimpleScenarioTest {
    public static void main(String [] args) {
        SimpleSceneScenario testScenario = new SimpleSceneScenario();

        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);
        //create main container
        mainContainer = runtime.createMainContainer(new ProfileImpl(null, 8888, null));

        try {
            AgentController rmaAgent = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
            rmaAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }

        try {
            testScenario.Start(false);
        } catch (ScenarioException e) {
            e.printStackTrace();
        } catch (ScenarioOnExectionException e) {
            e.printStackTrace();
        }
    }
}
