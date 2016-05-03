package CarAgent;

import Common.IPosition;
import jade.core.Agent;

/**
 * Created by adam on 5/3/16.
 */
public class CarAgent extends Agent {
    private IPosition currentPosition;
    private IPosition destination;
    private float chargedLevel;

    public CarAgent()
    {
        Register();
    }

    private void Register()
    {

    }
}
