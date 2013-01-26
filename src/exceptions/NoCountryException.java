package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Bartek Grzebinoga
 * Date: 26.01.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class NoCountryException extends Exception {
    public NoCountryException()
    {
        super("Brak kraju na liście obsługiwanych!");
    }
}
