package src.test.java.com.zensar.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.zensar.domain.ColorSwatch;
import com.zensar.domain.CurrencyEnum;
import com.zensar.domain.Price;
import com.zensar.domain.Product;
import com.zensar.model.LabelTypeEnum;
import com.zensar.model.ProductModel;


public class ProductToProductModelConvertertTest {
	
	private static final String PRODUCT_ID = "123456";
    private static final String TITLE = "title";
	
	
	/*
	 * Convert tests start
	 */
	
	
	ProductToProductModelConverter converter;

    @Before
    public void setUp() throws Exception {
    	
        converter = new ProductToProductModelConverter();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null, null));
    }
	
    @Test
	public void given_product_and_labelTypeIsShowPercDscount_when_convertProductToProductModel_then_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypeEnum> labelType = Optional.of(LabelTypeEnum.ShowPercDscount);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", CurrencyEnum.GBP);
    	price.setCurrency(CurrencyEnum.GBP);
    	price.setNow("5.00");
    	price.setWas(Optional.ofNullable(10.00f));
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or “£10�?.
    	assertEquals("£5", pm.getNowPrice());
    	
    	//ShowPercDscount - in which case return “x% off - now £y.yy�?.
    	assertEquals("50% off - now £5", pm.getPriceLabel());
		
	}
    
    @Test
	public void given_product_and_labelTypeIsShowWasNow_when_convertProductToProductModel_then_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypeEnum> labelType = Optional.of(LabelTypeEnum.ShowWasNow);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", CurrencyEnum.GBP);
    	price.setCurrency(CurrencyEnum.GBP);
    	price.setNow("10.00");
    	price.setWas(Optional.ofNullable(20.00f));
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or “£10�?.
    	assertEquals("£10.00", pm.getNowPrice());
    	
    	// ShowWasNow - in which case return a string saying “Was £x.xx, now £y.yyy�?.
    	assertEquals("Was £20.00, now £10.00", pm.getPriceLabel());
		
	}
    
    @Test
   	public void given_product_and_NUlllabelType_when_convertProductToProductModel_then_returnProductModel() throws Exception {
       	
       	//given
       
       	Optional<LabelTypeEnum> labelType = Optional.empty();
       	
       	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", CurrencyEnum.GBP);
       	price.setCurrency(CurrencyEnum.GBP);
       	price.setNow("10.00");
       	price.setWas(Optional.ofNullable(20.00f));
       	
       	ColorSwatch colorSwatch = new ColorSwatch();
       	
       	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
       	
       	//when
       	ProductModel pm = converter.convert(product, labelType);
       	
       	
       	
       	//then
       	assertEquals(PRODUCT_ID, pm.getId());
       	assertEquals(TITLE, pm.getTitle());
       	
       	//which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or “£10�?.
       	assertEquals("£10.00", pm.getNowPrice());
       	
       	// ShowWasNow - in which case return a string saying “Was £x.xx, now £y.yyy�?.
       	assertEquals("Was £20.00, now £10.00", pm.getPriceLabel());
   		
   	}
    
    
    @Test
	public void given_product_and_labelTypeIsShowWasThenNow_and_then1IsNotNUll_and_than2IsNull_when_convertProductToProductModel_then_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypeEnum> labelType = Optional.of(LabelTypeEnum.ShowWasThenNow);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", CurrencyEnum.GBP);
    	price.setCurrency(CurrencyEnum.GBP);
    	price.setNow("10.00");
    	price.setThen1(Optional.ofNullable(6.12f));
//    	price.setThen2(Optional.ofNullable(7.12f));
    	price.setWas(Optional.ofNullable(20.00f));
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or “£10�?.
    	assertEquals("£10", pm.getNowPrice());
    	
    	// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
    	// £z.zzz�?. If the original price.then2 is not empty use that for the “then�? price otherwise use
    	// the then1 price. If the then1 price is also empty then don’t show the “then�? price.
    	assertEquals("Was £10.00, then £6, now £10", pm.getPriceLabel());
		
	}
    
    @Test
	public void given_product_and_labelTypeIsShowWasThenNow_and_then1NotNUll_and_than2NotNull_when_convertProductToProductModel_then_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypeEnum> labelType = Optional.of(LabelTypeEnum.ShowWasThenNow);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", CurrencyEnum.GBP);
    	price.setCurrency(CurrencyEnum.GBP);
    	price.setNow("10.00");
    	price.setThen1(Optional.ofNullable(6.12f));
    	price.setThen2(Optional.ofNullable(7.12f));
    	price.setWas(Optional.ofNullable(20.00f));
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or “£10�?.
    	assertEquals("£10", pm.getNowPrice());
    	
    	// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
    	// £z.zzz�?. If the original price.then2 is not empty use that for the “then�? price otherwise use
    	// the then1 price. If the then1 price is also empty then don’t show the “then�? price.
    	assertEquals("Was £10.00, then £7, now £10", pm.getPriceLabel());
		
	}
    
    
    @Test
	public void given_product_and_nowPriceIsObject_when_convertProductToProductModel_then_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypeEnum> labelType = Optional.of(LabelTypeEnum.ShowWasNow);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", CurrencyEnum.GBP);
    	price.setCurrency(CurrencyEnum.GBP);
    	price.setNow("{from:\"12.36\", to: \"13:26\"}");
    	price.setThen1(Optional.ofNullable(6.12f));
    	price.setThen2(Optional.ofNullable(7.12f));
    	price.setWas(Optional.ofNullable(20.00f));
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75�?. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00�? or “£10�?.
    	assertEquals("£10", pm.getNowPrice());
    	
    	// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
    	// £z.zzz�?. If the original price.then2 is not empty use that for the “then�? price otherwise use
    	// the then1 price. If the then1 price is also empty then don’t show the “then�? price.
    	assertEquals("Was £20.00, now £13.26", pm.getPriceLabel());
		
	}
	

}
