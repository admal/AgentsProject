package Common.Messages;

import jade.core.AID;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class ReservationResponse extends Message {
    AID carID;
    boolean success;
    public ReservationResponse(AID carID, boolean success){

    }
}
