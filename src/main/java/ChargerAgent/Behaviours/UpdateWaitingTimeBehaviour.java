package ChargerAgent.Behaviours;

import ChargerAgent.ChargerAgent;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class UpdateWaitingTimeBehaviour extends TickerBehaviour {
    ChargerAgent agent;
    public UpdateWaitingTimeBehaviour(Agent a, long period) {
        super(a, period);
        agent = (ChargerAgent) a;
    }
    protected void onTick() {
        agent.onTick();
    }
}
