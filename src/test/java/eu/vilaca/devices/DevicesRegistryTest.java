package eu.vilaca.devices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DevicesRegistryTest {
	@Test
	void main_SHOULD_loadContext_WHEN_applicationstarts() {
		assertNotNull(this);
	}
}
