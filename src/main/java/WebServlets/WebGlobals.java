package WebServlets;

import MasterAgent.IMasterAgent;
import Scenarios.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 6/4/16.
 */
public class WebGlobals
{
    private static WebGlobals instance;
    public List<Scenario> scenarios;
    public IMasterAgent masterAgent;
    protected WebGlobals(){
        scenarios = new ArrayList<>();
        scenarios.add(new SimpleSceneScenario());
        scenarios.add(new RechargeSceneScenario());
        scenarios.add(new ClientQueueScenario());
        scenarios.add(new FullChargeScenario());
    }

    public static WebGlobals getInstance()
    {
        if (instance == null)
            instance = new WebGlobals();
        return instance;
    }
}
