package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.Abstract.IPosition;
import Common.DistanceHelper;
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
    private IPosition current_starting;
    private IPosition current_ending;
    public MovingBehaviour(CarAgent agent, long period){
        super(agent,period);
        this.agent = agent;
        this.positionIndex = 0;
    }
    protected void onTick() {
        // TODO: update that to utilize route and to update chargedPercentage correctly
        if(positionIndex< agent.getList_route().size()){
            float kilometers = DistanceHelper.from_geographic_to_km(
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
}
