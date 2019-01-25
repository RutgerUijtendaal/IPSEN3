package nl.dubio.exceptions;

public class ExecuteStatementException extends DatabaseException {
    
    public ExecuteStatementException() {
        super("Failed to execute a Statement");
    }
    
}
