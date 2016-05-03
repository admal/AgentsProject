package CarAgent;

import Common.AgentType;
import Common.Behaviours.RegisterBehaviour;
import Common.Abstract.IPosition;
import Common.Position;
import jade.core.AID;
import jade.core.Agent;

/**
 * Created by adam on 5/3/16.
 */
public class CarAgent extends Agent {
    private IPosition currentPosition;
    private IPosition destination;
    //in percents
    private float chargedLevel;
    private boolean inMove = false;

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
        currentPosition = new Position(20, 10);
        chargedLevel = 1900;
        Register();
    }

    private void Register()
    {
        RegisterBehaviour registerBehaviour = new RegisterBehaviour(this, AgentType.Car,currentPosition);
        addBehaviour(registerBehaviour);
    }

    public IPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setDestination(IPosition destination) {
        this.destination = destination;
    }

    public IPosition getDestination() {
        return destination;
    }
}
