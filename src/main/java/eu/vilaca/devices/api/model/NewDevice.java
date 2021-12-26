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
	@NotBlank
	@Size(max = 30)
	private String name;
	@NotBlank
	@Size(max = 20)
	private String brand;

	public NewDevice(String name, String brand) {
		this.brand = brand;
		this.name = name;
	}
}
