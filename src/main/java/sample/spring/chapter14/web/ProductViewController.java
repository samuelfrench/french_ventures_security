package sample.spring.chapter14.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter14.domain.Product;
import sample.spring.chapter14.domain.ProductAjaxData;
import sample.spring.chapter14.service.ProductService;


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
	
	@RequestMapping(value = "adminConsole")
	@ResponseBody ModelAndView getAdminConsole(WebRequest request)
	{
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		Iterator<GrantedAuthority> iterator = authorities.iterator();
		ModelAndView viewModel = new ModelAndView();
		viewModel.setViewName("adminConsole");
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
		Product p = productService.getProductByProductCode(productCode);
		p.setLargeImageDiv(productService.getProductImageContainer(productCode, false));
		viewModel.addObject("product", p);
		
		return viewModel;
	}
	
	@RequestMapping(value="/sqlTest")
	@ResponseBody String getAllProducts(WebRequest request)
	{
		return productService.getAllProducts().toString();
	}
	
	@RequestMapping(value = "customerProductTable", headers = "accept=application/json")
	@ResponseBody ProductAjaxData getAjaxData(WebRequest request)
	{
		Integer draw = null;
		try{
			Integer start = Integer.parseInt(request.getParameter("start"));
			Integer length = Integer.parseInt(request.getParameter("length"));
			draw = Integer.parseInt(request.getParameter("sEcho"));
		} catch (NumberFormatException e)
		{
			draw = 0;
			
		}
		
		
		ProductAjaxData userProduct = new ProductAjaxData();
				//userProduct.setAaData(productService.getProductsUser());
		
		
		List<Product> pList = productService.getProductsUser();
		
		//debug
		for(int x = 0; x < 1; x++)
		{
			pList.addAll(productService.getProductsUser());
		}
		userProduct.setAaData(pList);
		userProduct.setiTotalDisplayRecords(10);
		userProduct.setiTotalRecords(2000);
		userProduct.setsDraw(draw);
		return userProduct;
		//end debug
		
		//return new Gson().toJson(userProduct.subList(start, start+length));
	}
	
	@RequestMapping("productFinder")
	@ResponseBody ModelAndView getProductFinderView(WebRequest request)
	{
		return new ModelAndView("productFinder");
	}
}