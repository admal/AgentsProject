package CarAgent.Behaviours;

import CarAgent.CarAgent;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 * Created by adam on 5/3/16.
 */
public class CheckingFuelBehaviour extends TickerBehaviour {
    public CheckingFuelBehaviour(Agent a, long period) {
        super(a, period);
    }

    protected void onTick() {
        CarAgent carAgent = (CarAgent)myAgent;
        if(carAgent.isInMove())
        {
            carAgent.setChargedLevel(carAgent.getChargedLevel()-1 );
        }
    }
}
