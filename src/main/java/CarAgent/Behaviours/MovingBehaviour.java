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
    private int current_subroute_end_id;
    private IPosition step;
    
    public MovingBehaviour(CarAgent agent, long period){
        super(agent,period);
        this.agent = agent;
        this.current_subroute_end_id = 1;
        this.step = null;
    }
    protected void onTick() {
        this.moveForward();
        this.updatePower();
        this.checkIfArrived();
    }

    private void calculateStep() {
        IPosition start = this.getAgentPosition(this.current_subroute_end_id - 1);
        IPosition end = this.getAgentPosition(this.current_subroute_end_id);
        float distanceInM = DistanceHelper.fromGeographicToM(start.GetX(),start.GetY(),end.GetX(),end.GetY());
        float step_factor = distanceInM / this.agent.getSpeed();
        float X = (start.GetX() - end.GetX()) / step_factor;
        float Y = (start.GetY() - end.GetY()) / step_factor;
        if(X<0) X*=-1;
        if(Y<0) Y*=-1;
        this.step = this.adjustStepToDirection(new Position(X,Y));
    }

    private IPosition adjustStepToDirection(Position position) {
        float x = this.getAgentPosition(this.current_subroute_end_id - 1).GetX() < this.getAgentPosition(this.current_subroute_end_id).GetX()?1:-1;
        float y = this.getAgentPosition(this.current_subroute_end_id - 1).GetY() < this.getAgentPosition(this.current_subroute_end_id).GetY()?1:-1;
        Position direction = new Position(x,y);
        return new Position(position.GetX()*direction.GetX(), position.GetY()*direction.GetY());
    }

    private void checkIfArrived() {
    }

    private void updatePower() {

    }

    private void moveForward() {
        if(this.checkIfNewSubroute())
            this.changeSubroad();
        Position newAgentPosition = new Position(
                this.agent.getCurrentPosition().GetX()+this.step.GetX(),
                this.agent.getCurrentPosition().GetY()+this.step.GetY()
        );
        this.agent.setCurrentPosition(newAgentPosition);
    }

    private void changeSubroad() {
        this.current_subroute_end_id++;
        this.calculateStep();
    }

    private boolean checkIfNewSubroute() {
        return getAgentPosition(this.current_subroute_end_id -1) == getAgentPosition(this.current_subroute_end_id);
    }

    private IPosition getAgentPosition(int current_ending_id) {
        return this.agent.getRoute().getPoints().get(current_ending_id);
    }
}
