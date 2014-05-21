package com.nbempire.superml.service.impl;

import com.nbempire.superml.dao.ProductDao;
import com.nbempire.superml.dao.impl.ProductDaoImplSpring;
import com.nbempire.superml.domain.Product;
import com.nbempire.superml.service.ProductService;

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
    public Product findByQuery(String siteId, String query) {
        return productDao.findByQuery(siteId, query);
    }
}
