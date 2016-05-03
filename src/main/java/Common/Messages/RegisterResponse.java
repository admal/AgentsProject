package Common.Messages;

import CarAgent.CarAgent;
import Common.Abstract.ICarHandable;

/**
 * Created by adam on 5/3/16.
 */
public class RegisterResponse extends Message implements ICarHandable
{
    public boolean registered;

    public void Handle(CarAgent agent) {
        if (!registered)
            agent.doDelete();
    }
}
