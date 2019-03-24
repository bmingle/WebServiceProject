package src.test.java.com.zensar.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.zensar.model.LabelTypeEnum;
import com.zensar.model.ProductModel;
import com.zensar.services.DiscountService;

@RunWith(SpringRunner.class)
@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {
	
	private static final Integer CATEGORY_ID = 600001506;
	private static final Optional<LabelTypeEnum> LABEL_TYPE = Optional.ofNullable(LabelTypeEnum.ShowWasNow);
	private static final Optional<LabelTypeEnum> EMPTY_LABEL_TYPE = Optional.empty();
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private DiscountService service;

//	@Before
//	public void setUp() throws Exception {
//	}

	@Test
	public void given_categoryId_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
		
		List<ProductModel> response = Arrays.asList(new ProductModel());
		
		given(service.getDiscountedProducts(CATEGORY_ID, EMPTY_LABEL_TYPE))
			.willReturn(response);
		
		mockMvc.perform(get("/discount/discountedProductsByCategoryId/{id}", CATEGORY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	
	@Test
    public void given_wrongTypeCategoryId_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
        mockMvc.perform(get("/discount/discountedProductsByCategoryId/{id}", "str")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
	
	@Test
    public void given_categoryId_and_priceLabelType_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
        mockMvc.perform(get("/discount/discountedProductsByCategoryId/{id}?priceLabelType={labelType}", CATEGORY_ID, LABEL_TYPE.get())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
	
	@Test
    public void given_categoryId_and_wrongPriceLabelType_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
        mockMvc.perform(get("/discount/discountedProductsByCategoryId/{id}?priceLabelType={labelType}", CATEGORY_ID, "wrongwrong")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
	
	

}
