package eu.vilaca.devices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Device {
	@Id
	private Long id;
	private String name;
	private String brand;

	private LocalDateTime created;

	public Device(String name, String brand) {
		this.brand = brand;
		this.name = name;
		this.setCreated(LocalDateTime.now());
	}
}
