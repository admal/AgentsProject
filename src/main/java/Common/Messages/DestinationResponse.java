package Common.Messages;

import Common.Abstract.IMasterHandable;
import Common.Abstract.IPosition;
import Common.AgentClasses.Car;
import Common.AgentClasses.TransactionCar;
import Common.Route;
import MasterAgent.MasterAgent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;


/**
 * Created by jedrek on 03.05.16.
 * Reads messages from cars if all sent their position chooses the best, to go to client and sends to it the client's position.
 */
public class DestinationResponse extends Message implements IMasterHandable {
    private IPosition position;
    private Route route;
    private AID aid;
    private boolean participate;
    private MasterAgent agent;

    public DestinationResponse(IPosition position, AID aid, boolean participate) {
        this.position = position;
        this.aid = aid;
        this.participate = participate;
        this.route = null;
        this.agent = null;
    }

    public DestinationResponse(IPosition position, Route route, AID aid, boolean participate) {
        this.position = position;
        this.aid = aid;
        this.participate = participate;
        this.route = route;
        this.agent = null;
    }

    public void Handle(MasterAgent agent, ACLMessage msg) {
        this.agent = agent;
        agent.carsInCurrentTransaction.add(new TransactionCar(this.aid, this.position, this.route, this.participate));

        if (agent.carsInCurrentTransaction.size() == agent.cars.size()) {
            AID bestCar = getBestCarAID(agent.carsInCurrentTransaction);
            if (bestCar != null) {
                ACLMessage replyMsg = new ACLMessage(ACLMessage.CONFIRM);
                try {
                    replyMsg.setContentObject(new DestinationAcceptance(agent.currentClientPosition));
                    replyMsg.addReceiver(bestCar);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //we mark that the system is ready to assign next destination a car
                //we also set car from this transaction its destination
                for (Car car : agent.cars) {
                    if (car.getAid().equals(bestCar)) {
                        car.setDestination(agent.currentClientPosition);
                        break;
                    }
                }
                agent.send(replyMsg);
                agent.currentClientPosition = null;

            } else {
                System.out.println("There are currently no cars available.");
            }
            agent.carsInCurrentTransaction.clear();
        }
    }


    private AID getBestCarAID(List<TransactionCar> cars) {
        TransactionCar bestCar = null;
        for(TransactionCar _car : cars){
            if(bestCar==null)
                bestCar = _car.isParticipates() ? _car : null;
            else if(_car.getRoute().getTime() < bestCar.getRoute().getTime() && _car.isParticipates())
                bestCar = _car;
        }
        if (bestCar == null) {
            System.out.println("No suitable car found.");
            return null;
        }
        return bestCar.getAid();
    }

}
