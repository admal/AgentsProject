package Common.Behaviours;

import Common.AgentType;
import Common.Abstract.IPosition;
import Common.Globals;
import Common.Messages.Register;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by adam on 5/3/16.
 */
public class RegisterBehaviour extends OneShotBehaviour {
    private AgentType type;
    private IPosition currPos;

    public RegisterBehaviour(Agent a, AgentType type, IPosition currPos) {
        super(a);
        this.type = type;
        this.currPos = currPos;
    }

    public void action() {
        ACLMessage registerMessage = new ACLMessage(ACLMessage.INFORM);
        registerMessage.setOntology("Register");
        Register register = new Register(type, myAgent.getAID(), currPos);
        registerMessage.addReceiver(Globals.getInstance().MASTER);
        try {
            registerMessage.setContentObject(register);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(registerMessage);
    }
}
