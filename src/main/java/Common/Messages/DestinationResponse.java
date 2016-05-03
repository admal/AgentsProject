package Common.Messages;

import Common.Abstract.IMasterHandable;
import Common.Abstract.IPosition;
import Common.AgentClasses.Car;
import Common.AgentClasses.TransactionCar;
import Common.Position;
import MasterAgent.MasterAgent;
import MasterAgent.behaviours.NewClientRequestBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by jedrek on 03.05.16.
 * Reads messages from cars if all sent their position chooses the best to go to client and sends to it the client's position.
 */
public class DestinationResponse extends Message implements IMasterHandable {
    IPosition position;
    AID aid;
    boolean participate;

    public DestinationResponse(IPosition position, AID aid, boolean participate) {
        this.position = position;
        this.aid = aid;
        this.participate = participate;
    }

    public void Handle(MasterAgent agent, ACLMessage msg) {
        Car temp = new Car(aid, position);
        agent.carsInCurrentTransaction.add(new TransactionCar(aid, position, participate));

        if(agent.carsInCurrentTransaction.size() == agent.cars.size()) {

            AID bestCar = getBestCarAID(agent.carsInCurrentTransaction);

            ACLMessage replyMsg = msg.createReply();
            try {
                replyMsg.setContentObject(new DestinationAcceptance(agent.currentClientPosition));
            } catch (IOException e) {
                e.printStackTrace();
            }
            agent.send(replyMsg);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            agent.addBehaviour(new NewClientRequestBehaviour(agent));
        }
    }

    private AID getBestCarAID(List<TransactionCar> cars) {
        return cars.get(0).getAid();
    }

}
