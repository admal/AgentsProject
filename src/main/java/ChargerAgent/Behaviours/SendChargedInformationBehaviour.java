package ChargerAgent.Behaviours;

import ChargerAgent.ChargerAgent;
import Common.Messages.ChargeResponse;
import Common.Messages.ChargedInformation;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

/**
 * Created by jedrek on 02.09.16.
 */
public class SendChargedInformationBehaviour extends OneShotBehaviour{
    private ChargerAgent agent;
    private AID receiver;

    public SendChargedInformationBehaviour(Agent a, AID receiver) {
        super(a);
        this.agent = (ChargerAgent) a;
        this.receiver = receiver;
    }

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ChargedInformation response = new ChargedInformation(100f); //always inform it is fully charged
        msg.setOntology("chargedInformation");
        try {
            msg.setContentObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.addReceiver(receiver);
        myAgent.send(msg);
    }
}
