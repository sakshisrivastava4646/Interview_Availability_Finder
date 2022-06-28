package app.error;

import app.exception.BusinessException;

/**
 * The type Business error.
 */
public class BusinessError extends RestError {
    private String messageKey;
    private String[] arguments;

    private BusinessError() {
    }

  /**
   * Instantiates a new Business error.
   * @param messageKey the message key
   */
  public BusinessError(String messageKey) {
        this.messageKey = messageKey;
    }

  /**
   * Instantiates a new Business error.
   * @param messageKey the message key
   * @param arguments  the arguments
   */
  public BusinessError(String messageKey, String... arguments) {
        this.messageKey = messageKey;
        this.arguments = arguments;
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
    public BusinessException getException() {
        return new BusinessException(messageKey, arguments);
    }
}