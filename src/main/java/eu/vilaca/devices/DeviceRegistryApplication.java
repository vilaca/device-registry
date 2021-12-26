package eu.vilaca.devices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceRegistryApplication {

	public static final String BASE_PATH = "/devices";

	public static void main(String[] args) {
		SpringApplication.run(DeviceRegistryApplication.class, args);
	}
}
