package CarAgent;

import Common.AgentType;
import Common.Behaviours.RegisterBehaviour;
import Common.IPosition;
import Common.Messages.Register;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by adam on 5/3/16.
 */
public class CarAgent extends Agent {
    private IPosition currentPosition;
    private IPosition destination;
    private float chargedLevel;

    private final AID MASTER = new AID("master",AID.ISLOCALNAME);

    public CarAgent()
    {
        Register();
    }

    private void Register()
    {
        RegisterBehaviour registerBehaviour = new RegisterBehaviour(this, AgentType.Car,currentPosition);
        addBehaviour(registerBehaviour);
    }

}
