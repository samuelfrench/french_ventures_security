package sample.spring.chapter14.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter14.dao.ProductDao;
import sample.spring.chapter14.dao.ProductDaoImpl;
import sample.spring.chapter14.dao.ProductImageDAO;
import sample.spring.chapter14.dao.ProductImageDAOImpl;
import sample.spring.chapter14.domain.Product;
import sample.spring.chapter14.domain.ProductDetailsImpl;
import sample.spring.chapter14.service.FixedDepositService;
import sample.spring.chapter14.service.ProductService;
import sample.spring.chapter14.service.ProductServiceImpl;


@Controller
@RequestMapping(value = "/view/product/*")
public class ProductViewController {
	@Autowired
	private ProductService productService;
	
	/*
	 * TODO - MOVE ALL METHODS THAT RETURN VIEWS TO ANOTHER SEPERATE CONTROLLER
	 */
	@RequestMapping(value = "getList")
	@ResponseBody ModelAndView getProductList(WebRequest request)
	{
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		Iterator<GrantedAuthority> iterator = authorities.iterator();
		ModelAndView viewModel = new ModelAndView();
		viewModel.setViewName("productList");
		/* ProductImageDAO dao = new ProductImageDAOImpl();
		List<Product> productListing = new ArrayList<Product>();
		for(int x = 1; x < 13; x++) // TODO
		{
			productListing.add(dao.getProduct("SAMPLE_" + String.valueOf(x)));
		}
		*/
		viewModel.addObject("productListing", productService.getAllProducts());
		
		return viewModel;
	}
	
	@RequestMapping(value = "getDetails/{productCode}")
	@ResponseBody ModelAndView detailedProductView(WebRequest request, @PathVariable String productCode)
	{
		ModelAndView viewModel = new ModelAndView();
		viewModel.setViewName("productDetails");
		ProductImageDAO dao = new ProductImageDAOImpl();
		Product p = dao.getProduct(productCode);
		p.setLargeImageDiv(new ProductDetailsImpl().getProductImageContainer(productCode, false));
		viewModel.addObject("product", p);
		
		return viewModel;
	}
	
	@RequestMapping(value="/sqlTest")
	@ResponseBody String getAllProducts(WebRequest request)
	{
		return productService.getAllProducts().toString();
	}
}