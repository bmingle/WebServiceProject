package src.main.java.com.zensar.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.zensar.domain.Price;
import com.zensar.domain.Product;
import com.zensar.model.LabelTypeEnum;
import com.zensar.model.ProductModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductToProductModelConverter {
	
	@Autowired
	private ColorSwatchToColorSwatchModelConverter colorSwatchConverter;
	
	public ProductModel convert(Product source, Optional<LabelTypeEnum> labelType) {
		
		if(source==null)
			return null;

		String priceLabel = printPriceLabel(labelType, source.getPrice());
		
		log.info("product id {}", source.getProductId());
		
		
		ProductModel target = new ProductModel();
		
		target.setId(source.getProductId());
		target.setTitle(source.getTitle());
		target.setPriceLabel(priceLabel);
		target.setNowPrice(nowPrice(source.getPrice()));
		
		//TODO: I don't have a time
//		target.setColorSwatches(
//				source.getColorSwatches().stream().map(x -> {return colorSwatchConverter.convert(x);}
//						).collect(Collectors.toList())
//			);
		
		log.info("product {}", target);
		
		return target;
		
	}
	
	/**
	 * which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or  “£10�?. 
	 * @param price Price.class
	 * @return String
	 */
	private String nowPrice(Price price) {
		Float nowPrice;
		
		try {
			nowPrice = Float.parseFloat((String)price.getNow());
		}catch (Exception e) {
			//TODO: Don't forget ethe bind object to PriceNowModel
			nowPrice = 1.23f;
		}	
		return nowPrice <10 ? price.getCurrency().getResponse()+Math.round(nowPrice) : price.getCurrency().getResponse()+ nowPrice ;
	}
	
	
	/**
	 * price label processes
	 * @param labelType Optional<LabelTypeEnum>
	 * @param price Price
	 * @return String
	 */
	//TODO: I don't have a time
	private String printPriceLabel(Optional<LabelTypeEnum> labelType, Price price) {
		
		String response="";
		
		LabelTypeEnum priceLabel = labelType.map(x -> {
									return x;
								}).orElse(LabelTypeEnum.ShowWasNow);
		
		
		if(LabelTypeEnum.ShowWasNow.equals(priceLabel)) {
			
			response = price.getWas().map( x -> {
				return "Was " +price.getCurrency().getResponse()+x+", now "+nowPrice(price);
			}).orElse("Was "+nowPrice(price)+", now "+nowPrice(price));
		
		}
		else if(LabelTypeEnum.ShowWasThenNow.equals(priceLabel)) 
			log.info(" {}",priceLabel);
		else if(LabelTypeEnum.ShowPercDscount.equals(priceLabel)) 
			log.info(" {}",priceLabel);
		

		
		return response;
	}
	

	
	
	/**
	 * Calculate discount persantange
	 * @param nowPrice Float
	 * @param beforePrice Float
	 * @return discountPersantage Float
	 */
	private Float calculateDiscountPersentange(Float nowPrice, Float beforePrice) {
		return ((nowPrice-beforePrice)/beforePrice)*100;
	}
	

}
