package CarAgent.Behaviours;

import CarAgent.CarAgent;
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
            float kilometers = getRealDistance(parentAgent.getCurrentPosition().GetX(),
                    parentAgent.getCurrentPosition().GetY(),
                    parentAgent.getRoute().get(positionIndex).GetX(),
                    parentAgent.getRoute().get(positionIndex).GetY());
            parentAgent.setCurrentPosition(parentAgent.getRoute().get(positionIndex));
            parentAgent.setChargedLevel(parentAgent.getChargedLevel() - (int)kilometers);
            positionIndex++;
        }else{
            parentAgent.stop();
        }
    }

    private float getRealDistance(float lat1, float lon1, float lat2, float lon2){
        double theta = lon1 - lon2;
        float distance = (float) (Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta)));
        distance = (float) Math.acos(distance);
        distance = rad2deg(distance);
        distance = (float) (distance * 60 * 1.1515);
        distance = (float) (distance * 1.609344);
        return distance;
    }
    private static float deg2rad(double deg) {
        return (float) (deg * Math.PI / 180.0);
    }
    private static float rad2deg(double rad) {
        return (float) (rad * 180 / Math.PI);
    }
}
