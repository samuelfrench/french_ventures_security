package sample.spring.chapter14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter14.domain.Product;
import sample.spring.chapter14.service.ProductService;
import sample.spring.chapter14.service.WebUtility;


@Controller
@RequestMapping(value = "/view/product/*")
public class ProductViewController {
	
	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private WebUtility webUtility;
	
	
	@RequestMapping(value = "getList")
	@ResponseBody ModelAndView getProductList(WebRequest request)
	{
		ModelAndView viewModel = new ModelAndView();
		viewModel.setViewName("productList");
		viewModel.addObject("productListing", productService.getAllProducts());
		
		return viewModel;
	}
	
	@RequestMapping(value = "adminConsole")
	@ResponseBody ModelAndView getAdminConsole(WebRequest request)
	{
		
		ModelAndView viewModel = new ModelAndView();
		viewModel.setViewName("adminConsole");
		
		
		viewModel.addObject("productListing", productService.getAllProducts());
		
		return viewModel;
	}
	
	@RequestMapping(value = "getDetails/{productCode}")
	@ResponseBody ModelAndView detailedProductView(WebRequest request, @PathVariable String productCode)
	{
		ModelAndView viewModel = new ModelAndView();
		viewModel.setViewName("productDetails");
		Product p = productService.getProductByProductCode(productCode);
		p.setLargeImageDiv(productService.getProductImageContainer(productCode, false));
		viewModel.addObject("product", p);
		
		return viewModel;
	}
	
	
	
	
	@RequestMapping("productFinder")
	@ResponseBody ModelAndView getProductFinderView(WebRequest request)
	{
		return new ModelAndView("productFinder");
	}
}