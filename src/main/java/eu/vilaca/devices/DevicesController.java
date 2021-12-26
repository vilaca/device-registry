package eu.vilaca.devices;

import eu.vilaca.devices.model.Device;
import eu.vilaca.devices.model.NewDevice;
import eu.vilaca.devices.model.UpdateDevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static eu.vilaca.devices.DeviceRegistryApplication.*;

@RestController
@RequestMapping(value = BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class DevicesController {

	private final DevicesService service;

	@PostMapping
	public Mono<Device> create(@Valid @RequestBody NewDevice newDevice) {
		return service.create(newDevice);
	}

	@PutMapping(value = "/{id}")
	public Mono<Device> update(@Valid @RequestBody UpdateDevice newDevice) {
		return service.update(newDevice);
	}

	@GetMapping
	public Flux<Device> list(@Param("brand") String brand) {
		return service.list(brand);
	}

	@GetMapping(value = "/{id}")
	public Mono<Device> listById(@PathVariable("id") String id) {
		return service.listById(id);
	}

	@DeleteMapping(value = "/{id}")
	public Mono<Void> deleteById(@PathVariable("id") String id) {
		return service.deleteById(id);
	}
}