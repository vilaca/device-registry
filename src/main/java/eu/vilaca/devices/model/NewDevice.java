package eu.vilaca.devices.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class NewDevice {
	@NotBlank(message = "Field [name] should not be blank")
	@Size(max = 30, message = "Field [name] length should be less or equal to 30")
	private String name;
	@NotBlank(message = "Field [brand] should not be blank")
	@Size(max = 20, message = "Field [brand] length should be less or equal to 20")
	private String brand;

	public NewDevice(String name, String brand) {
		this.brand = brand;
		this.name = name;
	}
}
