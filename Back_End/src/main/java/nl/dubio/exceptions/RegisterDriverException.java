package nl.dubio.exceptions;

public class RegisterDriverException extends DatabaseException {

    public RegisterDriverException() {
        super("Cannot register the JDBC drivers");
    }

}
