package eu.vilaca.devices.services;

import eu.vilaca.devices.model.NewDevice;
import eu.vilaca.devices.model.SavedDevice;
import eu.vilaca.devices.model.UpdatedDevice;
import eu.vilaca.devices.exceptions.DeviceNotFoundException;
import eu.vilaca.devices.repositories.Device;
import eu.vilaca.devices.repositories.DevicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class DevicesService {

	private final DevicesRepository repository;

	public Mono<SavedDevice> create(NewDevice newDevice) {
		return repository.save(new Device(newDevice.getName(), newDevice.getBrand()))
				.flatMap(device -> Mono.just(map2SavedDevice(device)));
	}

	public Mono<SavedDevice> update(Long id, UpdatedDevice updatedDevice) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new DeviceNotFoundException()))
				.flatMap(device -> repository.save(update(updatedDevice, device)))
				.flatMap(updated -> Mono.just(map2SavedDevice(updated)));
	}

	public Mono<SavedDevice> partialUpdate(Long id, UpdatedDevice updatedDevice) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new DeviceNotFoundException()))
				.flatMap(device -> repository.save(partialUpdate(updatedDevice, device)))
				.flatMap(updated -> Mono.just(map2SavedDevice(updated)));
	}

	public Flux<SavedDevice> list(String brand) {
		return (isNullOrBlank(brand) ? repository.findAll() : repository.findByBrand(brand))
				.flatMap(device -> Mono.just(map2SavedDevice(device)));
	}

	public Mono<SavedDevice> findById(long id) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new DeviceNotFoundException()))
				.flatMap(savedDevice -> Mono.just(map2SavedDevice(savedDevice)));
	}

	public Mono<Void> deleteById(Long id) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new DeviceNotFoundException()))
				.flatMap(repository::delete);
	}

	private SavedDevice map2SavedDevice(Device device) {
		return new SavedDevice().setId(device.getId())
				.setName(device.getName())
				.setBrand(device.getBrand())
				.setCreated(device.getCreated());
	}

	private Device update(UpdatedDevice updatedDevice, Device device) {
		return device.setName(updatedDevice.getName())
				.setBrand(updatedDevice.getBrand());
	}

	private Device partialUpdate(UpdatedDevice updatedDevice, Device device) {
		if (!isNullOrBlank(updatedDevice.getName())) {
			device.setName(updatedDevice.getName());
		}
		if (!isNullOrBlank(updatedDevice.getBrand())) {
			device.setBrand(updatedDevice.getBrand());
		}
		return device;
	}

	private boolean isNullOrBlank(String toValidate) {
		return toValidate == null || toValidate.isBlank();
	}
}

