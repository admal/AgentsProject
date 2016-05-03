package Common.AgentClasses;

import Common.Abstract.IPosition;
import Common.Position;

/**
 * Created by jedrek on 03.05.16.
 */
public class Parking {
    public IPosition getPosition() {
        return position;
    }

    public void setPosition(IPosition position) {
        this.position = position;
    }

    private IPosition position;

    public Parking(IPosition position) {
        this.position = position;
    }
}
