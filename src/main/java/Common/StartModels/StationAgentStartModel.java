package Common.StartModels;

import Common.Abstract.IPosition;
import Common.AgentStartModel;

/**
 * Created by adam on 9/7/16.
 */
public class StationAgentStartModel extends AgentStartModel {
    public StationAgentStartModel(IPosition position, int id) {
        super(position, id, "charger", "ChargerAgent.ChargerAgent");
    }
}
