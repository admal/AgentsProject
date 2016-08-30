package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.DistanceHelper;
import jade.core.behaviours.TickerBehaviour;

/**
 * Created by janbaraniewski on 14/06/16.
 */
public class MovingBehaviour extends TickerBehaviour {
    private CarAgent parentAgent;
    private int positionIndex;
    public MovingBehaviour(CarAgent agent, long period){
        super(agent,period);
        this.parentAgent = agent;
        this.positionIndex = 0;
    }
    protected void onTick() {
        if(positionIndex<parentAgent.getRoute().size()){
            float kilometers = DistanceHelper.from_geographic_to_km(parentAgent.getCurrentPosition().GetX(),
                    parentAgent.getCurrentPosition().GetY(),
                    parentAgent.getRoute().get(positionIndex).GetX(),
                    parentAgent.getRoute().get(positionIndex).GetY());
            parentAgent.setCurrentPosition(parentAgent.getRoute().get(positionIndex));
            parentAgent.setChargedPercentage(parentAgent.getChargedPercentage() - (int)kilometers);
            positionIndex++;
        }else{
            parentAgent.stop();
        }
    }
}
