package com.yvanscoop.beignetShop.ErrorHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class RestErrorHandler {

    @Autowired
    MessageSource messageSource;


    // method to handle validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FieldValidationErrorDetails> handleValidationError(
            MethodArgumentNotValidException mNotValidException,
            HttpServletRequest request) {
        FieldValidationErrorDetails fErrorDetails =
                new FieldValidationErrorDetails();
        fErrorDetails.setError_timeStamp(new Date().getTime());
        fErrorDetails.setError_status(HttpStatus.BAD_REQUEST.value());
        fErrorDetails.setError_title("Field Validation Error");
        fErrorDetails.setError_detail("Input Field Validation Failed");
        fErrorDetails.setError_developer_Message(
                mNotValidException.getClass().getName());
        fErrorDetails.setError_path(request.getRequestURI());
        BindingResult result = mNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            FieldValidationError fError = processFieldError(error);
            List<FieldValidationError> fValidationErrorsList =
                    fErrorDetails.getErrors().get(error.getField());
            if (fValidationErrorsList == null) {
                fValidationErrorsList =
                        new ArrayList<>();
            }
            fValidationErrorsList.add(fError);
            fErrorDetails.getErrors().put(
                    error.getField(), fValidationErrorsList);
        }
        return new ResponseEntity<>(
                fErrorDetails, HttpStatus.BAD_REQUEST);
    }
    // method to process field error
    private FieldValidationError processFieldError(final FieldError error) {
        FieldValidationError fieldValidationError =
                new FieldValidationError();
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            System.out.println(currentLocale);
            String msg = messageSource.getMessage(
                    error.getDefaultMessage(), null, currentLocale);
            fieldValidationError.setField(error.getField());
            fieldValidationError.setType(MessageType.ERROR);
            fieldValidationError.setMessage(msg);
        }
        return fieldValidationError;
    }
}
