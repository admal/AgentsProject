package Common.Messages;

import jade.core.AID;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeRequest extends Message {
    public AID agentId;
    public ChargeRequest(AID agentId){
        this.agentId = agentId;
    }
}
