package src.main.java.com.zensar.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.zensar.domain.ColorSwatch;
import com.zensar.model.ColorSwatchModel;


@Component
public class ColorSwatchToColorSwatchModelConverter implements Converter<ColorSwatch, ColorSwatchModel> {

	@Override
	public ColorSwatchModel convert(ColorSwatch source) {
		
		if(source==null)
			return null;
		
		ColorSwatchModel target = new ColorSwatchModel();
		
		target.setColor(source.getColor());
		target.setSkuid(source.getSkuId());
		target.setRgbColor(source.getBasicColor());
		
		return target;
	}

}
