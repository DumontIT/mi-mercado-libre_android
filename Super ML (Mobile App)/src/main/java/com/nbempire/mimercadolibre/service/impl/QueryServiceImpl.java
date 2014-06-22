package com.nbempire.mimercadolibre.service.impl;

import android.content.SharedPreferences;
import com.nbempire.mimercadolibre.dao.QueryDao;
import com.nbempire.mimercadolibre.dao.impl.QueryDaoImpl;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.domain.Query;
import com.nbempire.mimercadolibre.service.QueryService;

import java.util.List;

/**
 * Created on 21/06/14, at 20:43.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 2.
 */
public class QueryServiceImpl implements QueryService {

    private QueryDao queryDao = new QueryDaoImpl();

    @Override
    public void add(SharedPreferences sharedPreferences, Query query) {
        //  TODO : Functionality : Check whether it already exists or not

        queryDao.add(sharedPreferences, query);
    }

    @Override
    public List<Query> findAll(SharedPreferences sharedPreferences) {
        return queryDao.findAll(sharedPreferences);
    }

    @Override
    public Query createFromProduct(Product product) {
        return new Query(product.getQuery(), product.getAveragePrice(), product.getMinimumPrice(), product.getMaximumPrice());
    }
}
