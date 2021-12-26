package eu.vilaca.devices.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class NewDevice {
	@NotBlank(message = "Device [name] should not be blank")
	@Size(max = 30, message = "Device [name] length should be less or equal to 30.")
	private String name;
	@NotBlank(message = "Device [brand] should not be blank")
	@Size(max = 20, message = "Device [brand] length should be less or equal to 20.")
	private String brand;

	public NewDevice(String name, String brand) {
		this.brand = brand;
		this.name = name;
	}
}
