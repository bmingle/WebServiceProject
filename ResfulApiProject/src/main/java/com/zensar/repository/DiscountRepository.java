package src.main.java.com.zensar.repository;

import java.util.List;

import com.zensar.domain.Product;

public interface DiscountRepository {
	
	List<Product> getDiscountedProducts(Integer categoryId);

}
