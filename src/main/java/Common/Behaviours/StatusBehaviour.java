package Common.Behaviours;

import Common.Abstract.IStatusSendable;
import Common.AgentType;
import Common.Globals;
import Common.Messages.Status;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by adam on 9/2/16.
 */
public class StatusBehaviour extends TickerBehaviour {
    public StatusBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        IStatusSendable sendable = (IStatusSendable)this.myAgent;
        Status statusMsg = sendable.GetStatusMessage();


        ACLMessage statusAcl = new ACLMessage(ACLMessage.INFORM);
        statusAcl.setOntology("status");
        statusAcl.addReceiver(Globals.getInstance().MASTER);
        try {
            statusAcl.setContentObject(statusMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(statusAcl);
    }
}
