package WebServlets;

import MasterAgent.IMasterAgent;

/**
 * Created by adam on 6/4/16.
 */
public class WebGlobals
{
    private static WebGlobals instance;
    public IMasterAgent masterAgent;
    protected WebGlobals(){}
    public static WebGlobals getInstance()
    {
        if (instance == null)
            instance = new WebGlobals();
        return instance;
    }
}
