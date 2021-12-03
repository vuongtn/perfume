package com.dotv.perfume.exception;

import com.dotv.perfume.untils.MessageResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AppException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse handleNotFoundException(AppException ex) {
        return new MessageResponse(ex.getMessage());
    }

//    // Xử lý tất cả các exception chưa được khai báo
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handlerException(Exception ex, WebRequest req) {
//        // Log err
//
//        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//    }

    // Xử lý tất cả các exception chưa được khai báo
//    @ExceptionHandler(Exception.class)
////    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public MessageResponse handlerException(Exception ex) {
//        // Log err
//        return new MessageResponse( ex.getMessage());
//    }
}
