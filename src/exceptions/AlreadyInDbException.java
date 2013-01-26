package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Bartek Grzebinoga
 * Date: 26.01.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class AlreadyInDbException extends Exception {
    public AlreadyInDbException()
    {
        super("Wpis znajduje się już w bazie danych");
    }
}
