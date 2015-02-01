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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter14.domain.Product;
import sample.spring.chapter14.domain.ProductAjaxData;
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
	
	@RequestMapping(value="/sqlTest")
	@ResponseBody String getAllProducts(WebRequest request)
	{
		return productService.getAllProducts().toString();
	}
	
	@RequestMapping(value = "customerProductTable", headers = "accept=application/json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody ProductAjaxData getAjaxData(WebRequest request)
	{
			Integer start = webUtility.safeInteger(request.getParameter("start"));
			Integer length = webUtility.safeInteger(request.getParameter("length"));
			Integer draw =  webUtility.safeInteger(request.getParameter("echo"));
		
		
		
		
		ProductAjaxData userProduct = new ProductAjaxData();
		
		
		List<Product> pList = productService.getProductsUser();
		
		//debuggy code
		for(int x = 0; x < 5; x++)
		{
			pList.addAll(productService.getProductsUser());
		}
		pList = pList.subList(start, length);
		userProduct.setAaData(pList);
		userProduct.setiTotalDisplayRecords(length);
		userProduct.setiTotalRecords(pList.size());
		userProduct.setsDraw(draw);
		return userProduct;
		//end debug
		
	}
	
	@RequestMapping("productFinder")
	@ResponseBody ModelAndView getProductFinderView(WebRequest request)
	{
		return new ModelAndView("productFinder");
	}
}