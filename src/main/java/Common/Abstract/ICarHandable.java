package Common.Abstract;

import CarAgent.CarAgent;
import jade.lang.acl.ACLMessage;

public interface ICarHandable {
    void Handle(CarAgent agent, ACLMessage original);
}
