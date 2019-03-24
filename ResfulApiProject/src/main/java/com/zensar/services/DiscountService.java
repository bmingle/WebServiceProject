package src.main.java.com.zensar.services;

import java.util.List;
import java.util.Optional;

import com.zensar.model.LabelTypeEnum;
import com.zensar.model.ProductModel;

public interface DiscountService {
	
	
	/**
	 * 
	 * 
	 * 
	 * @param categoryId
	 * @param priceLabelType
	 * @return List<ProductModel>
	 */
	
	List<ProductModel> getDiscountedProducts(Integer categoryId, Optional<LabelTypeEnum> priceLabelType);

}