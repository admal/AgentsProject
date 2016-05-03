package Common.Messages;

import Common.IPosition;
import Common.Position;


/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationResponse extends Message {
    IPosition position;

    public DestinationResponse(Position position) {
        this.position = position;
    }
}
