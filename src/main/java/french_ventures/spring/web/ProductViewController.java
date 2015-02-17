package french_ventures.spring.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import french_ventures.spring.domain.Product;
import french_ventures.spring.service.ProductService;
import french_ventures.spring.service.WebUtility;


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
		ModelAndView viewModel = new ModelAndView("productFinder");
		viewModel.addObject("addProductModel", new Product());
		return viewModel;
	}
	
	@RequestMapping(value = "addNewProduct", method = RequestMethod.POST)
	@ResponseBody ModelAndView addNewProduct(WebRequest request ,@ModelAttribute("addProductModel") Product product,
			@Validated MultipartFile image)
	{
		System.out.println(product.getImage().getOriginalFilename());
		System.out.println("debug: product code: " + product.getProductCode());
		productService.saveProduct(product);
		ModelAndView viewModel = new ModelAndView("productFinder");
		viewModel.addObject("addProductModel", new Product());
		viewModel.addObject("status", "Added Successfully!");
		return viewModel;
	}
}