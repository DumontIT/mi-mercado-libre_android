package com.nbempire.mimercadolibre.dao.impl;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.nbempire.mimercadolibre.MainKeys;
import com.nbempire.mimercadolibre.dao.QueryDao;
import com.nbempire.mimercadolibre.domain.Query;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 21/06/14, at 20:44.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 2.
 */
public class QueryDaoImpl implements QueryDao {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "QueryDaoImpl";

    @Override
    public void add(SharedPreferences sharedPreferences, Query query) {

        JSONArray queriesArray = new JSONArray();
        String storedQueriesJson = sharedPreferences.getString(MainKeys.Keys.SAVED_QUERIES, null);
        if (storedQueriesJson != null) {
            try {
                queriesArray = new JSONArray(storedQueriesJson);
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred while parsing JSON queries from sharedPreferences: " + e.getMessage());
            }
        }

        queriesArray.put(new Gson().toJson(query));

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString(MainKeys.Keys.SAVED_QUERIES, queriesArray.toString());
        preferencesEditor.commit();
    }

    @Override
    public List<Query> findAll(SharedPreferences sharedPreferences) {
        List<Query> storedQueries = new ArrayList<Query>();

        JSONArray queriesArray = new JSONArray();
        String storedQueriesJson = sharedPreferences.getString(MainKeys.Keys.SAVED_QUERIES, null);
        if (storedQueriesJson != null) {
            try {
                queriesArray = new JSONArray(storedQueriesJson);
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred while parsing JSON queries from sharedPreferences: " + e.getMessage());
            }
        }

        for (int index = 0; index < queriesArray.length(); index++) {
            try {
                storedQueries.add(new Gson().fromJson((String) queriesArray.get(index), Query.class));
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred while parsing a JSON Query: " + e.getMessage());
            }
        }

        return storedQueries;
    }
}
