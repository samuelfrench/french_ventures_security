package sample.spring.chapter14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import sample.spring.chapter14.domain.Product;
import sample.spring.chapter14.domain.ProductAjaxData;
import sample.spring.chapter14.service.ProductService;
import sample.spring.chapter14.service.WebUtility;

@RestController
@RequestMapping(value = "/rest/product")
public class ProductRestController {
	
	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private WebUtility webUtility;
	
	

	@RequestMapping(value = "/customerProductTable", method = RequestMethod.GET)
	public ProductAjaxData getAjaxData(WebRequest request)
	{
		//TODO 1: set response and accept type
		//TODO 2: Move request parameters into the actual request mapping definition thing
			Integer start = webUtility.safeInteger(request.getParameter("iDisplayStart"));
			Integer length = webUtility.safeInteger(request.getParameter("iDisplayLength"));
			Integer draw =  webUtility.safeInteger(request.getParameter("sEcho"));
		
		
		
		
		ProductAjaxData userProduct = new ProductAjaxData();
		
		
		List<Product> pList = productService.getProductsUser();
		
		//debuggy code
		for(int x = 0; x < 5000; x++)
		{
			pList.addAll(productService.getProductsUser());
		}
		pList = pList.subList(start, length);
		userProduct.setAaData(pList);
		userProduct.setiTotalDisplayRecords(length);
		userProduct.setiTotalRecords(pList.size());
		userProduct.setsEcho(draw);
		return userProduct;
		//end debug
		
	}
}
