package Common.WebModels;

import Common.Abstract.IPosition;
import Common.AgentType;

/**
 * Created by adam on 6/9/16.
 */
public class CarAgentWebModel
{
    public CarAgentWebModel(AgentType type, IPosition position, IPosition destination, float chargeLevel, String name) {
        this.type = type;
        this.position = position;
        this.destination = destination;
        this.chargeLevel = chargeLevel;
        this.name = name;
    }

    public AgentType type;
    public IPosition position;
    public IPosition destination;
    public float chargeLevel;
    public String name;
    public int speed;
    public int fuelBurning;

    public CarAgentWebModel(AgentType type, IPosition position, IPosition destination, float chargeLevel, String name, int speed, int fuelBurning) {
        this.type = type;
        this.position = position;
        this.destination = destination;
        this.chargeLevel = chargeLevel;
        this.name = name;
        this.speed = speed;
        this.fuelBurning = fuelBurning;
    }
}
