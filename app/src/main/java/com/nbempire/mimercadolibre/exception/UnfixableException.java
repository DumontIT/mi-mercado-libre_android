package com.nbempire.mimercadolibre.exception;

/**
 * Represents a kind of exceptions that can NOT be fixed on the move and the client must display an error to the user.
 * <p/>
 * Created on 03/06/14, at 23:30.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class UnfixableException extends Throwable {

    /**
     * The serialVersionUID of this exception.
     */
    private static final long serialVersionUID = 1548958641005552537L;

    public UnfixableException(Throwable throwable) {
        super(throwable);
    }
}
