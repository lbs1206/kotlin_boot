package com.example.balnk.exception

import com.example.balnk.dto.error.CustomErrorResponse
import com.example.balnk.dto.error.ErrorCodeErrorResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [CustomException::class])
    fun handlingCustomException(ex: CustomException): ResponseEntity<CustomErrorResponse> {
        val response = CustomErrorResponse(ex.message, ex.code)
        return ResponseEntity.status(ex.status).body(response)
    }

    @ExceptionHandler(value = [ErrorCodeException::class])
    fun handlingErrorCodeException(ex: ErrorCodeException): ResponseEntity<ErrorCodeErrorResponse> {
        val errorCode: ErrorCode = ex.errorCode;
        val response = ErrorCodeErrorResponse(errorCode.message,errorCode.code);
        return ResponseEntity.status(errorCode.status).body(response);
    }

    @ExceptionHandler(value = [DataIntegrityViolationException::class])
    fun handlingDataIntegrityViolationException(ex: DataIntegrityViolationException) : ResponseEntity<CustomErrorResponse>{
        val response = CustomErrorResponse(ex.message?: "", 500001)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}