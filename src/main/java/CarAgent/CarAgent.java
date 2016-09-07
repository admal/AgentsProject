package CarAgent;

import CarAgent.Behaviours.CheckingFuelBehaviour;
import CarAgent.Behaviours.MovingBehaviour;
import CarAgent.Behaviours.ReceivingBehaviour;
import Common.Abstract.IPosition;
import Common.Abstract.IStatusSendable;
import Common.AgentClasses.Car;
import Common.AgentClasses.ChargingStation;
import Common.AgentClasses.TransactionCharger;
import Common.AgentType;
import Common.Behaviours.RegisterBehaviour;
import Common.Behaviours.StatusBehaviour;
import Common.GoogleApiHelper.DirectionsClient;
import Common.Messages.Status;
import Common.Position;
import Common.Route;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by adam on 5/3/16.
 */
public class CarAgent extends Agent implements IStatusSendable {
    private int speed = 17; // That's approximate speed represented in m/s
    private int powerPercentPerMeeters = 1000; // 1% / 1000m
    private float chargedPercentage = 100;
    private boolean inMove = false;
    private IPosition currentPosition;
    private IPosition destination;
    private IPosition chargingPosition;
    private List<IPosition> list_route;
    private Route route;
    private List<ChargingStation> stations;
    public List<TransactionCharger> chargingStations;
    private MovingBehaviour movingBehaviour;

    public CarAgent() {}
    public IPosition getChargingPosition() { return chargingPosition; }

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

    public void setInMove(boolean inMove) { this.inMove = inMove; }

    public float getChargedPercentage() { return chargedPercentage; }

    public void setChargedPercentage(float chargedPercentage) { this.chargedPercentage = chargedPercentage; }

    public IPosition getCurrentPosition() { return currentPosition; }

    public void setCurrentPosition(IPosition position){
        this.currentPosition = position;
        //System.out.println(this.getLocalName()+" new position "+this.currentPosition.toString());
    }

    public IPosition getDestination() { return destination; }

    public List<IPosition> getList_route() { return this.list_route; }

    public int getSpeed() { return speed; }

    public void setChargingPosition(IPosition chargingPosition) {
        this.chargingPosition = chargingPosition;
    }

    @Override
    protected void setup() {
        super.setup();
        Object[] args = getArguments();
        this.currentPosition= (Position)args[0];
        if(args.length > 1) {
            this.speed = (int) args[1];
            this.powerPercentPerMeeters = (int) args[2];
            this.chargedPercentage = (float) args[3];
        }

        this.chargingPosition = null;
        this.movingBehaviour = null;
        chargingStations = new ArrayList<TransactionCharger>();
        
        //hardcoded just for debuging
        this.list_route = new ArrayList<IPosition>();
//        list_route.add(new Position(41,40));
//        list_route.add(new Position(40,40));
//        list_route.add(new Position(40,39));
//        list_route.add(new Position(40,38));
//        list_route.add(new Position(40,37));
//        list_route.add(new Position(39,36));
//        list_route.add(new Position(38,35));

        System.out.println("Auto: "+ currentPosition);
        Register();
    }

    private void Register()
    {
        RegisterBehaviour registerBehaviour = new RegisterBehaviour(this, AgentType.Car,currentPosition);
        addBehaviour(registerBehaviour);
        addBehaviour(new ReceivingBehaviour(this));
        addBehaviour(new CheckingFuelBehaviour(this,1000));
        addBehaviour(new StatusBehaviour(this, 5000));
    }

    public void setDestination(IPosition destination) {
        this.destination = destination;
        this.inMove = true;
        this.route = DirectionsClient.getDirectionsToTarget(this, destination);
        this.currentPosition = this.route.getPoints().get(0);
        this.movingBehaviour = new MovingBehaviour(this,1000);
        addBehaviour(this.movingBehaviour);
    }
    public void stop(){
        this.destination = null;
        this.inMove = false;
        removeBehaviour(this.movingBehaviour);
    }

    public boolean hasEnoughFuelForTrip(Route route) {
        return this.chargedPercentage >= route.getDistance()/1000; // From m to km
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getPowerPercentPerMeeters() {
        return powerPercentPerMeeters;
    }

    @Override
    public Status GetStatusMessage() {
        Status status = new Status();
        status.type = AgentType.Car;
        Car carInfo = new Car(this.getAID(), this.getCurrentPosition());
        carInfo.setChargerPosition(this.getChargingPosition());
        carInfo.setCharge(this.getChargedPercentage());
        carInfo.setDestination(this.getDestination());
        carInfo.setSpeed(this.getSpeed());
        carInfo.setFuelBurning(this.getPowerPercentPerMeeters());

        status.agentInformation = carInfo;
        return status;
    }
}
