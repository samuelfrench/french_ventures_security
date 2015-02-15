package french_ventures.spring.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import french_ventures.spring.domain.Product;

public class TableUtilityImpl implements TableUtility {

	// MOVE TO SERVICE TODO
	public List<Product> applyFilter(List<Product> list, Boolean descOrder,
			filterTypeEnum filterType) {
		switch (filterType) {
		case CURRENCY:
			return list.parallelStream().sorted(new Comparator<Product>() {

				@Override
				public int compare(Product p1, Product p2) {
					Integer compare = Double.valueOf(
							p1.getCost()
									.substring(1, p1.getCost().length() - 1))
							.compareTo(
									Double.valueOf(p2.getCost().substring(1,
											p2.getCost().length() - 1)));
					if (descOrder) {
						return compare;
					} else {
						return -compare;
					}
				}
			}).collect(Collectors.toList());

		default:
			return list;
		}
	}

	@Override
	public List<Product> isolateCurrentPage(List<Product> list, Integer start,
			Integer length) {
		try {
			if (list.size() == 0 || start < 0 || length < 1) {
				return null;
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			log.error("Could not get current page: malformed parameter");
			return null;
		}
		if (list.size() > start + length) {
			return list.subList(start, length + start);
		} else {
			return list.subList(start, list.size() - 1);
		}
	}
}
