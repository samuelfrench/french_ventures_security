package sample.spring.chapter14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	

	@RequestMapping(value = "/customerProductTable", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProductAjaxData getAjaxData(WebRequest request, @RequestParam("start") String rawStart, @RequestParam("length") String rawLength, @RequestParam("draw") String rawDraw)
	{
		//TODO 1: set response and accept type
		//TODO 2: Move request parameters into the actual request mapping definition thing
			Integer start = webUtility.safeInteger(rawStart);
			Integer length = webUtility.safeInteger(rawLength);
			Integer draw =  webUtility.safeInteger(rawDraw);
		
		
		
		
		ProductAjaxData userProduct = new ProductAjaxData();
		
		
		List<Product> pList = productService.getProductsUser();
		
		//debuggy code
		for(int x = 0; x < 50; x++)
		{
			pList.addAll(productService.getProductsUser());
		}
		userProduct.setiTotalRecords(pList.size());
		userProduct.setiTotalDisplayRecords(pList.size());
		pList = pList.subList(start, length+start);
		for(Product p: pList)
		{
			p.setImageHtml("<img src='/french_ventures_secure/resources/image/product/compressed/" + p.getResourceURL()+"'>");
		}
		userProduct.setAaData(pList);
		
		
		userProduct.setsEcho(draw);
		return userProduct;
		//end debug
		
	}
}
