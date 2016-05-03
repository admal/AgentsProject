package Common;

import jade.core.AID;

/**
 * Created by adam on 5/3/16.
 */
public class Globals
{
    private static Globals instance;
    public final AID MASTER = new AID("master", AID.ISLOCALNAME);
    public final int MAX_WIDTH = 200;
    public final int MAX_HEIGHT = 200;
    protected Globals(){}
    public Globals getInstance()
    {
        if (instance == null)
            instance = new Globals();
        return instance;
    }
}
