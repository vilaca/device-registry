package eu.vilaca.devices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateDevice {
	private Long id;
	private String name;
	private String brand;
}
