package Common.Messages;

import Common.Abstract.IMasterHandable;
import Common.Abstract.IPosition;
import Common.Position;
import MasterAgent.MasterAgent;
import MasterAgent.behaviours.NewClientRequestBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationResponse extends Message implements IMasterHandable {
    IPosition position;

    public DestinationResponse(IPosition position) {
        this.position = position;
    }

    public void Handle(MasterAgent agent, ACLMessage msg) {



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agent.addBehaviour(new NewClientRequestBehaviour(agent));
    }
}
