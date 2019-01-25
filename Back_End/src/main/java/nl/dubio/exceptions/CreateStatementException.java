package nl.dubio.exceptions;

public class CreateStatementException extends DatabaseException {

    public CreateStatementException() {
        super("Failed to create a statement");
    }

}
