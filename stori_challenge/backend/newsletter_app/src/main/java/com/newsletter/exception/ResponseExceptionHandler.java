package com.newsletter.exception;

import java.nio.file.FileAlreadyExistsException;
import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
	String errorMessager = "";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			errorMessager = error.getDefaultMessage();
		});

		ResponseDetails execeptionResponse = new ResponseDetails(new Date(), "MethodArgumentNotValidException",
				errorMessager);

		errorMessager = "";
		return new ResponseEntity<>(execeptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ResponseDetails execeptionResponse = new ResponseDetails(new Date(), "Parameter is missing",
				ex.getLocalizedMessage());

		return new ResponseEntity<>(execeptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		ex.getConstraintViolations().forEach((error) -> {
			errorMessager += error.getMessage() + ".";
		});

		ResponseDetails execeptionResponse = new ResponseDetails(new Date(), "Constraint Violation", errorMessager);

		errorMessager = "";
		return new ResponseEntity<>(execeptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleFileSizeLimitExceeded(MaxUploadSizeExceededException ex) {
		ResponseDetails execeptionResponse = new ResponseDetails(new Date(), "File to big",
				"Maximum upload size was exceeded");

		return new ResponseEntity<>(execeptionResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		ResponseDetails execeptionResponse = new ResponseDetails(new Date(), "Error ",
				"Generic Error: " + ex.getMessage());

		return new ResponseEntity<>(execeptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FileAlreadyExistsException.class)
	public ResponseEntity<Object> handleFileExists(Exception ex) {
		ResponseDetails execeptionResponse = new ResponseDetails(new Date(), "File already exists",
				"A file with that name already exists " + ex.getMessage() + ex.getLocalizedMessage());

		return new ResponseEntity<>(execeptionResponse, HttpStatus.BAD_REQUEST);
	}
}
