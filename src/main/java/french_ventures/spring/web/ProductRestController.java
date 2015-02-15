package french_ventures.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import french_ventures.spring.domain.Product;
import french_ventures.spring.domain.ProductAjaxData;
import french_ventures.spring.service.ProductService;
import french_ventures.spring.service.WebUtility;

@RestController
@RequestMapping(value = "/rest/product")
public class ProductRestController {

	@Autowired
	private ProductService productService;

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
		userProduct.setiTotalRecords(pList.size());
		userProduct.setiTotalDisplayRecords(pList.size());
		pList = pList.subList(start, length + start);
		for (Product p : pList) {
			p.setImageHtml("<a href='/french_ventures_secure/resources/image/product/original/"
					+ p.getResourceURL()
					+ "'>"
					+ "<img id='"
					+ p.getResourceURL()
					+ "' class = 'tableImageItem' "
					+ "src='/french_ventures_secure/resources/image/product/thumb/"
					+ p.getResourceURL() + "'></a>");

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
			// END DEMO/DEBUG CODE
		}
		userProduct.setAaData(pList);

		userProduct.setsEcho(draw);
		return userProduct;
		// end debug

	}
}
