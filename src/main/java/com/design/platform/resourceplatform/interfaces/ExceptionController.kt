package com.design.platform.resourceplatform.interfaces

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler
    fun allExceptionHandler(ex: Exception): String {
        if (ex.cause != null) return allExceptionHandler(ex.cause as Exception)

        return if (ex is ConstraintViolationException)
            validationExceptionHandler(ex)
        else ex.localizedMessage
    }

    private fun validationExceptionHandler(ex: ConstraintViolationException): String {
        return ex.constraintViolations.stream()
            .map { "\tproperty \"${it.propertyPath}\" : ${it.message}\n" }
            .collect(Collectors.joining("", "exception from : \n", ""))
    }
}
