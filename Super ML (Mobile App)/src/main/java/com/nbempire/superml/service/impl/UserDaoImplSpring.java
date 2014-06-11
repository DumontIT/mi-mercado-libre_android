package com.nbempire.superml.service.impl;

import android.util.Log;
import com.nbempire.superml.MainKeys;
import com.nbempire.superml.dao.UserDao;
import com.nbempire.superml.domain.AvailableFilter;
import com.nbempire.superml.domain.Subscriptions;
import com.nbempire.superml.domain.User;
import com.nbempire.superml.dto.SelectedFiltersDto;
import com.nbempire.superml.dto.SelectedValuesDto;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        HttpEntity<SubscriptionDto> requestEntity = new HttpEntity<SubscriptionDto>(createDto(user), httpHeaders);

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

    private SubscriptionDto createDto(User user) {
        SubscriptionDto subscriptionDto = new SubscriptionDto(user.getProduct().getQuery());

        List<SelectedFiltersDto> selectedFilters = parseSelectedFilters(user.getProduct().getAvailableFilters());
        Log.d(TAG, "Selected filters: " + selectedFilters.size());

        subscriptionDto.setSelectedFilters(selectedFilters);

        Set<Integer> selectedSubscriptions = new HashSet<Integer>();
        for (Subscriptions eachSubscription : user.getSubscriptions()) {
            selectedSubscriptions.add(eachSubscription.ordinal());
        }
        subscriptionDto.setSelectedSubscriptions(selectedSubscriptions);

        return subscriptionDto;
    }

    private List<SelectedFiltersDto> parseSelectedFilters(AvailableFilter[] availableFilters) {

        List<SelectedFiltersDto> filters = new ArrayList<SelectedFiltersDto>();
        for (AvailableFilter eachAvailableFilter : availableFilters) {

            List<SelectedValuesDto> values = new ArrayList<SelectedValuesDto>();
            for (AvailableFilter eachPossibleValue : eachAvailableFilter.getPossibleValues()) {
                if (eachPossibleValue.isChecked()) {
                    values.add(new SelectedValuesDto(eachPossibleValue.getId(), eachPossibleValue.getName()));
                }
            }

            if (!values.isEmpty()) {
                filters.add(new SelectedFiltersDto(eachAvailableFilter.getId(), values));
            }
        }

        return filters;
    }
}
