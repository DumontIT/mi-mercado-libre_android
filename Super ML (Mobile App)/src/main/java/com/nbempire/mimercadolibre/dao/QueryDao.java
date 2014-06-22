package com.nbempire.mimercadolibre.dao;

import android.content.SharedPreferences;
import com.nbempire.mimercadolibre.domain.Query;

import java.util.List;

/**
 * Created on 21/06/14, at 20:43.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 2.
 */
public interface QueryDao {

    /**
     * Add this query to the saved queries.
     *
     * @param sharedPreferences
     *         The {@link android.content.SharedPreferences} storage system in where the {@code query} will  be persisted.
     * @param query
     *         The {@link com.nbempire.mimercadolibre.domain.Query} object to store.
     */
    void add(SharedPreferences sharedPreferences, Query query);

    List<Query> findAll(SharedPreferences sharedPreferences);
}
