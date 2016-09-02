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
    private IPosition direction;
    private float meters_to_burn;


    public MovingBehaviour(CarAgent agent, long period){
        super(agent,period);
        this.agent = agent;
        this.current_subroute_end_id = 1;
        this.step = this.calculateStep();
        this.meters_to_burn = 0;
    }
    protected void onTick() {
        this.moveForward();
        this.updatePower();
        this.checkIfArrived();
    }

    private void updatePower() {
        this.meters_to_burn+=this.agent.getSpeed();
        if(this.meters_to_burn/this.agent.getPowerPercentPerMeeters()>1){
            this.meters_to_burn-=this.agent.getPowerPercentPerMeeters();
            this.agent.setChargedPercentage(this.agent.getChargedPercentage()-1);
        }
    }

    private IPosition calculateStep() {
        IPosition start = this.getAgentPosition(this.current_subroute_end_id - 1);
        IPosition end = this.getAgentPosition(this.current_subroute_end_id);
        float distanceInM = DistanceHelper.fromGeographicToM(start.GetX(),start.GetY(),end.GetX(),end.GetY());
        float step_factor = distanceInM / this.agent.getSpeed();
        float X = (start.GetX() - end.GetX()) / step_factor;
        float Y = (start.GetY() - end.GetY()) / step_factor;
        if(X<0) X*=-1;
        if(Y<0) Y*=-1;
        return this.adjustStepToDirection(new Position(X,Y));
    }

    private IPosition adjustStepToDirection(IPosition position) {
        float x = this.getAgentPosition(this.current_subroute_end_id - 1).GetX() < this.getAgentPosition(this.current_subroute_end_id).GetX()?1:-1;
        float y = this.getAgentPosition(this.current_subroute_end_id - 1).GetY() < this.getAgentPosition(this.current_subroute_end_id).GetY()?1:-1;
        this.direction = new Position(x,y);
        return new Position(position.GetX()*direction.GetX(), position.GetY()*direction.GetY());
    }

    private void checkIfArrived() {
        if(this.agent.getCurrentPosition() == this.agent.getRoute().getPoints().get(this.agent.getRoute().getPoints().size()-1)){
            this.agent.setChargedPercentage(this.agent.getChargedPercentage()-((1/this.agent.getPowerPercentPerMeeters())*this.meters_to_burn));
            this.agent.stop();
       } else   if(this.direction.GetX() == -1 && this.agent.getCurrentPosition().GetX() < this.agent.getRoute().getPoints().get(this.current_subroute_end_id).GetX()
                || this.direction.GetX() == 1 && this.agent.getCurrentPosition().GetX() > this.agent.getRoute().getPoints().get(this.current_subroute_end_id).GetX()){
            IPosition newPosition = this.agent.getRoute().getPoints().get(this.current_subroute_end_id);
            this.agent.setCurrentPosition(newPosition);
            this.agent.setChargedPercentage(this.agent.getChargedPercentage()-((1/this.agent.getPowerPercentPerMeeters())*this.meters_to_burn));
            this.agent.stop();
        }

    }

    private void moveForward() {
        System.out.println(this.agent.getLocalName()+" moving forward");
        if(this.checkIfNewSubroute())
            this.changeSubroad();
        IPosition newAgentPosition = this.makeStep();
        this.agent.setCurrentPosition(newAgentPosition);
    }

    private IPosition makeStep() {
        IPosition newPosition = new Position(
                this.agent.getCurrentPosition().GetX()+this.step.GetX(),
                this.agent.getCurrentPosition().GetY()+this.step.GetY()
        );
        if(this.direction.GetX() == -1 && newPosition.GetX() < this.agent.getRoute().getPoints().get(this.current_subroute_end_id).GetX()
                || this.direction.GetX() == 1 && newPosition.GetX() > this.agent.getRoute().getPoints().get(this.current_subroute_end_id).GetX()){
            newPosition = this.agent.getRoute().getPoints().get(this.current_subroute_end_id);
        }
        return newPosition;
    }

    private void changeSubroad() {
        System.out.println("Changing subroad");
        this.current_subroute_end_id++;
        this.step = this.calculateStep();
    }

    private boolean checkIfNewSubroute() {
        return this.agent.getCurrentPosition() == getAgentPosition(this.current_subroute_end_id);
    }

    private IPosition getAgentPosition(int current_ending_id) {
        return this.agent.getRoute().getPoints().get(current_ending_id);
    }
}
