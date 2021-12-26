package eu.vilaca.devices;

import eu.vilaca.devices.api.exceptions.DeviceNotFoundException;
import eu.vilaca.devices.api.model.NewDevice;
import eu.vilaca.devices.api.model.UpdatedDevice;
import eu.vilaca.devices.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class DevicesService {

	private final DevicesRepository repository;

	public Mono<Device> create(NewDevice newDevice) {
		return repository.save(new Device(newDevice.getName(), newDevice.getBrand()));
	}

	public Mono<Device> update(Long id, UpdatedDevice device) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new DeviceNotFoundException()))
				.flatMap(dev -> update(device, dev));
	}

	public Flux<Device> list(String brand) {
		return isNullOrBlank(brand) ? repository.findAll() : repository.findByBrand(brand);
	}

	public Mono<Device> findById(long id) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new DeviceNotFoundException()));
	}

	public Mono<Void> deleteById(Long id) {
		return repository.deleteById(id);
	}

	private Mono<Device> update(UpdatedDevice device, Device dev) {
		if (isNullOrBlank(device.getName())) {
			dev.setName(device.getName());
		}
		if (isNullOrBlank(device.getBrand())) {
			dev.setBrand(device.getBrand());
		}
		return repository.save(dev);
	}

	private boolean isNullOrBlank(String toValidate) {
		return toValidate == null || toValidate.isBlank();
	}
}

