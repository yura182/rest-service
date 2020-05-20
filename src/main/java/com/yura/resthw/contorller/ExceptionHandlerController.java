package com.yura.resthw.contorller;

import com.yura.resthw.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionDto> handleEntityNotFound(RuntimeException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(exceptionDto.getHttpStatus())
                .body(exceptionDto);
    }
}
