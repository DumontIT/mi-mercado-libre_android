package com.nbempire.superml.dao.impl;

import android.util.Log;
import com.nbempire.superml.dao.ProductDao;
import com.nbempire.superml.domain.Product;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 20/05/14, at 02:58.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ProductDaoImplSpring implements ProductDao {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "ProductDaoImplSpring";

    @Override
    public Product findByQuery(String siteId, String query) {
        //  TODO : Refactor :  Extract host to MainKeys or something similar.
        String urlString = String.format("http://super-ml.herokuapp.com/%1$s/averagePrice/%2$s", siteId, query);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        Product result = null;
        try {
            Log.i(TAG, "GET call against: " + urlString);

            result = restTemplate.getForObject(urlString, Product.class);

            Log.i(TAG, "Obtained product: " + result.getAveragePrice());
        } catch (RestClientException restClientException) {
            Log.e(TAG, "An error occurred while searching for: \"" + query + "\", " + restClientException.getMessage());
        }

        return result;
    }
}
