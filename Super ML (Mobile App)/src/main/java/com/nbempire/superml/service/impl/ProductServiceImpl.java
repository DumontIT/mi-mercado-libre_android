package com.nbempire.superml.service.impl;

import com.nbempire.superml.dao.ProductDao;
import com.nbempire.superml.dao.impl.ProductDaoImplSpring;
import com.nbempire.superml.domain.AvailableFilter;
import com.nbempire.superml.domain.Product;
import com.nbempire.superml.exception.UnfixableException;
import com.nbempire.superml.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 17:59.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImplSpring();

    @Override
    public Product findByQuery(String siteId, String query) throws UnfixableException {
        Product product = productDao.findByQuery(siteId, query);
        product.setQuery(query);
        return product;
    }

    @Override
    public List<AvailableFilter> getSubcategories(Product product, String category) {
        AvailableFilter selectedFilter = findFilterByName(product, category);

        List<AvailableFilter> filters = new ArrayList<AvailableFilter>();

        if (selectedFilter != null) {
            Collections.addAll(filters, selectedFilter.getPossibleValues());
        }

        return filters;
    }

    private AvailableFilter findFilterByName(Product product, String category) {
        AvailableFilter result = null;

        for (AvailableFilter eachAvailableFilter : product.getAvailableFilters()) {
            if (eachAvailableFilter.getName().equals(category)) {
                result = eachAvailableFilter;
            }
        }
        return result;
    }
}
