package src.main.java.com.zensar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.model.LabelTypeEnum;
import com.zensar.model.ProductModel;
import com.zensar.services.DiscountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/discount")
@Api(value="/discount", description="Everythink For Discount")
public class DiscountController {
	
	@Autowired
	private DiscountService discountService;
	
	@ApiOperation(
			value = "Get discounted products by category_id.", 
			notes = "The array of products should only contain products with a price reduction and should be sorted to show the highest price reduction first. Price reduction is calculated using price.was - price.now. ", 
			response = ProductModel.class,
			responseContainer = "List"
	)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value = "/discountedProductsByCategoryId/{categoryId}")
	public List<ProductModel> getDiscountedProductsByCAtegoryId(@PathVariable(required = true) Integer categoryId, @RequestParam(required = false) LabelTypeEnum priceLabelType){
		
		Optional<LabelTypeEnum> labelType = Optional.ofNullable(priceLabelType);
		
		return discountService.getDiscountedProducts(categoryId, labelType);
	}

}
