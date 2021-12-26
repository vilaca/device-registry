package eu.vilaca.devices;

import eu.vilaca.devices.model.Device;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DevicesRepository extends ReactiveCrudRepository<Device, Long> {
	Flux<Device> findByBrand(String brand);
}