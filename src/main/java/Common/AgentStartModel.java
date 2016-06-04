package Common;

import Common.Abstract.IPosition;

/**
 * Created by adam on 6/4/16.
 */
public class AgentStartModel {
    IPosition position;
    int id;

    public AgentStartModel(IPosition position, int id) {
        this.position = position;
        this.id = id;
    }
}
