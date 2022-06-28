package app.controller.utils;

import app.error.BusinessError;
import app.error.RestError;
import app.error.TechnicalError;
import app.exception.BusinessException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Exception handler controller.
 */
@ControllerAdvice
public class ExceptionHandlerController {
    /**
     * Handle business rule validation error rest error.
     * @param request   the request
     * @param response  the response
     * @param exception the exception
     * @return the rest error
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestError handleBusinessRuleValidationError(
            HttpServletRequest request, HttpServletResponse response, Exception exception) {

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            return new BusinessError(businessException.getMessageKey(), businessException.getArguments());
        } else {
            return new TechnicalError(exception);
        }
    }
}
