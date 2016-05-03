package Common.Messages;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ChargeResponse extends Message {
    long waitingTime;
    public ChargeResponse(long waitingTime){
        this.waitingTime = waitingTime;
    }
}
