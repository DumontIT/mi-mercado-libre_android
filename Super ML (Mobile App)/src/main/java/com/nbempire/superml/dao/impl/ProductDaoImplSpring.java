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
    public Product findByQuery(String query) {
        String urlString = "http://super-ml.herokuapp.com/pp/averagePrice/" + query;

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        Product result = null;
        try {
            // Make the HTTP GET request, marshaling the response to a String
            result = restTemplate.getForObject(urlString, Product.class);
            Log.i(TAG, "Obtained product: " + result.getAveragePrice());
        } catch (RestClientException restClientException) {
            Log.e(TAG, "An error occurred while searching for: \"" + query + "\"");
        }

        return result;
    }
}
