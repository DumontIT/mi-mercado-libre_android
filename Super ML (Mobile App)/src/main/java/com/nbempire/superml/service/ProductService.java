package com.nbempire.superml.service;

import com.nbempire.superml.domain.AvailableFilter;
import com.nbempire.superml.domain.Product;
import com.nbempire.superml.exception.UnfixableException;

import java.util.List;

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
     * @param siteId
     *         The site ID where to look for.
     * @param query
     *         The user query. The query should be as detailed as possible to find the best average price.
     *
     * @return The average price.
     */
    Product findByQuery(String siteId, String query) throws UnfixableException;

    /**
     * Get all possible values for the specified {@code category}
     *
     * @param product
     *         The {@link com.nbempire.superml.domain.Product} where the filters are.
     * @param category
     *         The name of the filter. It will be used to search in product's filters.
     *
     * @return A List of available filters that are the possible values that the specified {@code category} can take.
     */
    List<AvailableFilter> getSubcategories(Product product, String category);
}
