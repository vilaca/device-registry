package eu.vilaca.devices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NewDevice {
	private String name;
	private String brand;

	public NewDevice(String name, String brand) {
		this.brand = brand;
		this.name = name;
	}
}
