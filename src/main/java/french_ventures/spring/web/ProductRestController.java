package french_ventures.spring.web;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.mysql.jdbc.log.Log;

import french_ventures.spring.domain.Product;
import french_ventures.spring.domain.ProductAjaxData;
import french_ventures.spring.service.ProductService;
import french_ventures.spring.service.TableUtility;
import french_ventures.spring.service.WebUtility;
import french_ventures.spring.service.TableUtility.filterTypeEnum;

@RestController
@RequestMapping(value = "/rest/product")
public class ProductRestController {

	static Logger log = Logger.getLogger(WebUtility.class);

	@Autowired
	private ProductService productService;
	
	@Autowired
	private TableUtility tableUtility;

	@Autowired
	private WebUtility webUtility;

	@RequestMapping(value = "/customerProductTable", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProductAjaxData getAjaxData(WebRequest request,
			@RequestParam("start") String rawStart,
			@RequestParam("length") String rawLength,
			@RequestParam("draw") String rawDraw,
			@RequestParam("order[0][column]") String rawOrderColumn,
			@RequestParam("order[0][dir]") String rawOrderDirection) {
		// TODO 1: set response and accept type
		// TODO 2: Move request parameters into the actual request mapping
		// definition thing
		Integer start = webUtility.safeInteger(rawStart);
		Integer length = webUtility.safeInteger(rawLength);
		Integer draw = webUtility.safeInteger(rawDraw);

		// column ordering
		Integer orderColumn = webUtility.safeInteger(rawOrderColumn);
		Boolean descOrder = webUtility.isDescending(rawOrderDirection);

		ProductAjaxData userProduct = new ProductAjaxData();

		List<Product> pList = productService.getProductsUser();

		// debuggy code
		for (int x = 0; x < 1000; x++) {
			pList.addAll(productService.getProductsUser());
		}

		StringBuffer buffer = null;
		for (Product p : pList) {

			buffer = new StringBuffer();

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

			// TODO - DEMO - ADD RANDOM COST FOR NOW UNTIL WE GET INVENTORY DATA

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

			// p.setWeightInGrams(Math.);
		}
		// end debug

		userProduct.setiTotalRecords(pList.size());
		userProduct.setiTotalDisplayRecords(pList.size());

		pList = tableUtility.applyFilter(pList, descOrder,filterTypeEnum.CURRENCY);

		pList = tableUtility.isolateCurrentPage(pList, start, length);

		userProduct.setAaData(pList);

		userProduct.setsEcho(draw);
		return userProduct;
		// end debug

	}
	
	
	
}
