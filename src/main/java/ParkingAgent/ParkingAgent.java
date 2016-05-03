package ParkingAgent;

import Common.IPosition;
import jade.core.Agent;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ParkingAgent extends Agent{
    private IPosition position;
    private int MAX_CARS = 20;
    private int occupied;
    public ParkingAgent(){
        this.occupied = 0;
    }

}
