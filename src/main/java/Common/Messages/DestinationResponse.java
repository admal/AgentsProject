package Common.Messages;

import Common.Abstract.IPosition;
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
