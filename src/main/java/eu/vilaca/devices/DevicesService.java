package eu.vilaca.devices;

import eu.vilaca.devices.model.Device;
import eu.vilaca.devices.model.NewDevice;
import eu.vilaca.devices.model.UpdateDevice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class DevicesService {
	public Mono<Device> create(NewDevice newDevice) {
		return null;
	}

	public Flux<Device> list(String brand) {
		return null;
	}

	public Mono<Device> listById(String id) {
		return null;
	}

	public Mono<Void> deleteById(String id) {
		return null;
	}

	public Mono<Device> update(UpdateDevice newDevice) {
		return null;
	}
}
