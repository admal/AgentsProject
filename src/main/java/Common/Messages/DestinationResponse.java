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
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by jedrek on 03.05.16.
 * Reads messages from cars if all sent their position chooses the best to go to client and sends to it the client's position.
 */
public class DestinationResponse extends Message implements IMasterHandable {
    private IPosition position;
    private AID aid;
    private boolean participate;
    private MasterAgent agent;

    public DestinationResponse(IPosition position, AID aid, boolean participate) {
        this.position = position;
        this.aid = aid;
        this.participate = participate;
    }

    public void Handle(MasterAgent agent, ACLMessage msg) {
        this.agent = agent;
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
        TransactionCar car, bestCar=null;
        double bestDistance = Double.MAX_VALUE, distance;
        Iterator<TransactionCar> it = cars.iterator();
        while(it.hasNext()){
            car = it.next();
            if(car.isParticipates()){
                //TODO Calculate the best car based on GoogleApi's time to get to client
                distance = calculateDistance(agent.currentClientPosition, car.getPosition());
                if(distance < bestDistance){
                    bestCar = car;
                }
            }
        }
        if(bestCar == null) System.out.println("No suitable car found.");
        return bestCar.getAid();
    }

    private double calculateDistance(IPosition currentClientPosition, IPosition position) {

        return Math.sqrt(Math.pow(currentClientPosition.GetX() - position.GetX(), 2) +
                Math.pow(currentClientPosition.GetY() - position.GetY(), 2));
    }

}
