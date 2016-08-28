package Common.Messages;

import Common.Abstract.IMasterHandable;
import Common.Abstract.IPosition;
import Common.AgentClasses.Car;
import Common.AgentClasses.TransactionCar;
import Common.Position;
import MasterAgent.MasterAgent;
import MasterAgent.behaviours.NewClientRequestBehaviour;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.Duration;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Common.GoogleApiHelper.Connector;


/**
 * Created by jedrek on 03.05.16.
 * Reads messages from cars if all sent their position chooses the best, to go to client and sends to it the client's position.
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

        if (agent.carsInCurrentTransaction.size() == agent.cars.size()) {

            AID bestCar = getBestCarAID(agent.carsInCurrentTransaction, agent.currentClientPosition);
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


    private AID getBestCarAID(List<TransactionCar> cars, IPosition destination) {
        TransactionCar car, bestCar = null;
        double bestDistance = Double.MAX_VALUE, distance;
        Iterator<TransactionCar> it = cars.iterator();
        GeoApiContext gapiContext = Connector.getGeoApiContext();

        Duration[] durations = Connector.getCarDurationsMatrix(gapiContext, cars, destination);
        int fin = 0;
        boolean found = false;
        for (int i = 0; i < durations.length; i++) {
            if (durations[i].inSeconds <= durations[fin].inSeconds && cars.get(i).isParticipates()) {
                found = true;
                fin = i;
            }
        }

        bestCar = cars.get(fin);

        if (bestCar == null || !found) {
            System.out.println("No suitable car found.");
            return null;
        }

        return bestCar.getAid();
    }

}
