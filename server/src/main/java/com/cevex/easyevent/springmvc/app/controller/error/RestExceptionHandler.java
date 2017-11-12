package com.cevex.easyevent.springmvc.app.controller.error;

import com.cevex.easyevent.springmvc.share.framework.error.exception.AlreadyExistsException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.EmptyException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
import com.cevex.easyevent.springmvc.share.framework.error.exception.WrongParameterException;
import com.cevex.easyevent.springmvc.share.framework.error.model.ErrorCause;
import com.cevex.easyevent.springmvc.share.framework.error.model.ErrorMessage;
import com.cevex.easyevent.springmvc.share.framework.error.model.FieldValidationErrorCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ValidationErrorMapper validationErrorMapper;

    /**
     * Format the exception to rest error format
     *
     * @param status  - http response status
     * @param message - Message of the error
     * @param ex      - original exception
     * @return The response entity well format
     */
    private ResponseEntity<ErrorCause> formatToRestError(HttpStatus status, String message, RuntimeException ex) {
        ErrorCause errorMessage = new ErrorCause(message, ex.getMessage());
        return new ResponseEntity<>(errorMessage, status);
    }

    //=========================================================================
    //                  HTTP 204
    //=========================================================================

    @ExceptionHandler(value = {EmptyException.class})
    protected ResponseEntity<Void> handleEmpty(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //=========================================================================
    //                  HTTP 400
    //=========================================================================

    @ExceptionHandler(value = {WrongParameterException.class})
    protected ResponseEntity<List<FieldValidationErrorCause>> handleWrongParameter(RuntimeException ex, WebRequest request) {
        BindingResult validationResult = ((WrongParameterException) ex).getBindingResult();
        return new ResponseEntity<>(validationErrorMapper.mapValidationErrorList(validationResult),
                HttpStatus.BAD_REQUEST);
    }

    //=========================================================================
    //                  HTTP 404
    //=========================================================================

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorCause> handleNotFound(RuntimeException ex, WebRequest request) {
        return formatToRestError(HttpStatus.NOT_FOUND, "Not found", ex);
    }

    //=========================================================================
    //                  HTTP 409
    //=========================================================================

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<ErrorCause> handleConflict(RuntimeException ex, WebRequest request) {
        return formatToRestError(HttpStatus.CONFLICT, "Already exist", ex);
    }

    //=========================================================================
    //                  HTTP 500
    //=========================================================================

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<ErrorCause> handleInternalServerError(RuntimeException ex, WebRequest request) {
        return formatToRestError(HttpStatus.INTERNAL_SERVER_ERROR, "Server error", ex);
    }

    //=========================================================================
    //                  HTTP 501
    //=========================================================================

    @ExceptionHandler(value = {NotImplementedException.class})
    protected ResponseEntity<ErrorCause> handleNotImplemented(RuntimeException ex, WebRequest request) {
        return formatToRestError(HttpStatus.NOT_IMPLEMENTED, "Should be available soon...", ex);
    }
}
