package com.nbempire.superml.service;

import com.nbempire.superml.domain.Product;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 17:57.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface ProductService {

    /**
     * Finds the average price for a specified query.
     *
     * @param query
     *         The user query. The query should be as detailed as possible to find the best average price.
     *
     * @return The average price.
     */
    Product findByQuery(String query);
}
