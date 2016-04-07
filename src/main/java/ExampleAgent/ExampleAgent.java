package ExampleAgent;

import ExampleAgent.Behaviours.ExampleBehaviour;
import jade.core.Agent;

/**
 * Created by Adam on 2016-04-07.
 */
public class ExampleAgent extends Agent
{
    public ExampleAgent() {
        System.out.println("Example agent starts");
        addBehaviour(new ExampleBehaviour());
    }
}
