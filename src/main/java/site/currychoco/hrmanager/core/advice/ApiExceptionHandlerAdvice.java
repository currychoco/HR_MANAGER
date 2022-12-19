package site.currychoco.hrmanager.core.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import site.currychoco.hrmanager.core.dto.ErrorDto;
import site.currychoco.hrmanager.core.exception.BadRequestException;
import site.currychoco.hrmanager.core.exception.ForbiddenException;
import site.currychoco.hrmanager.core.exception.NotFoundException;
import site.currychoco.hrmanager.core.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.BindException;

@RestControllerAdvice("site.currychoco.hrmanager")
public class ApiExceptionHandlerAdvice {

    // 400 Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        BadRequestException.class
                , MissingServletRequestParameterException.class
                , MissingServletRequestPartException.class
                , ServletRequestBindingException.class
                , HttpMessageNotReadableException.class
                , MethodArgumentNotValidException.class
                , MethodArgumentTypeMismatchException.class
                , BindException.class
    })
    protected ResponseEntity<ErrorDto> badRequestException(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        ErrorDto error = new ErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 401 Unauthorized
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorDto> unauthorizedExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        ErrorDto error = new ErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // 403 Forbidden
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<ErrorDto> forbiddenExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        ErrorDto error = new ErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    // 404 Not Found
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorDto> notFoundException(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        ErrorDto error = new ErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // 500 Internal Server Error
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDto> exceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        ErrorDto error = new ErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
