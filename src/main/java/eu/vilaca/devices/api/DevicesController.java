package eu.vilaca.devices.api;


import eu.vilaca.devices.dto.NewDevice;
import eu.vilaca.devices.dto.SavedDevice;
import eu.vilaca.devices.dto.UpdatedDevice;
import eu.vilaca.devices.services.DevicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static eu.vilaca.devices.DeviceRegistryApplication.*;

@RestController
@RequestMapping(value = BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class DevicesController {

	private final DevicesService service;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Mono<SavedDevice> create(@Valid @RequestBody NewDevice device) {
		return service.create(device);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<SavedDevice> update(@NotBlank @PathVariable("id") Long id, @Valid @RequestBody UpdatedDevice device) {
		return service.update(id, device);
	}

	@PatchMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<SavedDevice> partialUpdate(@NotBlank @PathVariable("id") Long id, @RequestBody UpdatedDevice device) {
		return service.partialUpdate(id, device);
	}

	@GetMapping(value = "/{id}")
	public Mono<SavedDevice> listById(@NotBlank @PathVariable("id") Long id) {
		return service.findById(id);
	}

	@GetMapping
	public Flux<SavedDevice> list(@Param("brand") String brand) {
		return service.list(brand);
	}

	@DeleteMapping(value = "/{id}")
	public Mono<Void> deleteById(@NotBlank @PathVariable("id") Long id) {
		return service.deleteById(id);
	}
}