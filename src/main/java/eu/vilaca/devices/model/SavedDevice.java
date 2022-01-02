package eu.vilaca.devices.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@ToString
public class SavedDevice {
	private Long id;
	private String name;
	private String brand;
	private LocalDateTime created;
}
