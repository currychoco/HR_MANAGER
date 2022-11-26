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
    protected void badRequestException(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        res.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    // 401 Unauthorized
    @ExceptionHandler(UnauthorizedException.class)
    protected void unauthorizedExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        res.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }

    // 403 Forbidden
    @ExceptionHandler(ForbiddenException.class)
    protected void forbiddenExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        res.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    // 404 Not Found
    @ExceptionHandler(NotFoundException.class)
    protected void notFoundException(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        res.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    protected void exceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception exception) throws IOException {

        exception.printStackTrace();
        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
