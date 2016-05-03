package Common.Messages;

import Common.AgentClasses.ChargingStation;

import java.util.List;

/**
 * Created by adam on 5/3/16.
 */
public class RegisterResponse extends Message
{
    public boolean registered;
    public List<ChargingStation> chargingStations;

    public RegisterResponse(boolean registered, List<ChargingStation> chargingStations) {
        this.registered = registered;
        this.chargingStations = chargingStations;
    }
}
