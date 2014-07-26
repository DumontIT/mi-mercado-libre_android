package com.nbempire.mimercadolibre.service.impl;

import android.content.SharedPreferences;
import com.nbempire.mimercadolibre.dao.ProductDao;
import com.nbempire.mimercadolibre.dao.impl.ProductDaoImplSpring;
import com.nbempire.mimercadolibre.domain.AvailableFilter;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.exception.UnfixableException;
import com.nbempire.mimercadolibre.service.ProductService;

import java.util.ArrayList;
import java.util.Calendar;
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

    /**
     * Tag for class' log.
     */
    private static final String TAG = "ProductServiceImpl";

    private ProductDao productDao = new ProductDaoImplSpring();

    @Override
    public Product findByQuery(String siteId, String query) throws UnfixableException {
        Product product = productDao.findByQuery(siteId, query);
        product.setQuery(query);
        product.setSiteId(siteId);
        product.setDate(Calendar.getInstance().getTime());

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

    @Override
    public void add(SharedPreferences sharedPreferences, Product product) {
        //  TODO : Functionality : Check whether it already exists or not

        productDao.add(sharedPreferences, product);
    }

    @Override
    public List<Product> findAll(SharedPreferences sharedPreferences) {
        return productDao.findAll(sharedPreferences);
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
