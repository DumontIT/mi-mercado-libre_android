package com.nbempire.mimercadolibre.service;

import android.content.SharedPreferences;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.domain.Query;

import java.util.List;

/**
 * Created on 21/06/14, at 20:42.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 2.
 */
public interface QueryService {

    /**
     * Same as {@link com.nbempire.mimercadolibre.dao.QueryDao#add(android.content.SharedPreferences, com.nbempire.mimercadolibre.domain.Query)}
     */
    void add(SharedPreferences sharedPreferences, Query query);

    List<Query> findAll(SharedPreferences sharedPreferences);

    Query createFromProduct(Product product);
}
