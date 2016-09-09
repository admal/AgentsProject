package Scenarios;

import Common.Position;
import Common.StartModels.CarAgentStartModel;
import Common.StartModels.StationAgentStartModel;
import Scenarios.Exceptions.ScenarioOnExectionException;
import WebServlets.WebGlobals;

import java.util.ArrayList;

/**
 * Created by jedrek on 07.09.16.
 */
public class RechargeSceneScenario extends Scenario {

    public RechargeSceneScenario() {
        super();
        InitData();
    }

    private void InitData() {
        super.name = "RechargeScenario";
        super.description = "It is recharge scenario, containing 2 discharged cars and 3 chargers";
        super.agents = new ArrayList<>();
        Position cp1 = new Position(52.26849389999999f, 21.046502000000032f);
        Position cp2 = new Position(52.20f, 21.15663840000003f);
        Position cp3 = new Position(52.16849389999999f, 21.146502000000032f);
        Position tmp3 = new Position(52.23202520000001f, 21.123663840000003f);
        Position tmp4 = new Position(52.20663840000003f, 21.134695499999998f);
        Position tmp5 = new Position(52.24663840000003f, 20.14495499999998f);
        super.agents.add(new CarAgentStartModel(cp1, 1, 17, 1000, 15f));
        super.agents.add(new CarAgentStartModel(cp2, 2, 17, 1000, 30f));
        super.agents.add(new CarAgentStartModel(cp3, 3, 17, 1000, 19f));
        super.agents.add((new StationAgentStartModel(tmp3, 1)));
        super.agents.add((new StationAgentStartModel(tmp4, 2)));
        super.agents.add((new StationAgentStartModel(tmp5, 3)));
    }

    @Override
    protected void Execute() throws ScenarioOnExectionException {
        super.stateDescription = "started, request will be sent soon...";
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Position reqPos = new Position(52.31296756f,21.122228700000037f);
                        WebGlobals.getInstance().masterAgent.addClientLocation(reqPos);
                        RechargeSceneScenario.super.stateDescription = "Request sent at: " +  reqPos.toString();
                    }
                },
                10000
        );
    }

    @Override
    protected void Reset() {
        InitData();
    }
}
