package Common.StartModels;

import Common.Abstract.IPosition;
import Common.AgentStartModel;

/**
 * Created by adam on 9/7/16.
 */
public class CarAgentStartModel extends AgentStartModel {
    private int speed;
    private int powerPercentPerMeters;
    private float chargedPercentage;

    public CarAgentStartModel(IPosition position, int id, int speed, int powerPercentPerMeters, float chargedPercentage) {
        super(position, id, "car", "CarAgent.CarAgent");
        this.speed = speed;
        this.powerPercentPerMeters = powerPercentPerMeters;
        this.chargedPercentage = chargedPercentage;
    }

    @Override
    public Object[] GetArguments() {
        return new Object[]{super.position, speed, powerPercentPerMeters, chargedPercentage};
    }
}
