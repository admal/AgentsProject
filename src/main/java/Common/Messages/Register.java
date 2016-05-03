package Common.Messages;

import Common.AgentType;
import jade.core.AID;

import java.io.Serializable;

/**
 * Created by adam on 5/3/16.
 */
public class Register implements Serializable
{
    public AgentType type;
    public AID agentId;
}

