package Common.StartModels;

import Common.Abstract.IPosition;

/**
 * Created by adam on 9/7/16.
 */
public class StationAgentStartModel extends AgentStartModel {
    public StationAgentStartModel(IPosition position, int id) {
        super(position, id, "charger", "ChargerAgent.ChargerAgent");
    }
}
