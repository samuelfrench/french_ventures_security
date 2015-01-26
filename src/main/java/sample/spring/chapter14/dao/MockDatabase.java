package sample.spring.chapter14.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sample.spring.chapter14.domain.Product;


public class MockDatabase {
	
	//TODO - NEED TO FINISH
	
	private List<Product> dataStore;
	private static Integer IMAGE_START = 1;
	private static Integer PRODUCT_COUNT = 13;
	public MockDatabase()
	{
		dataStore = new ArrayList<Product>();
		Product p = null;
		Random r = new Random();
		for(Integer i = IMAGE_START; i < PRODUCT_COUNT; i++) {
			p = new Product();
			p.setProductCode("SAMPLE_".concat(i.toString()));
			p.setResourceURL("IMG_".concat(String.format("%04d", i)).concat(".JPG"));
			p.setQtyPerUnit(r.nextInt(1000));
			p.setRetailPriceUSD(r.nextDouble());
			p.setDescription("Description dynamically loaded for product code " + p.getProductCode());
			System.out.println(p.getProductCode());
			dataStore.add(p);
			p = null;
		}
	}
	
	
	public Product getProductByProductCode(String productCode)
	{
		Product returnValue = null;
		for(int i = 0; i < dataStore.size(); i++)
		{
			try {
				if(dataStore.get(i).getProductCode().equalsIgnoreCase(productCode))
				{
					returnValue = dataStore.get(i);
					break;
				}
			} catch (NullPointerException e) {
				returnValue = null;
			}
		}
		return returnValue;
	}
}
