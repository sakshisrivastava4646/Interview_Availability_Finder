package app.error;

import app.exception.TechnicalException;

/**
 * The type Technical error.
 */
public class TechnicalError extends RestError {
    private String message;

    private TechnicalError() {
    }

    /**
     * Instantiates a new Technical error.
     * @param exception the exception
     */
    public TechnicalError(Exception exception) {
        super(exception);
        this.message = exception.getMessage();
    }

    /**
     * Gets message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public TechnicalException getException() {
        return new TechnicalException(message);
    }
}
