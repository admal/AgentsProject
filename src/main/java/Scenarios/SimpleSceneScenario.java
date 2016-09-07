package Scenarios;

import Common.Position;
import Common.StartModels.CarAgentStartModel;
import Common.StartModels.StationAgentStartModel;
import WebServlets.WebGlobals;
import jade.wrapper.AgentContainer;

import java.util.ArrayList;

/**
 * Created by adam on 9/6/16.
 * Simple scenario. There are 3 cars and 1 station. There occurs 1 client request. Car 1 should go there because it is the closest car.
 */
public class SimpleSceneScenario extends Scenario {
    /**
     * It is static to keep track for each scenario if it was not started already.
     */
    private static AgentContainer scenarioContainer = null;

    public SimpleSceneScenario() {
        super();
        InitData();
    }

    private void InitData() {
        super.name = "SimpleScenario";
        super.description = "It is simple scenario, containing just several fully charged cars and 1 station in Targówek.";
        super.agents = new ArrayList<>();
        Position tmp1 = new Position(52.26849389999999f, 21.046502000000032f);
        Position tmp2 = new Position(52.27293840000001f, 21.054693199999974f);
        Position tmp3 = new Position(52.2861984f, 21.0785932f);
        Position tmp4 = new Position(52.254361f, 21.03371500000003f);
        Position wysockiego = new Position(52.28829349999999f, 21.02004260000001f);
        super.agents.add(new CarAgentStartModel(tmp1, 1, 34, 1000, 30));
        super.agents.add(new CarAgentStartModel(tmp2, 2, 34, 1000, 60));
        super.agents.add(new CarAgentStartModel(tmp3, 3, 34, 1000, 100));
        super.agents.add((new StationAgentStartModel(tmp4, 1)));
        super.agents.add(new StationAgentStartModel(wysockiego, 2));
    }
    @Override
    protected void Execute() throws ScenarioOnExectionException {
        super.stateDescription = "started, request will be sent soon...";
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Position reqPos = new Position(52.2296756f,21.012228700000037f);
                        WebGlobals.getInstance().masterAgent.addClientLocation(reqPos);
                        SimpleSceneScenario.super.stateDescription = "Request sent at: " +  reqPos.toString();
                    }
                },
                10000
        );

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Position reqPos = new Position(51.9266282f,19.802207700000054f);
                        WebGlobals.getInstance().masterAgent.addClientLocation(reqPos);
                        SimpleSceneScenario.super.stateDescription = "Another request sent at: " +  reqPos.toString();
                    }
                },
                30000
        );

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Position reqPos = new Position(52.26849389999999f,21.046502000000032f);
                        WebGlobals.getInstance().masterAgent.addClientLocation(reqPos);
                        SimpleSceneScenario.super.stateDescription = "Another request sent at: " +  reqPos.toString();
                    }
                },
                32000
        );
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        SimpleSceneScenario.super.stateDescription = "All requests sent";
                    }
                },
                35000
        );
    }

    @Override
    protected void Reset() {
        InitData();
    }
}
