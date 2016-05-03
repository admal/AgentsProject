package Common.Abstract;

import MasterAgent.MasterAgent;
import jade.lang.acl.ACLMessage;

/**
 * Created by adam on 5/3/16.
 */
public interface IMasterHandable {
    void Handle(MasterAgent agent, ACLMessage msg);
}

