package Common.WebModels;

import Common.Abstract.IPosition;
import Common.AgentType;

/**
 * Created by adam on 6/9/16.
 */
public class StationaryAgentWebModel
{
    public StationaryAgentWebModel(String name, AgentType type, IPosition position) {
        this.name = name;
        this.type = type;
        this.position = position;
    }

    public String name;
    public AgentType type;
    public IPosition position;
}
