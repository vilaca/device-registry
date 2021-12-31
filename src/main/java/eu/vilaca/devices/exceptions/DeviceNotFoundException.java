package eu.vilaca.devices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeviceNotFoundException extends ResponseStatusException {
	public DeviceNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Device not found in Database.");
	}
}
