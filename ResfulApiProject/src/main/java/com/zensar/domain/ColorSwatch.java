package src.main.java.com.zensar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColorSwatch {
	private String basicColor;
	private String skuId;
	private String color;
}
