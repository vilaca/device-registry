package eu.vilaca.devices;

import eu.vilaca.devices.api.model.NewDevice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@DirtiesContext
public class DevicesServiceTest {

	private WebTestClient webTestClient;

	@Autowired
	private DevicesService service;

	@Mock
	private DevicesRepository repository;

	@Test
	void GIVEN_device_WHEN_calling_create_THEN_repository_create_is_called_AND_return_device() {


	}


	@Test
	void GIVEN_device_name_too_long_WHEN_calling_create_THEN_return_BAD_REQUEST() {

		final var newDevice = new NewDevice().setName("!".repeat(31)).setBrand("brand");

		webTestClient.post()
				.uri(DeviceRegistryApplication.BASE_PATH)
				.bodyValue(newDevice)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void GIVEN_device_missing_brand_to_be_created_WHEN_calling_create_THEN_return_BAD_REQUEST() {

		final var newDevice = new NewDevice().setName("name");

		webTestClient.post()
				.uri(DeviceRegistryApplication.BASE_PATH)
				.bodyValue(newDevice)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void GIVEN_device_brand_too_long_WHEN_calling_create_THEN_return_BAD_REQUEST() {

		final var newDevice = new NewDevice().setName("name").setBrand("!".repeat(21));

		webTestClient.post()
				.uri(DeviceRegistryApplication.BASE_PATH)
				.bodyValue(newDevice)
				.exchange()
				.expectStatus().isBadRequest();
	}
}
