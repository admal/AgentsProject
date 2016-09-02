package Common.Messages;

import ChargerAgent.ChargerAgent;
import Common.Abstract.IChargerHandable;
import jade.core.AID;

/**
 * Created by jedrek on 02.09.16.
 */
public class ReservationCancel extends Message implements IChargerHandable{

    private AID carAID;

    public ReservationCancel(AID carAID) {
        this.carAID = carAID;
    }

    @Override
    public void Handle(ChargerAgent agent) {
        agent.cancelReservation(carAID);
    }
}
