package Common.Messages;

import Common.Abstract.IPosition;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationResponse extends Message {
    IPosition position;

    public DestinationResponse(IPosition position) {
        this.position = position;
    }
}
