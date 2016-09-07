package WebServlets;

import MasterAgent.IMasterAgent;
import Scenarios.Scenario;
import Scenarios.SimpleSceneScenario;

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
    }
    public static WebGlobals getInstance()
    {
        if (instance == null)
            instance = new WebGlobals();
        return instance;
    }
}
