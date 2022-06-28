package app.exception;

import java.util.Arrays;

/**
 * The type Business exception.
 */
public class BusinessException extends RuntimeException {
    private final String messageKey;
    private final String[] arguments;

    /**
     * Instantiates a new Business exception.
     * @param messageKey the message key
     * @param arguments  the arguments
     */
    public BusinessException(String messageKey, String... arguments) {
        super(messageKey);
        this.messageKey = messageKey;
        this.arguments = arguments == null ? new String[0] : arguments;
    }

    /**
     * Gets message key.
     * @return the message key
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Get arguments string [ ].
     * @return the string [ ]
     */
    public String[] getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return super.toString() + ", arguments=" + Arrays.toString(arguments);
    }
}
