package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.Abstract.IPosition;
import Common.DistanceHelper;
import Common.Position;
import jade.core.behaviours.TickerBehaviour;

/**
 * Created by janbaraniewski on 14/06/16.
 */
public class MovingBehaviour extends TickerBehaviour {
    private CarAgent agent;
    private int positionIndex;
    /*
        2 following vars may not be clear:
        those are 2 consecutive points from list returned by google maps
        between which we are currently moving
    */
    private int current_starting_id;
    private int current_ending_id;
    private IPosition current_position;
    private IPosition step;
    
    public MovingBehaviour(CarAgent agent, long period){
        super(agent,period);
        this.agent = agent;
        this.current_ending_id = 1;
        this.current_position = null;
        this.step = null;
    }
    protected void onTick() {
        this.moveForward();
        this.updatePower();
        this.checkIfArrived();
    }

    private void calculateStep() {
        IPosition start = this.getAgentPosition(this.current_ending_id - 1);
        IPosition end = this.getAgentPosition(this.current_ending_id);
        float distanceInM = DistanceHelper.fromGeographicToM(start.GetX(),start.GetY(),end.GetX(),end.GetY());
        float step_factor = distanceInM / this.agent.getSpeed();
        float X = (start.GetX() - end.GetX()) / step_factor;
        float Y = (start.GetY() - end.GetY()) / step_factor;
        if(X<0) X*=-1;
        if(Y<0) Y*=-1;
        this.step = new Position(X,Y);
    }

    private void checkIfArrived() {
    }

    private void updatePower() {
    }

    private void moveForward() {
        if(this.checkIfNewSubroute())
            this.changeSubroad();
        if(positionIndex< agent.getList_route().size()){
            float kilometers = DistanceHelper.fromGeographicToKm(
                    agent.getCurrentPosition().GetX(),
                    agent.getCurrentPosition().GetY(),
                    agent.getList_route().get(positionIndex).GetX(),
                    agent.getList_route().get(positionIndex).GetY()
            );
            agent.setCurrentPosition(agent.getList_route().get(positionIndex));
            agent.setChargedPercentage(agent.getChargedPercentage() - (int)kilometers);
            positionIndex++;
        }else{
            agent.stop();
        }
    }

    private void changeSubroad() {
        this.current_ending_id++;
        this.calculateStep();
    }

    private boolean checkIfNewSubroute() {
        return getAgentPosition(this.current_ending_id-1) == getAgentPosition(this.current_ending_id);
    }

    private IPosition getAgentPosition(int current_ending_id) {
        return this.agent.getRoute().getPoints().get(current_ending_id);
    }
}
