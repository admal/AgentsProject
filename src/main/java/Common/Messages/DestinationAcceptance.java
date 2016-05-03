package Common.Messages;

import Common.Abstract.IPosition;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationAcceptance extends Message {
    IPosition clientPosition;

    public DestinationAcceptance(IPosition clientPosition) {
        this.clientPosition = clientPosition;
    }
}
