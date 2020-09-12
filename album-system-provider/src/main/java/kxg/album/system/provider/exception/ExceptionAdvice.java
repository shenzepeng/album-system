package kxg.album.system.provider.exception;

import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.constant.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * 要写注释呀
 */
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     * @param ex
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public KxgResponse<String> defaultErrorHandler(MethodArgumentNotValidException ex) {
        StringBuilder result = new StringBuilder();
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        if(errors != null){
            for (int i = 0; i < errors.size(); i++) {
                FieldError error = errors.get(i);
                result.append((i + 1)).append(": ").append(error.getDefaultMessage()).append(";");
            }
        }
        return KxgResponse.create(ReturnCode.ARGUMENT_NOT_VALID, result.toString());
    }

}