package sample.spring.chapter14.domain;

import java.util.List;

public class ProductAjaxData {
	
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	private List<Product> aaData;
	private Integer draw;
	

	/**
	 * @return the iTotalDisplayRecords
	 */
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}


	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}


	/**
	 * @return the aaData
	 */
	public List<Product> getAaData() {
		return aaData;
	}


	/**
	 * @param aaData the aaData to set
	 */
	public void setAaData(List<Product> aaData) {
		this.aaData = aaData;
	}


	/**
	 * @return the iTotalRecords
	 */
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}


	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}


	/**
	 * @return the sEcho
	 */
	public Integer getsDraw() {
		return draw;
	}


	/**
	 * @param sEcho the sEcho to set
	 */
	public void setsEcho(Integer draw) {
		this.draw = draw;
	}
}
