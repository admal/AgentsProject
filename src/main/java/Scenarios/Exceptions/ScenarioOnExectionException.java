package Scenarios.Exceptions;

/**
 * Created by adam on 9/6/16.
 */
public class ScenarioOnExectionException extends Exception {
    public ScenarioOnExectionException(String message, String scenarioName) {
        super("On \'"+scenarioName+"\': " + message);
    }
}
