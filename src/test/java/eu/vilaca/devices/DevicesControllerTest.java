package eu.vilaca.devices;

import eu.vilaca.devices.api.DevicesController;
import eu.vilaca.devices.model.NewDevice;
import eu.vilaca.devices.model.SavedDevice;
import eu.vilaca.devices.repositories.Device;
import eu.vilaca.devices.services.DevicesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DirtiesContext
public class DevicesControllerTest {

	private WebTestClient webTestClient;

	@MockBean
	private DevicesService mockService;

	@InjectMocks
	private DevicesController controller;

	@LocalServerPort
	private int randomServerPort;

	@BeforeEach
	public void setup() {
		this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
	}

	@Test
	void GIVEN_valid_device_to_be_created_WHEN_calling_create_THEN_return_OK() {

		final var newDevice = new NewDevice("name", "brand");
		when(mockService.create(any())).thenReturn(Mono.just(new SavedDevice().setId(0L)));

		webTestClient.post()
				.uri(DeviceRegistryApplication.BASE_PATH)
				.bodyValue(newDevice)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().exists("Location")
				.expectBody(Device.class)
				.value(device -> assertNotNull(device.getId()));

		verify(mockService, times(1)).create(any());
	}


	@Test
	void GIVEN_device_missing_name_to_be_created_WHEN_calling_create_THEN_return_BAD_REQUEST() {

		final var newDevice = new NewDevice().setBrand("brand");

		webTestClient.post()
				.uri(DeviceRegistryApplication.BASE_PATH)
				.bodyValue(newDevice)
				.exchange()
				.expectStatus().isBadRequest();
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
