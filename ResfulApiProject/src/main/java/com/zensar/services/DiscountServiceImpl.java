package src.main.java.com.zensar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.converter.ProductToProductModelConverter;
import com.zensar.model.LabelTypeEnum;
import com.zensar.model.ProductModel;
import com.zensar.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {
	
	@Autowired
	private DiscountRepository discountRepository;

	@Override
	public List<ProductModel> getDiscountedProducts(Integer categoryId, Optional<LabelTypeEnum> priceLabelType) {
		
		
		// converter
		ProductToProductModelConverter productConverter = new ProductToProductModelConverter();
		
		// convert product to productModel and map elements to productModel list
		List<ProductModel> productModels = discountRepository.getDiscountedProducts(categoryId).stream().map(product ->
								{ return productConverter.convert(product, priceLabelType);}
						).collect(Collectors.toList());
		
		return productModels;
	}

	

}
