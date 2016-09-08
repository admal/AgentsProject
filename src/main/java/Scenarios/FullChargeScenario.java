package Scenarios;

import Common.Position;
import Common.StartModels.CarAgentStartModel;
import Common.StartModels.StationAgentStartModel;
import WebServlets.WebGlobals;

import java.util.ArrayList;

/**
 * Created by jedrek on 07.09.16.
 */
public class FullChargeScenario extends Scenario {

    public FullChargeScenario() {
        super();
        InitData();
    }

    private void InitData() {
        super.name = "FullRechargeScenario";
        super.description = "A car performs a recharge at the station, then goes to a client which was waiting the whole time";
        super.agents = new ArrayList<>();
        Position cp1 = new Position(52.25469f, 21.03508f);
        Position tmp1 = new Position(52.25673f, 21.03508f);
        super.agents.add(new CarAgentStartModel(cp1, 1, 17, 1000, 19f));
        super.agents.add((new StationAgentStartModel(tmp1, 1)));
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
                        FullChargeScenario.super.stateDescription = "Request sent at: " +  reqPos.toString();
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
