package exceptions;

public class NoCountryException extends Exception {
    public NoCountryException()
    {
        super("Brak kraju na liście obsługiwanych!");
    }
}
