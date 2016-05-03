package Common.Messages;

import Common.AgentType;
import Common.IPosition;
import jade.core.AID;

import java.io.Serializable;

/**
 * Created by adam on 5/3/16.
 */
public class Register extends Message
{
    public AgentType type;
    public AID agentId;
    public IPosition currPosition;
}

