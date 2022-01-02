package eu.vilaca.devices.api;


import eu.vilaca.devices.model.NewDevice;
import eu.vilaca.devices.model.SavedDevice;
import eu.vilaca.devices.model.UpdatedDevice;
import eu.vilaca.devices.services.DevicesService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.net.URI;

import static eu.vilaca.devices.DeviceRegistryApplication.*;

@RestController
@RequestMapping(value = BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class DevicesController {

	private final DevicesService service;

	@ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SavedDevice.class)))
	@ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema()))
	@PostMapping
	public Mono<ResponseEntity<SavedDevice>> create(@Valid @RequestBody Mono<NewDevice> body) {
		return body.flatMap(newDevice -> service.create(newDevice)
				.flatMap(s -> Mono.just(ResponseEntity.created(URI.create(BASE_PATH + "/" + s.getId())).body(s))));
	}

	@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SavedDevice.class)))
	@ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema()))
	@ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema()))
	@PutMapping(value = "/{id}")
	public Mono<SavedDevice> update(@NotBlank @PathVariable("id") Long id, @Valid @RequestBody Mono<UpdatedDevice> updatedDevice) {
		return updatedDevice.flatMap(device -> service.update(id, device));
	}

	@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SavedDevice.class)))
	@ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema()))
	@ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema()))
	@PatchMapping(value = "/{id}")
	public Mono<SavedDevice> partialUpdate(@NotBlank @PathVariable("id") Long id, @RequestBody Mono<UpdatedDevice> updatedDevice) {
		return updatedDevice.flatMap(device -> service.partialUpdate(id, device));
	}

	@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SavedDevice.class)))
	@ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema()))
	@ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema()))
	@GetMapping(value = "/{id}")
	public Mono<SavedDevice> listById(@NotBlank @PathVariable("id") Long id) {
		return service.findById(id);
	}

	@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SavedDevice.class))))
	@GetMapping
	public Flux<SavedDevice> list(@Param("brand") String brand) {
		return service.list(brand);
	}

	@ApiResponse(responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema()))
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Mono<Void> deleteById(@NotBlank @PathVariable("id") Long id) {
		return service.deleteById(id);
	}
}