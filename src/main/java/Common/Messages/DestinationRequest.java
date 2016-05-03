package Common.Messages;

import Common.Abstract.IPosition;
import Common.Position;

/**
 * Created by jedrek on 03.05.16.
 */
public class DestinationRequest extends Message {
    IPosition clientPosition;

    public DestinationRequest(Position clientPosition){
        this.clientPosition = clientPosition;
    }
}
