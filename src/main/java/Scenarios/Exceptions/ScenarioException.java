package Scenarios.Exceptions;

/**
 * Created by adam on 9/6/16.
 */
public class ScenarioException extends Exception {
    public ScenarioException(String message) {
        super("Scenario exception: " + message);
    }
}
