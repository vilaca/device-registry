package eu.vilaca.devices.api;

import eu.vilaca.devices.api.exceptions.DeviceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(DeviceNotFoundException.class)
	public ResponseEntity<Void> example(NumberFormatException exception) {
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<List<String>> handleException(WebExchangeBindException e) {
		final var errors = e.getBindingResult()
				.getAllErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> handleConstraint(ConstraintViolationException e) {
		return ResponseEntity.badRequest().body(e.getConstraintViolations().stream().map(
				constraintViolation -> constraintViolation.getMessage()
		).collect(Collectors.toList()));
	}
}
