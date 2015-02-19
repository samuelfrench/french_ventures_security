package french_ventures.spring.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import french_ventures.spring.domain.Product;
import french_ventures.spring.domain.ProductAjaxData;
import french_ventures.spring.domain.Response;
import french_ventures.spring.service.ProductService;
import french_ventures.spring.service.TableUtility;
import french_ventures.spring.service.WebUtility;
import french_ventures.spring.service.TableUtility.filterTypeEnum;

@RestController
@RequestMapping(value = "/rest/product")
public class ProductRestController {

	static Logger log = Logger.getLogger(ProductRestController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private TableUtility tableUtility;

	@Autowired
	private WebUtility webUtility;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody Response insertProduct(WebRequest request)
	{
		Response response = new Response(false);
		Product product = new Product();
		product.setProductCode(request.getParameter("productCode"));
		product.setWeightInGrams(webUtility.safeDouble(request.getParameter("weightInGrams")));
		product.setLength(webUtility.safeDouble(request.getParameter("length")));
		product.setWidth(webUtility.safeDouble(request.getParameter("width")));
		product.setThickness(webUtility.safeDouble(request.getParameter("thickness")));
		product.setQtyPerUnit(webUtility.safeInteger(request.getParameter("qtyPerUnit")));
		product.setDescription(webUtility.safeString(request.getParameter("description")));
		product.setRetailPriceUSD(webUtility.safeDouble(request.getParameter("retailPriceUSD")));
		productService.saveProduct(product);
		response.setSuccess(true);
		response.setMessage("Product added successfully!");
		
		
		return response;
	}
	
	
	@RequestMapping(value = "/customerProductTable", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProductAjaxData getAjaxData(WebRequest request,
			@RequestParam("start") String rawStart,
			@RequestParam("length") String rawLength,
			@RequestParam("draw") String rawDraw,
			@RequestParam("order[0][column]") String rawOrderColumn,
			@RequestParam("order[0][dir]") String rawOrderDirection,
			@RequestParam("search[value]") String rawSearch) {
		
	
		// definition thing
		Integer start = webUtility.safeInteger(rawStart);
		Integer length = webUtility.safeInteger(rawLength);
		Integer draw = webUtility.safeInteger(rawDraw);
		
		log.debug("Params: length: " + length + " draw " + draw + " start: " + start);

		// column ordering/filtering
		Integer orderColumn = webUtility.safeInteger(rawOrderColumn);
		Boolean descOrder = webUtility.isDescending(rawOrderDirection);
		String search = webUtility.safeString(rawSearch);
		log.debug("search: " + search);

		ProductAjaxData userProduct = new ProductAjaxData();

		List<Product> pList = productService.getProductsUser();
		log.debug("list size: " + pList.size());

		StringBuffer buffer = null;
		for (Product p : pList) {
			buffer = new StringBuffer();
			//create image cell
			buffer.append("<a href='/french_ventures_secure/resources/image/product/original/");
			buffer.append(p.getResourceURL());
			buffer.append("'>");
			buffer.append("<img id='");
			buffer.append(p.getResourceURL());
			buffer.append("' class = 'tableImageItem' ");
			buffer.append("src='/french_ventures_secure/resources/image/product/thumb/");
			buffer.append(p.getResourceURL());
			buffer.append("'></a>");
			p.setImageHtml(buffer.toString());

			// DEMO - ADD RANDOM COST FOR NOW UNTIL WE GET INVENTORY DATA
			String costString = "$" + (Math.random() * 1000);
			try {
				p.setCost(costString.substring(0, costString.indexOf('.') + 3));
			} catch (IndexOutOfBoundsException e) {
				p.setCost("$0.99");
			}
			if (pList.indexOf(p) % 3 != 2) {
				p.setUnitOnHand(200);
				p.setInStock("<h2>YES</h2>");
			} else {
				p.setUnitOnHand(0);
				p.setInStock("<p style='font-color: red;'>COMING SOON!</p>");
			}
		}
		// end debug
		
		log.debug("Original list size: " + pList.size());
		Integer totalRecords = pList.size();
		
		if (search != null && search.length() > 1 && !emptyList(pList)) {
			log.debug("Applying search: " + search);
			pList = tableUtility.search(pList, search);
			log.debug("Post search value: " + pList.size());
		} 

		if (orderColumn.equals(1) && !emptyList(pList)) {
			log.debug("Applying filter");
			pList = tableUtility.applyFilter(pList, descOrder,
					filterTypeEnum.CURRENCY);
			log.debug("Filtered results length: " + pList.size());
		}

		
		
		
		
		
		//get current subset of elements to display
		if(length+start > pList.size()){length = pList.size();}
		pList = tableUtility.isolateCurrentPage(pList, start, length);

		//finalize response data
		userProduct.setiTotalRecords(totalRecords);
		userProduct.setiTotalDisplayRecords(totalRecords);
		userProduct.setAaData(pList);
		userProduct.setsEcho(draw);
		
		return userProduct;
	}
	
	private Boolean emptyList(List<Product> pList){
		Boolean emptyResultSet;
		try{
			emptyResultSet = (pList.size() < 1);
		} catch (NullPointerException e){
			emptyResultSet = true;
		}
		return emptyResultSet;
	}
}
