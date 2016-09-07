package Scenarios;

import Common.AgentStartModel;
import MasterAgent.IMasterAgent;
import WebServlets.WebGlobals;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.List;

/**
 * Created by adam on 9/6/16.
 */
public abstract class Scenario {

    public abstract String getName();
    public abstract String getDescription();
    /**
     * Gets static agents container of object
     * @return container with agents or null
     */
    public abstract AgentContainer getScenarioContainer();
    public abstract void setAgentsContainer(AgentContainer container);

    /**
     * List of all agents that will be started in the scenario.
     */
    protected List<AgentStartModel> agents = null;

    /**
     * !Important: Only this method should be used to start scenarios!
     * !Important: Jade Platform has to be started before starting scenarios!
     *
     * Firstly function checks if there is already started Jade Platform container with given scenario. If container is not created it starts new container.
     * If container is created and flag force is set to false then it throws ScenarioException.
     * If container is created and force flag is set to true, it shutdown container first and then starts new container with new agents.
     * @param force flag if exeution should be forced
     * @throws ScenarioException
     */
    public void Start(boolean force) throws ScenarioException, ScenarioOnExectionException {
        Runtime runtime = Runtime.instance();
        AgentContainer scenarioContainer = this.getScenarioContainer();
        if(scenarioContainer != null && force) {
            try {
                scenarioContainer.kill();
            } catch (StaleProxyException e) {
                e.printStackTrace();
                throw new ScenarioException("Agent container could not be killed!");
            }
        } else if(scenarioContainer != null) {
            throw new ScenarioException("Scenario is already started!");
        }

        this.setAgentsContainer(runtime.createAgentContainer(new ProfileImpl(null, 8888, this.getName())));
        scenarioContainer = this.getScenarioContainer();
        AgentController masterAgent = null;
        try {
            masterAgent = scenarioContainer.createNewAgent("master","MasterAgent.MasterAgent", null);
            masterAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
            throw new ScenarioException("Could not create master agent!");
        }

        if(agents == null) {
            throw new ScenarioException("List of agents must be initialized!");
        }
        for (AgentStartModel agent : this.agents) {
            AgentController agentController = null;
            try {
                agentController = scenarioContainer.createNewAgent(agent.getName(), agent.getAgentClassName(), agent.GetArguments());
                agentController.start();
            } catch (StaleProxyException e) {
                e.printStackTrace();
                throw new ScenarioException("Agents cannot be started!");
            }

        }

        //put master agent into WebGlobals
        try {
            WebGlobals.getInstance().masterAgent = masterAgent.getO2AInterface(IMasterAgent.class);
        } catch (StaleProxyException e) {
            e.printStackTrace();
            throw new ScenarioException("Could not create master agent interface!");
        }
        //start execution of the scenario
        this.Execute();
    }

    protected abstract void Execute() throws ScenarioOnExectionException;
}
