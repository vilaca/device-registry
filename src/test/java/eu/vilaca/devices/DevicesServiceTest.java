package eu.vilaca.devices;

import eu.vilaca.devices.repositories.DevicesRepository;
import eu.vilaca.devices.services.DevicesService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DirtiesContext
public class DevicesServiceTest {

	@MockBean
	private DevicesRepository repository;

	@InjectMocks
	private DevicesService service;

	
}
