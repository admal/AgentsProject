package CarAgent;

import CarAgent.Behaviours.CheckingFuelBehaviour;
import CarAgent.Behaviours.MovingBehaviour;
import CarAgent.Behaviours.ReceivingBehaviour;
import Common.AgentClasses.ChargingStation;
import Common.AgentType;
import Common.Behaviours.RegisterBehaviour;
import Common.Abstract.IPosition;
import Common.Position;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by adam on 5/3/16.
 */
public class CarAgent extends Agent {
    private final int speed = 60;
    private IPosition currentPosition;
    private IPosition destination;
    private IPosition chargingPosition;
    private List<IPosition> route;
    private MovingBehaviour movingBehaviour;

    //in percents
    private float chargedLevel;
    private boolean inMove = false;

    private List<ChargingStation> stations;


    public void UpdateStations(List<ChargingStation> stations)
    {
        this.stations = stations;
    }

    public List<ChargingStation> getStations() {
        return stations;
    }

    public boolean isInMove() {
        return inMove;
    }

    public void setInMove(boolean inMove) {
        this.inMove = inMove;
    }

    public float getChargedLevel() {
        return chargedLevel;
    }

    public void setChargedLevel(float chargedLevel) {
        this.chargedLevel = chargedLevel;
    }

    public CarAgent()
    {

    }

    @Override
    protected void setup() {
        super.setup();
        Object[] args = getArguments();
        this.currentPosition= (Position)args[0];
        this.chargingPosition = null;
        this.movingBehaviour = null;
        
        //hardcoded just for debuging
        this.route = new ArrayList<IPosition>();
        route.add(new Position(41,40));
        route.add(new Position(40,40));
        route.add(new Position(40,39));
        route.add(new Position(40,38));
        route.add(new Position(40,37));
        route.add(new Position(39,36));
        route.add(new Position(38,35));

        System.out.println("Auto: "+ currentPosition);
        chargedLevel = 90;
        Register();
    }

    private void Register()
    {
        RegisterBehaviour registerBehaviour = new RegisterBehaviour(this, AgentType.Car,currentPosition);
        addBehaviour(registerBehaviour);
        addBehaviour(new ReceivingBehaviour(this));
        addBehaviour(new CheckingFuelBehaviour(this,1000));
    }

    public IPosition getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(IPosition position){ this.currentPosition = position; }

    public void setDestination(IPosition destination) {
        this.destination = destination;
        this.inMove = true;
        this.movingBehaviour = new MovingBehaviour(this,1000);
        addBehaviour(this.movingBehaviour);
    }
    public void stop(){
        this.destination = null;
        this.inMove = false;
        removeBehaviour(this.movingBehaviour);
    }
    public IPosition getDestination() {
        return destination;
    }

    public List<IPosition> getRoute() {
        return this.route;
    }
}
