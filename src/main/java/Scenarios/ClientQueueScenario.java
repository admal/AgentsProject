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
public class ClientQueueScenario extends Scenario {

    public ClientQueueScenario() {
        super();
        InitData();
    }

    private void InitData() {
        super.name = "ClientQueueScenario";
        super.description = "It is a client queue scenario in which the number of requests is much higher than cars" +
                " so the unassigned clients wait for a free car which system will assign ass soon as 1 is free.";
        super.agents = new ArrayList<>();
        Position cp1 = new Position(52.26849389999999f, 21.046502000000032f);
        Position cp2 = new Position(52.24f, 21.14663840000003f);
        Position tmp3 = new Position(52.23202520000001f, 21.0923663840000003f);
        super.agents.add(new CarAgentStartModel(cp1, 1, 17, 1000, 15f));
        super.agents.add(new CarAgentStartModel(cp2, 2, 17, 1000, 30f));
        super.agents.add((new StationAgentStartModel(tmp3, 1)));
    }

    @Override
    protected void Execute() throws ScenarioOnExectionException {
        super.stateDescription = "Spamming requests in 10 sec.";
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Position reqPos = new Position(52.2596756f,21.032228700000037f);
                        WebGlobals.getInstance().masterAgent.addClientLocation(reqPos);
                        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(52.2296756f,21.152228700000037f));
                        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(52.1696756f,21.112228700000037f));
                        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(52.1296756f,21.112228700000037f));
                        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(52.2296756f,21.212228700000037f));
                        WebGlobals.getInstance().masterAgent.addClientLocation(new Position(52.2296756f,21.312228700000037f));
                        ClientQueueScenario.super.stateDescription = "Request sent at: " +  reqPos.toString();
                    }
                },
                10000
        );
    }

    @Override
    protected void Reset() {

    }
}
