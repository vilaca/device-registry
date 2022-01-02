package eu.vilaca.devices.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdatedDevice {
	@Size(max = 30, message = "Field [name] length should be less or equal to 30")
	@NotBlank(message = "Field [name] should not be blank")
	private String name;
	@Size(max = 20, message = "Field [brand] length should be less or equal to 20")
	@NotBlank(message = "Field [brand] should not be blank")
	private String brand;
}
