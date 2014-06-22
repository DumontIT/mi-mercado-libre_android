package com.nbempire.mimercadolibre.dao;

import android.content.SharedPreferences;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.exception.UnfixableException;

import java.util.List;

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

    /**
     * Add this product to the saved queries.
     *
     * @param sharedPreferences
     *         The {@link android.content.SharedPreferences} storage system in where the {@code product} will  be persisted.
     * @param product
     *         The {@link com.nbempire.mimercadolibre.domain.Product} object to store.
     */
    void add(SharedPreferences sharedPreferences, Product product);

    List<Product> findAll(SharedPreferences sharedPreferences);
}
