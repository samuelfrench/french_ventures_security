package french_ventures.spring.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.ServletContextResource;
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
	
	@Autowired
	  private ApplicationContext appContext;
	
	
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
	@ResponseBody ModelAndView addNewProduct(@ModelAttribute("addProductModel") Product product,
			@Validated MultipartFile image)
	{
		System.out.println(product.getImage().getOriginalFilename());
		
		
		//String filePath = "C:\\dev\\apache-tomcat-8.0.18\\webapps\\french_ventures_secure\\resources\\image\\product\\upload\\" + product.getImage().getOriginalFilename();
		

		//debug	
	//String filePath =  + "\\resources\\image\\product\\thumb\\" + product.getImage().getOriginalFilename();

		
		try {

			product.setResourceURL(product.getImage().getOriginalFilename());
				product.getImage().transferTo(new File("/upload/" + product.getImage().getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		System.out.println("debug: product code: " + product.getProductCode());
		productService.saveProduct(product);
		ModelAndView viewModel = new ModelAndView("productFinder");
		viewModel.addObject("addProductModel", new Product());
		viewModel.addObject("status", "Added Successfully!");
		
		return viewModel;
	}
}