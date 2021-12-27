package eu.vilaca.devices;

import eu.vilaca.devices.api.model.NewDevice;
import eu.vilaca.devices.repositories.DevicesRepository;
import eu.vilaca.devices.services.DevicesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.*;

public class DevicesServiceTest {

	private DevicesRepository mockRepository = Mockito.mock(DevicesRepository.class);

	private DevicesService service = new DevicesService(mockRepository);

	@Test
	void GIVEN_empty_brand_parameter_WHEN_list_THEN_findAll_is_called() {
		when(mockRepository.findAll()).thenReturn(Flux.empty());
		service.list("");
		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void GIVEN_not_empty_brand_parameter_WHEN_list_THEN_findAll_is_called() {
		when(mockRepository.findByBrand(anyString())).thenReturn(Flux.empty());
		service.list("brand-name");
		verify(mockRepository, times(1)).findByBrand(anyString());
	}
}
