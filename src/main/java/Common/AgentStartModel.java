package Common;

import Common.Abstract.IPosition;

/**
 * Created by adam on 6/4/16.
 */
public class AgentStartModel {
    public IPosition position;
    int id;
    public String type = "undefined";
    public String agentClassName = "undefined";

    public AgentStartModel(IPosition position, int id) {
        this.position = position;
        this.id = id;
    }

    public AgentStartModel(IPosition position, int id, String agentType, String agentClassName) {
        this.position = position;
        this.id = id;
        this.type = agentType;
        this.agentClassName = agentClassName;
    }

    public Object[] GetArguments() {
        return new Object[]{position};
    }

    public String getAgentType() {
        return type;
    }

    public String getName() {
        return type + id;
    }

    public String getAgentClassName() {
        return agentClassName;
    }
}
