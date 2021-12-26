package eu.vilaca.devices;

import eu.vilaca.devices.model.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

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
}
