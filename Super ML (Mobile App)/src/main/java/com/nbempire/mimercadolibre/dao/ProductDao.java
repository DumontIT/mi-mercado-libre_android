package com.nbempire.mimercadolibre.dao;

import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.exception.UnfixableException;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 20/05/14, at 02:58.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface ProductDao {

    Product findByQuery(String siteId, String query) throws UnfixableException;
}
