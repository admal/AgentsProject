package CarAgent.Behaviours;

import CarAgent.CarAgent;
import Common.Abstract.IPosition;
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
            parentAgent.setCurrentPosition(parentAgent.getRoute().get(positionIndex));
            positionIndex++;
        }else{
            parentAgent.stop();
        }
    }
}
