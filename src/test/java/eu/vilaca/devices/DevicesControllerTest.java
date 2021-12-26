package eu.vilaca.devices;


import eu.vilaca.devices.api.model.NewDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DirtiesContext
public class DevicesControllerTest {

	private WebTestClient webTestClient;

	@Autowired
	private DevicesService service;

	@LocalServerPort
	private int randomServerPort;

	@BeforeEach
	public void setup() {
		this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
	}
//
//	@Test
//	void GIVEN_valid_device_to_be_created_WHEN_calling_create_THEN_return_OK() {
//
//		final var newDevice = new NewDevice("name", "brand");
//
//		webTestClient.post()
//				.uri(DeviceRegistryApplication.BASE_PATH)
//				.bodyValue(newDevice)
//				.exchange()
//				.expectStatus().isOk()
//				.expectBody(Device.class)
//				.value(device -> assertNotNull(device.getId()));
//	}

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
