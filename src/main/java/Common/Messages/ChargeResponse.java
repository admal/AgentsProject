package Common.Messages;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeResponse extends Message {
    int waitingTime;
    public ChargeResponse(int waitingTime){
        this.waitingTime = waitingTime;
    }

}
