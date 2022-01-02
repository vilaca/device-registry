package eu.vilaca.devices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Devices Registry", version = "1.0", description = "Devices registry APIs v1.0"))
public class DeviceRegistryApplication {

	public static final String BASE_PATH = "/devices";

	public static void main(String[] args) {
		SpringApplication.run(DeviceRegistryApplication.class, args);
	}
}
