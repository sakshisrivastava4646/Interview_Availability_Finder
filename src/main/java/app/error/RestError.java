package app.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;

/**
 * The type Rest error.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public abstract class RestError implements Serializable {
    @JsonIgnore
    private Exception exception;

  /**
   * Instantiates a new Rest error.
   */
  protected RestError() {
    }

  /**
   * Instantiates a new Rest error.
   * @param exception the exception
   */
  protected RestError(Exception exception) {//previously public
        this.exception = exception;
    }

  /**
   * Gets exception.
   * @return the exception
   */
  @JsonIgnore
    public abstract RuntimeException getException();
}
