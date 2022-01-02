package eu.vilaca.devices.repositories;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@NoArgsConstructor
@Data
@Accessors(chain = true)
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
