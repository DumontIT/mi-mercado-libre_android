package com.nbempire.superml.service.impl;

import android.util.Log;
import com.nbempire.superml.MainKeys;
import com.nbempire.superml.dao.UserDao;
import com.nbempire.superml.domain.User;
import com.nbempire.superml.dto.SubscriptionDto;
import com.nbempire.superml.exception.UnfixableException;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 06/06/14, at 21:28.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class UserDaoImplSpring implements UserDao {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "UserDaoImplSpring";

    @Override
    public boolean updateSubscriptions(User user) throws UnfixableException {

        // Set the username and password for creating a Basic Auth request
        HttpAuthentication authHeader = new HttpBasicAuthentication(MainKeys.ServerAuthentication.USER, MainKeys.ServerAuthentication.PASSWORD);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAuthorization(authHeader);

        //  TODO : Unhard-code this...
        SubscriptionDto dto = new SubscriptionDto("ipod touch");
        HttpEntity<SubscriptionDto> requestEntity = new HttpEntity<SubscriptionDto>(dto, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        String urlString = String.format(MainKeys.API_HOST + "/users/%s/subscriptions", user.getId());
        boolean result;
        try {
            Log.i(TAG, "POST call against: " + urlString);

            // Make the HTTP POST request to the Basic Auth protected URL
            ResponseEntity<String> response = restTemplate.exchange(urlString, HttpMethod.POST, requestEntity, String.class);
            Log.i(TAG, "POST call returned status code: " + response.getStatusCode().getReasonPhrase());

            result = response.getStatusCode() == HttpStatus.OK;
        } catch (RestClientException restClientException) {
            Log.e(TAG, "An error occurred while adding subscriptions: " + restClientException.getMessage());
            throw new UnfixableException(restClientException);
        }

        return result;
    }
}